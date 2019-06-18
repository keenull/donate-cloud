package com.xshhope.user.controller;

import com.google.common.collect.Maps;
import com.xshhope.common.support.ActionResult;
import com.xshhope.common.support.ActionTemplate;
import com.xshhope.common.utils.AppUserUtil;
import com.xshhope.common.utils.Langs;
import com.xshhope.common.utils.Pager;
import com.xshhope.model.log.LogAnnotation;
import com.xshhope.model.user.AppUser;
import com.xshhope.model.user.LoginAppUser;
import com.xshhope.model.user.Qualificate;
import com.xshhope.model.user.SysRole;
import com.xshhope.user.model.UserRoleParam;
import com.xshhope.user.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private AppUserService appUserService;

    /**
     * 当前登录用户 LoginAppUser
     */
    @PostMapping("/users/current")
    public ActionResult<LoginAppUser> getLoginAppUser() {
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        Qualificate qualificate = appUserService.getQualificateByUserId(loginAppUser.getId());

        if (Langs.isEmpty(qualificate)){
            loginAppUser.setPassed(0);
        }else {
            loginAppUser.setPassed(qualificate.getPassed());
        }

        return ActionTemplate.toAck("成功", loginAppUser);
    }

    @GetMapping(value = "/users-anon/internal", params = "username")
    public LoginAppUser findByUsername(String username) {
        return appUserService.findByUsername(username);
    }

    /**
     * 用户查询
     *
     * @param
     */
    @PreAuthorize("hasAuthority('back:user:query')")
    @PostMapping("/users/find")
    public ActionResult findUsers(@RequestBody Map<String, Object> params) {

        Pager pager = appUserService.findUsers(params);

        Map<String, Object> map = Maps.newHashMap();
        map.put("current", pager.getCurrentPageNumber());
        map.put("size", pager.getPerPageSize());
        map.put("total", pager.getTotalElements());
        map.put("list", pager.getData());
        return ActionTemplate.toAck("查询成功", map);
    }

    @PreAuthorize("hasAuthority('back:user:query')")
    @GetMapping("/users/{id}")
    public ActionResult<AppUser> findUserById(@PathVariable Long id) {
        return ActionTemplate.toAck("查询成功", appUserService.findById(id));
    }

    @GetMapping("/users-anon/{id}")
    public AppUser findUserByIdd(@PathVariable("id") Long id) {
        return appUserService.findById(id);
    }

    /**
     * 添加用户,根据用户名注册
     *
     * @param appUser
     */
    @PostMapping("/users-anon/register")
    public ActionResult<AppUser> register(@RequestBody AppUser appUser) {
        // 用户名等信息的判断逻辑挪到service了
        appUserService.addAppUser(appUser);

        return ActionTemplate.toAck("添加成功", appUser);
    }

    /**
     * 修改自己的个人信息
     *
     * @param appUser
     */
    @LogAnnotation(module = "修改个人信息")
    @PutMapping("/users/me")
    public ActionResult<AppUser> updateMe(@RequestBody AppUser appUser) {
        AppUser user = AppUserUtil.getLoginAppUser();
        appUser.setId(user.getId());

        appUserService.updateAppUser(appUser);

        return ActionTemplate.toAck("修改成功", appUser);
    }

    /**
     * 修改密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    @LogAnnotation(module = "修改密码")
    @PutMapping(value = "/users/password", params = {"oldPassword", "newPassword"})
    public ActionResult updatePassword(String oldPassword, String newPassword) {
        if (StringUtils.isBlank(oldPassword)) {
            throw new IllegalArgumentException("旧密码不能为空");
        }
        if (StringUtils.isBlank(newPassword)) {
            throw new IllegalArgumentException("新密码不能为空");
        }

        AppUser user = AppUserUtil.getLoginAppUser();
        appUserService.updatePassword(user.getId(), oldPassword, newPassword);
        return ActionTemplate.toAck("修改密码成功");
    }

    /**
     * 管理后台，给用户重置密码
     *
     * @param id          用户id
     * @param newPassword 新密码
     */
    @LogAnnotation(module = "重置密码")
    @PreAuthorize("hasAuthority('back:user:password')")
    @PutMapping(value = "/users/{id}/password", params = {"newPassword"})
    public ActionResult resetPassword(@PathVariable Long id, String newPassword) {
        return appUserService.updatePassword(id, null, newPassword);
    }

    /**
     * 管理后台修改用户
     *
     * @param appUser
     */
    @LogAnnotation(module = "修改用户")
    @PreAuthorize("hasAuthority('back:user:update')")
    @PostMapping("/users/update")
    public ActionResult updateAppUser(@RequestBody AppUser appUser) {
        return appUserService.updateAppUser(appUser);
    }

    /**
     * 管理后台给用户分配角色
     *
     * @param param      用户id 角色ids
     */
    @LogAnnotation(module = "分配角色")
    @PreAuthorize("hasAuthority('back:user:role:set')")
    @PostMapping("/users/distributeRoles")
    public ActionResult setRoleToUser(@RequestBody UserRoleParam param) {
        return appUserService.setRoleToUser(param.getId(), param.getSet());
    }

    /**
     * 获取用户的角色
     *
     * @param id 用户id
     */
    @PreAuthorize("hasAnyAuthority('back:user:role:set','user:role:byuid')")
    @GetMapping("/users/{id}/roles")
    public ActionResult<Set<SysRole>> findRolesByUserId(@PathVariable Long id) {
        return ActionTemplate.toAck("获取成功", appUserService.findRolesByUserId(id));
    }

    /**
     * 绑定手机号
     *
     * @param phone
     * @param key
     * @param code
     */
    @LogAnnotation(module = "绑定手机号")
    @PostMapping(value = "/users/binding-phone")
    public ActionResult bindingPhone(String phone, String key, String code) {
        if (StringUtils.isBlank(phone)) {
            throw new IllegalArgumentException("手机号不能为空");
        }

        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("key不能为空");
        }

        if (StringUtils.isBlank(code)) {
            throw new IllegalArgumentException("code不能为空");
        }

        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        log.info("绑定手机号，key:{},code:{},username:{}", key, code, loginAppUser.getUsername());

        String value = "";
        if (value == null) {
            throw new IllegalArgumentException("验证码错误");
        }

        if (phone.equals(value)) {
            appUserService.bindingPhone(loginAppUser.getId(), phone);
        } else {
            throw new IllegalArgumentException("手机号不一致");
        }
        return ActionTemplate.toAck("绑定用户成功");
    }

    /**
     * 禁用用户
     *
     * @param id 用户id
     */
    @PreAuthorize("hasAnyAuthority('back:user:role:set','user:role:byuid')")
    @GetMapping("/users/banUser/{id}")
    public ActionResult banUserById(@PathVariable("id") Long id) {
        return appUserService.banUserById(id);
    }

    /**
     * 解禁用户
     *
     * @param id 用户id
     */
    @PreAuthorize("hasAnyAuthority('back:user:role:set','user:role:byuid')")
    @GetMapping("/users/unBanUser/{id}")
    public ActionResult unBanUserById(@PathVariable("id") Long id) {
        return appUserService.unBanUserById(id);
    }

    /**
     * 资质审核申请
     *
     * @param qualificate
     * @return
     */
    @PostMapping("/users/userQualificate")
    public ActionResult<Qualificate> toQualificate(@RequestBody Qualificate qualificate) {
        // 用户名等信息的判断逻辑挪到service了
        appUserService.toQualificate(qualificate);

        return ActionTemplate.toAck("添加成功", qualificate);
    }

    /**
     * 得到资质审核
     *
     * @param id
     * @return
     */
    @GetMapping("/users/getQualificate/{id}")
    public ActionResult<Qualificate> getQualificate(@PathVariable("id") Long id) {
        // 用户名等信息的判断逻辑挪到service了
        Qualificate qualificate = appUserService.getQualificateByUserId(id);

        if (Langs.isEmpty(qualificate)){
            return ActionTemplate.toNack("该用户尚未申请");
        }

        return ActionTemplate.toAck("添加成功", qualificate);
    }

    /**
     * 资质审核通过
     *
     * @param id
     * @return
     */
    @GetMapping("/users/qualificateSuccess/{id}")
    public ActionResult qualificateSuccess(@PathVariable("id") Long id) {
        // 用户名等信息的判断逻辑挪到service了
        appUserService.qualificateSuccess(id);

        return ActionTemplate.toAck("审核成功");
    }

    /**
     * 资质审核失败
     *
     * @param id
     * @return
     */
    @GetMapping("/users/qualificateFail/{id}")
    public ActionResult qualificateFail(@PathVariable("id") Long id) {
        // 用户名等信息的判断逻辑挪到service了
        appUserService.qualificateFail(id);

        return ActionTemplate.toAck("审核成功");
    }
}
