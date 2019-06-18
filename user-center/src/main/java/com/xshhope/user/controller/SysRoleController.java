package com.xshhope.user.controller;

import com.google.common.collect.Maps;
import com.xshhope.common.support.ActionResult;
import com.xshhope.common.support.ActionTemplate;
import com.xshhope.common.utils.Pager;
import com.xshhope.model.log.LogAnnotation;
import com.xshhope.model.user.SysPermission;
import com.xshhope.model.user.SysRole;
import com.xshhope.user.model.RolePermParam;
import com.xshhope.user.service.SysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;

	/**
	 * 管理后台添加角色
	 * 
	 * @param sysRole
	 */
	@LogAnnotation(module = "添加角色")
	@PreAuthorize("hasAuthority('back:role:save')")
	@PostMapping("/roles/add")
	public ActionResult<SysRole> save(@RequestBody SysRole sysRole) {
		if (StringUtils.isBlank(sysRole.getCode())) {
			throw new IllegalArgumentException("角色code不能为空");
		}
		if (StringUtils.isBlank(sysRole.getName())) {
			sysRole.setName(sysRole.getCode());
		}

		sysRoleService.save(sysRole);

		return ActionTemplate.toAck("添加成功", sysRole);
	}

	/**
	 * 管理后台删除角色
	 * 
	 * @param id
	 */
	@LogAnnotation(module = "删除角色")
	@PreAuthorize("hasAuthority('back:role:delete')")
	@GetMapping("/roles/delete/{id}")
	public ActionResult deleteRole(@PathVariable("id") Long id) {
		return sysRoleService.deleteRole(id);
	}

	/**
	 * 管理后台修改角色
	 * 
	 * @param sysRole
	 */
	@LogAnnotation(module = "修改角色")
	@PreAuthorize("hasAuthority('back:role:update')")
	@PostMapping("/roles/update")
	public ActionResult<SysRole> update(@RequestBody SysRole sysRole) {
		if (StringUtils.isBlank(sysRole.getName())) {
			throw new IllegalArgumentException("角色名不能为空");
		}

		return sysRoleService.update(sysRole);
	}

	/**
	 * 管理后台给角色分配权限
	 * @param param
	 * @return
	 */
	@LogAnnotation(module = "分配权限")
	@PreAuthorize("hasAuthority('back:role:permission:set')")
	@PostMapping("/roles/distributePer")
	public ActionResult setPermissionToRole(@RequestBody RolePermParam param) {

		return sysRoleService.setPermissionToRole(param.getId(), param.getSet());
	}

	/**
	 * 获取角色的权限
	 * 
	 * @param id
	 */
	@PreAuthorize("hasAnyAuthority('back:role:permission:set','role:permission:byroleid')")
	@GetMapping("/roles/{id}/permissions")
	public ActionResult<Set<SysPermission>> findPermissionsByRoleId(@PathVariable Long id) {
		Set<SysPermission> sysPermissionSet = sysRoleService.findPermissionsByRoleId(id);
		return ActionTemplate.toAck("根据角色ID查询角色权限成功",sysPermissionSet);
	}

	@PreAuthorize("hasAuthority('back:role:query')")
	@GetMapping("/roles/{id}")
	public ActionResult<SysRole> findById(@PathVariable Long id) {
		SysRole sysRole = sysRoleService.findById(id);
		return ActionTemplate.toAck("查询角色成功",sysRole);
	}

	/**
	 * 搜索角色
	 * 
	 * @param params
	 */
	@PreAuthorize("hasAuthority('back:role:query')")
	@PostMapping("/roles/find")
	public ActionResult findRoles(@RequestBody Map<String, Object> params) {
		Pager<SysRole> pager = sysRoleService.findRoles(params);

		Map<String, Object> map = Maps.newHashMap();
		map.put("current", pager.getCurrentPageNumber());
		map.put("size", pager.getPerPageSize());
		map.put("total", pager.getTotalElements());
		map.put("list", pager.getData());
		return ActionTemplate.toAck("查询成功", map);
	}

}
