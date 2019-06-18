package com.xshhope.user.service;

import com.xshhope.common.support.ActionResult;
import com.xshhope.common.utils.Pager;
import com.xshhope.model.user.SysPermission;
import com.xshhope.model.user.SysRole;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SysRoleService {

	ActionResult save(SysRole sysRole);

	ActionResult update(SysRole sysRole);

	ActionResult deleteRole(Long id);

	ActionResult setPermissionToRole(Long id, List<Long> permissionIds);

	SysRole findById(Long id);

	Pager<SysRole> findRoles(Map<String, Object> params);

	Set<SysPermission> findPermissionsByRoleId(Long roleId);
}
