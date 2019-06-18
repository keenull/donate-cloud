package com.xshhope.user.dao;

import com.xshhope.model.user.Qualificate;
import org.apache.ibatis.annotations.*;

/**
 * @author xshhope
 */
@Mapper
public interface QualificateDao {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user_qualificate(userId, colleague, school, studentId, file, deleted, passed, img) "
            + "values(#{userId}, #{colleague}, #{school}, #{studentId}, #{file}, #{deleted}, #{passed}, #{img})")
    int save(Qualificate qualificate);

    @Update("update user_qualificate t set t.is_deleted = 0 where id = #{id}")
    int update(Qualificate qualificate);

    @Update("update user_qualificate t set t.passed = 1 where id = #{id}")
    int updateSuccess(Long id);

    @Update("update user_qualificate t set t.passed = 0 where id = #{id}")
    int updateFail(Long id);

    @Select("select * from user_qualificate t where t.userId = #{id}")
    Qualificate getQualificateByUserId(Long id);
}
