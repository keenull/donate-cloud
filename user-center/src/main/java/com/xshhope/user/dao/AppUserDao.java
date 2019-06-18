package com.xshhope.user.dao;

import com.xshhope.model.user.AppUser;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface AppUserDao {

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into app_user(username, password, nickname, headImgUrl, phone, sex, enabled, type, createTime, updateTime) "
			+ "values(#{username}, #{password}, #{nickname}, #{headImgUrl}, #{phone}, #{sex}, #{enabled}, #{type}, #{createTime}, #{updateTime})")
	int save(AppUser appUser);

	int update(AppUser appUser);

	@Deprecated
	@Select("select * from app_user t where t.username = #{username}")
	AppUser findByUsername(String username);

	@Select("select * from app_user t where t.id = #{id}")
	AppUser findById(Long id);

	int count(Map<String, Object> params);

	List<AppUser> findData(Map<String, Object> params);

	@Update({ "update app_user set enabled = 0 where id = #{id}" })
	int banUserById(Long id);

	@Update({ "update app_user set enabled = 1 where id = #{id}" })
	int unBanUserById(Long id);
}
