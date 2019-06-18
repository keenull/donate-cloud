package com.xshhope.user.service.impl;

import com.xshhope.common.support.ActionResult;
import com.xshhope.common.support.ActionTemplate;
import com.xshhope.common.utils.PageUtil;
import com.xshhope.common.utils.Pager;
import com.xshhope.model.user.SysPermission;
import com.xshhope.user.dao.RolePermissionDao;
import com.xshhope.user.dao.SysPermissionDao;
import com.xshhope.user.service.SysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

	@Autowired
	private SysPermissionDao sysPermissionDao;
	@Autowired
	private RolePermissionDao rolePermissionDao;

	@Override
	public Set<SysPermission> findByRoleIds(Set<Long> roleIds) {
		return rolePermissionDao.findPermissionsByRoleIds(roleIds);
	}

	@Transactional
	@Override
	public ActionResult save(SysPermission sysPermission) {
		SysPermission permission = sysPermissionDao.findByPermission(sysPermission.getPermission());
		if (permission != null) {
			throw new IllegalArgumentException("权限标识已存在");
		}
		sysPermission.setCreateTime(new Date());
		sysPermission.setUpdateTime(sysPermission.getCreateTime());

		sysPermissionDao.save(sysPermission);
		log.info("保存权限标识：{}", sysPermission);

		return ActionTemplate.toAck("保存权限标识成功");
	}

	@Transactional
	@Override
	public ActionResult update(SysPermission sysPermission) {
		sysPermission.setUpdateTime(new Date());
		sysPermissionDao.update(sysPermission);
		log.info("修改权限标识：{}", sysPermission);

		return ActionTemplate.toAck("修改权限标识成功");
	}

	@Transactional
	@Override
	public ActionResult delete(Long id) {
		SysPermission permission = sysPermissionDao.findById(id);
		if (permission == null) {
			throw new IllegalArgumentException("权限标识不存在");
		}

		sysPermissionDao.delete(id);
		rolePermissionDao.deleteRolePermission(null, id);
		log.info("删除权限标识：{}", permission);

		return ActionTemplate.toAck("删除权限标识成功");
	}

	@Override
	public Pager<SysPermission> findPermissions(Map<String, Object> params) {

		if (params.containsKey("name")){
			params.put("name", params.get("name").toString().trim());
		}

		int total = sysPermissionDao.count(params);
		List<SysPermission> list = Collections.emptyList();
		if (total > 0) {
			PageUtil.pageParamConver(params, true);
			list = sysPermissionDao.findData(params);
		}

		Pager<SysPermission> pager = new Pager<>();
		pager.setData(list);
		pager.setTotalElements(total);
		return pager;
	}

	@Override
	public SysPermission findByPermissionId(Long id) {
		SysPermission sysPermission = sysPermissionDao.findById(id);
		return sysPermission;
	}
}
