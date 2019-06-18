package com.xshhope.user.dao;

import com.xshhope.model.user.SysRole;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysRoleDao {

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into sys_role(code, name, createTime, updateTime, is_deleted) values(#{code}, #{name}, #{createTime}, #{createTime}, 1)")
	int save(SysRole sysRole);

	@Update("update sys_role t set t.name = #{name} ,t.updateTime = #{updateTime} where t.id = #{id}")
	int update(SysRole sysRole);

	@Select("select * from sys_role t where t.id = #{id}")
	SysRole findById(Long id);

	@Select("select * from sys_role t where t.code = #{code}")
	SysRole findByCode(String code);

	@Update("update sys_role t set t.is_deleted = 0 where id = #{id}")
	int delete(Long id);

	int count(Map<String, Object> params);

	List<SysRole> findData(Map<String, Object> params);

}
