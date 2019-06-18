package com.xshhope.user.service.impl;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.xshhope.common.support.ActionResult;
import com.xshhope.common.support.ActionTemplate;
import com.xshhope.common.utils.PageUtil;
import com.xshhope.common.utils.Pager;
import com.xshhope.model.user.SysPermission;
import com.xshhope.model.user.SysRole;
import com.xshhope.model.user.constants.UserCenterMq;
import com.xshhope.user.dao.RolePermissionDao;
import com.xshhope.user.dao.SysRoleDao;
import com.xshhope.user.dao.UserRoleDao;
import com.xshhope.user.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private RolePermissionDao rolePermissionDao;
	@Autowired
	private AmqpTemplate amqpTemplate;

	@Transactional
	@Override
	public ActionResult save(SysRole sysRole) {
		SysRole role = sysRoleDao.findByCode(sysRole.getCode());
		if (role != null) {
			throw new IllegalArgumentException("角色code已存在");
		}

		sysRole.setCreateTime(new Date());
		sysRole.setUpdateTime(sysRole.getCreateTime());

		sysRoleDao.save(sysRole);
		log.info("保存角色：{}", sysRole);
		return ActionTemplate.toAck("保存角色成功");
	}

	@Transactional
	@Override
	public ActionResult update(SysRole sysRole) {
		sysRole.setUpdateTime(new Date());

		sysRoleDao.update(sysRole);
		log.info("修改角色：{}", sysRole);

		return ActionTemplate.toAck("修改角色成功");
	}

	@Transactional
	@Override
	public ActionResult deleteRole(Long id) {
		SysRole sysRole = sysRoleDao.findById(id);

		sysRoleDao.delete(id);
		rolePermissionDao.deleteRolePermission(id, null);
		userRoleDao.deleteUserRole(null, id);

		log.info("删除角色：{}", sysRole);

		// 发布role删除的消息
		amqpTemplate.convertAndSend(UserCenterMq.MQ_EXCHANGE_USER, UserCenterMq.ROUTING_KEY_ROLE_DELETE, id);

		return ActionTemplate.toAck("删除角色成功");
	}

	/**
	 * 给角色设置权限
	 *
	 * @param roleId
	 * @param permissionIds
	 */
	@Transactional
	@Override
	public ActionResult setPermissionToRole(Long roleId, List<Long> permissionIds) {
		SysRole sysRole = sysRoleDao.findById(roleId);
		if (sysRole == null) {
			throw new IllegalArgumentException("角色不存在");
		}

		// 查出角色对应的old权限
		Set<Long> oldPermissionIds = rolePermissionDao.findPermissionsByRoleIds(Sets.newHashSet(roleId)).stream()
				.map(p -> p.getId()).collect(Collectors.toSet());

		// 需要添加的权限
		Collection<Long> addPermissionIds = org.apache.commons.collections4.CollectionUtils.subtract(permissionIds,
				oldPermissionIds);
		if (!CollectionUtils.isEmpty(addPermissionIds)) {
			addPermissionIds.forEach(permissionId -> {
				rolePermissionDao.saveRolePermission(roleId, permissionId);
			});
		}
		// 需要移除的权限
		Collection<Long> deletePermissionIds = org.apache.commons.collections4.CollectionUtils
				.subtract(oldPermissionIds, permissionIds);
		if (!CollectionUtils.isEmpty(deletePermissionIds)) {
			deletePermissionIds.forEach(permissionId -> {
				rolePermissionDao.deleteRolePermission(roleId, permissionId);
			});
		}

		log.info("给角色id：{}，分配权限：{}", roleId, permissionIds);

		return ActionTemplate.toAck("给角色分配权限成功");
	}

	@Override
	public SysRole findById(Long id) {
		return sysRoleDao.findById(id);
	}

	@Override
	public Pager<SysRole> findRoles(Map<String, Object> params) {

		if (params.containsKey("name")){
			params.put("name", params.get("name").toString().trim());
		}

		int total = sysRoleDao.count(params);
		List<SysRole> list = Collections.emptyList();
		if (total > 0) {
			PageUtil.pageParamConver(params, true);

			list = sysRoleDao.findData(params);
		}
		Pager<SysRole> pager = new Pager<>();
		pager.setData(list);
		pager.setTotalElements(total);

		Map<String, Object> map = Maps.newHashMap();
		map.put("current", pager.getCurrentPageNumber());
		map.put("size", pager.getPerPageSize());
		map.put("total", pager.getTotalElements());
		map.put("list", pager.getData());
		return pager;
	}

	@Override
	public Set<SysPermission> findPermissionsByRoleId(Long roleId) {
		return rolePermissionDao.findPermissionsByRoleIds(Sets.newHashSet(roleId));
	}
}
