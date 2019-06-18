package com.xshhope.user.service;

import com.xshhope.common.support.ActionResult;
import com.xshhope.common.utils.Pager;
import com.xshhope.model.user.SysPermission;

import java.util.Map;
import java.util.Set;

public interface SysPermissionService {

	/**
	 * 根绝角色ids获取权限集合
	 * 
	 * @param roleIds
	 * @return
	 */
	Set<SysPermission> findByRoleIds(Set<Long> roleIds);

	ActionResult save(SysPermission sysPermission);

	ActionResult update(SysPermission sysPermission);

	ActionResult delete(Long id);

	Pager<SysPermission> findPermissions(Map<String, Object> params);

    SysPermission findByPermissionId(Long id);
}
