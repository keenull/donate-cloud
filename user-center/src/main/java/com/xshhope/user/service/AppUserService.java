package com.xshhope.user.service;

import com.xshhope.common.support.ActionResult;
import com.xshhope.common.utils.Pager;
import com.xshhope.model.user.AppUser;
import com.xshhope.model.user.LoginAppUser;
import com.xshhope.model.user.Qualificate;
import com.xshhope.model.user.SysRole;
import com.xshhope.user.model.AppUserDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AppUserService {

	ActionResult addAppUser(AppUser appUser);

	ActionResult updateAppUser(AppUser appUser);

	LoginAppUser findByUsername(String username);

	AppUser findById(Long id);

	ActionResult setRoleToUser(Long id, List<Long> roleIds);

	ActionResult updatePassword(Long id, String oldPassword, String newPassword);

	Pager<AppUserDTO> findUsers(Map<String, Object> params);

	Set<SysRole> findRolesByUserId(Long userId);

	ActionResult bindingPhone(Long userId, String phone);

	ActionResult banUserById(Long id);

	ActionResult unBanUserById(Long id);

    void toQualificate(Qualificate qualificate);

	void qualificateSuccess(Long id);

	void qualificateFail(Long id);

    Qualificate getQualificateByUserId(Long id);
}
