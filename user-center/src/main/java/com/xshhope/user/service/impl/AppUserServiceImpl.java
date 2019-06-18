package com.xshhope.user.service.impl;

import com.google.common.collect.Lists;
import com.xshhope.common.support.ActionResult;
import com.xshhope.common.support.ActionTemplate;
import com.xshhope.common.utils.*;
import com.xshhope.model.user.*;
import com.xshhope.model.user.constants.CredentialType;
import com.xshhope.model.user.constants.UserType;
import com.xshhope.user.dao.AppUserDao;
import com.xshhope.user.dao.QualificateDao;
import com.xshhope.user.dao.UserCredentialsDao;
import com.xshhope.user.dao.UserRoleDao;
import com.xshhope.user.model.AppUserDTO;
import com.xshhope.user.service.AppUserService;
import com.xshhope.user.service.SysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserDao appUserDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private UserCredentialsDao userCredentialsDao;
    @Autowired
    private QualificateDao qualificateDao;

    @Transactional
    @Override
    public ActionResult addAppUser(AppUser appUser) {
        String username = appUser.getUsername();
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("用户名不能为空");
        }

        if (PhoneUtil.checkPhone(username)) {// 防止用手机号直接当用户名，手机号要发短信验证
            throw new IllegalArgumentException("用户名要包含英文字符");
        }

        if (username.contains("@")) {// 防止用邮箱直接当用户名，邮箱也要发送验证（暂未开发）
            throw new IllegalArgumentException("用户名不能包含@");
        }

        if (username.contains("|")) {
            throw new IllegalArgumentException("用户名不能包含|字符");
        }

        if (StringUtils.isBlank(appUser.getPassword())) {
            throw new IllegalArgumentException("密码不能为空");
        }

        if (StringUtils.isBlank(appUser.getNickname())) {
            appUser.setNickname(username);
        }

        if (StringUtils.isBlank(appUser.getType())) {
            appUser.setType(UserType.APP.name());
        }

        UserCredential userCredential = userCredentialsDao.findByUsername(appUser.getUsername());
        if (userCredential != null) {
            throw new IllegalArgumentException("用户名已存在");
        }

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword())); // 加密密码
        appUser.setEnabled(Boolean.TRUE);
        appUser.setCreateTime(new Date());
        appUser.setUpdateTime(appUser.getCreateTime());

        appUserDao.save(appUser);
        userCredentialsDao
                .save(new UserCredential(appUser.getUsername(), CredentialType.USERNAME.name(), appUser.getId()));
        log.info("添加用户：{}", appUser);

        return ActionTemplate.toAck("添加用户成功");
    }

    @Transactional
    @Override
    public ActionResult updateAppUser(AppUser appUser) {
        appUser.setUpdateTime(new Date());

        appUserDao.update(appUser);
        log.info("修改用户：{}", appUser);

        return ActionTemplate.toAck("修改用户成功");
    }

    @Transactional
    @Override
    public LoginAppUser findByUsername(String username) {
        AppUser appUser = userCredentialsDao.findUserByUsername(username);
        if (appUser != null) {
            LoginAppUser loginAppUser = new LoginAppUser();
            BeanUtils.copyProperties(appUser, loginAppUser);

            Set<SysRole> sysRoles = userRoleDao.findRolesByUserId(appUser.getId());
            loginAppUser.setSysRoles(sysRoles);// 设置角色

            if (!CollectionUtils.isEmpty(sysRoles)) {
                Set<Long> roleIds = sysRoles.parallelStream().map(SysRole::getId).collect(Collectors.toSet());
                Set<SysPermission> sysPermissions = sysPermissionService.findByRoleIds(roleIds);
                if (!CollectionUtils.isEmpty(sysPermissions)) {
                    Set<String> permissions = sysPermissions.parallelStream().map(SysPermission::getPermission)
                            .collect(Collectors.toSet());

                    loginAppUser.setPermissions(permissions);// 设置权限集合
                }

            }

            return loginAppUser;
        }

        return null;
    }

    @Override
    public AppUser findById(Long id) {
        return appUserDao.findById(id);
    }

    /**
     * 给用户设置角色<br>
     * 这里采用先删除老角色，再插入新角色
     */
    @Transactional
    @Override
    public ActionResult setRoleToUser(Long id, List<Long> roleIds) {
        AppUser appUser = appUserDao.findById(id);
        if (appUser == null) {
            throw new IllegalArgumentException("用户不存在");
        }

        userRoleDao.deleteUserRole(id, null);
        if (!CollectionUtils.isEmpty(roleIds)) {
            roleIds.forEach(roleId -> {
                userRoleDao.saveUserRoles(id, roleId);
            });
        }

        log.info("修改用户：{}的角色，{}", appUser.getUsername(), roleIds);

        return ActionTemplate.toAck("修改用户成功");
    }

    /**
     * 修改密码
     *
     * @param id
     * @param oldPassword
     * @param newPassword
     */
    @Transactional
    @Override
    public ActionResult updatePassword(Long id, String oldPassword, String newPassword) {
        AppUser appUser = appUserDao.findById(id);
        if (StringUtils.isNoneBlank(oldPassword)) {
            if (!passwordEncoder.matches(oldPassword, appUser.getPassword())) { // 旧密码校验
                throw new IllegalArgumentException("旧密码错误");
            }
        }

        AppUser user = new AppUser();
        user.setId(id);
        user.setPassword(passwordEncoder.encode(newPassword)); // 加密密码

        updateAppUser(user);
        log.info("修改密码：{}", user);

        return ActionTemplate.toAck("修改密码成功");
    }

    @Override
    public Pager<AppUserDTO> findUsers(Map<String, Object> params) {

        if (params.containsKey("nickname")){
            params.put("nickname", params.get("nickname").toString().trim());
        }

        int total = appUserDao.count(params);
        List<AppUser> list = Lists.newArrayList();
        List<AppUserDTO> appUserDTOS = Lists.newArrayList();
        if (total > 0) {
            PageUtil.pageParamConver(params, true);

            list = appUserDao.findData(params);
        }

        list.forEach(x ->{
            AppUserDTO appUserDTO = new AppUserDTO();
            appUserDTO.setId(x.getId());
            appUserDTO.setUsername(x.getUsername());
            appUserDTO.setNickname(x.getNickname());
            appUserDTO.setPhone(x.getPhone());
            appUserDTO.setMail(x.getMail());
            appUserDTO.setSex(x.getSex());
            appUserDTO.setEnabled(x.getEnabled());
            appUserDTO.setType(x.getType());

            Qualificate qualificate = qualificateDao.getQualificateByUserId(x.getId());
            if (Langs.isEmpty(qualificate)){
                appUserDTO.setPassed(0);
            }else {
                appUserDTO.setPassed(qualificate.getPassed());
            }
            appUserDTOS.add(appUserDTO);
        });
        Pager<AppUserDTO> pager = new Pager<>();
        pager.setData(appUserDTOS);
        pager.setTotalElements(total);
        return pager;
    }

    @Override
    public Set<SysRole> findRolesByUserId(Long userId) {
        return userRoleDao.findRolesByUserId(userId);
    }

    /**
     * 绑定手机号
     */
    @Transactional
    @Override
    public ActionResult bindingPhone(Long userId, String phone) {
        UserCredential userCredential = userCredentialsDao.findByUsername(phone);
        if (userCredential != null) {
            throw new IllegalArgumentException("手机号已被绑定");
        }

        AppUser appUser = appUserDao.findById(userId);
        appUser.setPhone(phone);

        updateAppUser(appUser);
        log.info("绑定手机号成功,username:{}，phone:{}", appUser.getUsername(), phone);

        // 绑定成功后，将手机号存到用户凭证表，后续可通过手机号+密码或者手机号+短信验证码登陆
        userCredentialsDao.save(new UserCredential(phone, CredentialType.PHONE.name(), userId));

        return ActionTemplate.toAck("绑定手机号成功");
    }

    @Override
    public ActionResult banUserById(Long id) {
        int result = appUserDao.banUserById(id);
        if (result > 0){
            return ActionTemplate.toAck("禁用用户成功");
        }
        return ActionTemplate.toNack("禁用用户失败");
    }

    @Override
    public ActionResult unBanUserById(Long id) {
        int result = appUserDao.unBanUserById(id);
        if (result > 0){
            return ActionTemplate.toAck("解禁用户成功");
        }
        return ActionTemplate.toNack("解禁用户失败");
    }

    @Override
    public void toQualificate(Qualificate qualificate) {
        AppUser user = AppUserUtil.getLoginAppUser();
        qualificate.setDeleted(0);
        qualificate.setPassed(0);
        qualificate.setUserId(user.getId());

        qualificateDao.save(qualificate);

        log.info("资质审核：{}", qualificate);

    }

    @Override
    public void qualificateSuccess(Long id) {
        qualificateDao.updateSuccess(id);
        log.info("审核通过");
    }

    @Override
    public void qualificateFail(Long id) {
        qualificateDao.updateFail(id);
        log.info("审核未通过");
    }

    @Override
    public Qualificate getQualificateByUserId(Long id) {
        return qualificateDao.getQualificateByUserId(id);
    }

}
