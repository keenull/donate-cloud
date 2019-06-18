package com.xshhope.apply.repository;

import com.xshhope.model.apply.ApplyPO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @author xshhope
 */
@Repository
public interface ApplyRepository extends JpaRepository<ApplyPO, Long> {

    @Query("from ApplyPO apply where apply.deleted = 0 and (apply.title like %:search% or apply.content like %:search% or apply.completed like %:search% or apply.goal like %:search% ) order by :orderField desc")
    List<ApplyPO> listApplyDESC(@Param("search") String search, @Param("orderField") String orderField, Pageable pageable);

    @Query("from ApplyPO apply where apply.deleted = 0 and (apply.title like %:search% or apply.content like %:search% or apply.completed like %:search% or apply.goal like %:search% ) order by :orderField desc")
    List<ApplyPO> listApplyASC(@Param("search") String search, @Param("orderField") String orderField, Pageable pageable);

    @Query("select count(1) from ApplyPO apply where apply.deleted = 0")
    Long countApplys();

    @Query("from ApplyPO apply where apply.id =:id and apply.deleted = 0")
    ApplyPO getApplyById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update ApplyPO apply set apply.applyState =:state, apply.goal =:goal, apply.title = :title, apply.gmtModified = :date where apply.id = :id")
    void update(@Param("state") Integer applyState, @Param("goal") Double goal, @Param("title") String title, @Param("date") Date date, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update ApplyPO apply set apply.gmtModified =:gmtModify, apply.deleted = 1 where apply.id = :applyId")
    void delete(@Param("applyId") Long id, @Param("gmtModify") Date date);


    @Modifying
    @Transactional
    @Query("update ApplyPO apply set apply.gmtModified =:gmtModify, apply.applyState = 0 where apply.id = :applyId")
    void approveApply(@Param("applyId") Long applyId, @Param("gmtModify") Date gmtModify);

    @Modifying
    @Transactional
    @Query("update ApplyPO apply set apply.gmtModified =:gmtModify, apply.applyState = 1 where apply.id = :applyId")
    void denyApply(@Param("applyId") Long applyId, @Param("gmtModify") Date gmtModify);

    @Query("from ApplyPO apply where apply.deleted = 0 and (apply.title like %:search% or apply.userName like %:search%) and apply.userId = :userId")
    List<ApplyPO> getApplyByUser(@Param("userId") Long userId, @Param("search") String search, Pageable pageable);
}
