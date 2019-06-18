package com.xshhope.user.controller;

import com.google.common.collect.Maps;
import com.xshhope.common.support.ActionResult;
import com.xshhope.common.support.ActionTemplate;
import com.xshhope.common.utils.Pager;
import com.xshhope.model.log.LogAnnotation;
import com.xshhope.model.user.SysPermission;
import com.xshhope.user.service.SysPermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class SysPermissionController {

	@Autowired
	private SysPermissionService sysPermissionService;

	/**
	 * 管理后台添加权限
	 * 
	 * @param sysPermission
	 * @return
	 */
	@LogAnnotation(module = "添加权限")
	@PreAuthorize("hasAuthority('back:permission:save')")
	@PostMapping("/permissions/add")
	public ActionResult<SysPermission> save(@RequestBody SysPermission sysPermission) {
		if (StringUtils.isBlank(sysPermission.getPermission())) {
			throw new IllegalArgumentException("权限标识不能为空");
		}
		if (StringUtils.isBlank(sysPermission.getName())) {
			throw new IllegalArgumentException("权限名不能为空");
		}

		sysPermissionService.save(sysPermission);

		return ActionTemplate.toAck("添加权限成功", sysPermission);
	}

	/**
	 * 管理后台修改权限
	 * 
	 * @param sysPermission
	 */
	@LogAnnotation(module = "修改权限")
	@PreAuthorize("hasAuthority('back:permission:update')")
	@PostMapping("/permissions/modify")
	public ActionResult<SysPermission> update(@RequestBody SysPermission sysPermission) {
		if (StringUtils.isBlank(sysPermission.getName())) {
			throw new IllegalArgumentException("权限名不能为空");
		}

		sysPermissionService.update(sysPermission);

		return ActionTemplate.toAck("修改权限成功", sysPermission);
	}

	/**
	 * 删除权限标识
	 * 
	 * @param id
	 */
	@LogAnnotation(module = "删除权限")
	@PreAuthorize("hasAuthority('back:permission:delete')")
	@GetMapping("/permissions/delete/{id}")
	public ActionResult delete(@PathVariable Long id) {
		return sysPermissionService.delete(id);
	}

	/**
	 * 查询所有的权限标识
	 */
	@PreAuthorize("hasAuthority('back:permission:query')")
	@PostMapping("/permissions/find")
	public ActionResult findPermissions(@RequestBody Map<String, Object> params) {
		Pager<SysPermission> pager = sysPermissionService.findPermissions(params);

		Map<String, Object> map = Maps.newHashMap();
		map.put("current", pager.getCurrentPageNumber());
		map.put("size", pager.getPerPageSize());
		map.put("total", pager.getTotalElements());
		map.put("list", pager.getData());
		return ActionTemplate.toAck("查询成功", map);
	}

	/**
	 * 根据id查找
	 */
	@GetMapping("/permissions/findById/{id}")
	public ActionResult<SysPermission> findPermissionById(@PathVariable("id") Long id) {

		SysPermission sysPermission = sysPermissionService.findByPermissionId(id);

		return ActionTemplate.toAck("查询成功", sysPermission);
	}
}
