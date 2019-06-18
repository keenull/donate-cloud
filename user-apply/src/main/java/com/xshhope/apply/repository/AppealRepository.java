package com.xshhope.apply.repository;

import com.xshhope.apply.model.dto.AppealApplyDTO;
import com.xshhope.apply.model.dto.AppealDTO;
import com.xshhope.apply.model.dto.RecordCountDTO;
import com.xshhope.model.apply.AppealPO;
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
public interface AppealRepository extends JpaRepository<AppealPO, Long> {

    @Query("from AppealPO appeal where appeal.deleted = 0 and appeal.appealType = 1 and appeal.appealVerify = 0 and (appeal.title like %:search% or appeal.content like %:search% ) and appeal.appealState = 0 order by :orderField desc")
    List<AppealPO> listPersonVerifiedDescT(@Param("search") String search, @Param("orderField") String orderField, Pageable pageable);

    @Query("from AppealPO appeal where appeal.deleted = 0 and appeal.appealType = 1 and appeal.appealVerify = 0 and (appeal.title like %:search% or appeal.content like %:search% ) and appeal.appealState = 0 order by :orderField asc")
    List<AppealPO> listPersonVerifiedAscT(@Param("search") String search, @Param("orderField") String orderField, Pageable pageable);

    @Query("from AppealPO appeal where appeal.deleted = 0 and appeal.appealType = 1 and appeal.appealVerify = 0 and (appeal.title like %:search% or appeal.content like %:search% ) and appeal.appealState = 1 order by :orderField desc")
    List<AppealPO> listPersonVerifiedCompletedDesc(@Param("search") String search, @Param("orderField") String orderField, Pageable pageable);

    @Query("from AppealPO appeal where appeal.deleted = 0 and appeal.appealType = 1 and appeal.appealVerify = 0 and (appeal.title like %:search% or appeal.content like %:search% ) and appeal.appealState = 1 order by :orderField asc")
    List<AppealPO> listPersonVerifiedCompletedAsc(@Param("search") String search, @Param("orderField") String orderField, Pageable pageable);

    @Query("select count(1) from AppealPO appeal where appeal.deleted = 0 and appeal.appealType = 1")
    Long countPersonAppeal();

    @Query("select count(1) from AppealPO appeal where appeal.deleted = 0 and appeal.appealType = 1 and appeal.appealVerify = 0")
    Long countPersonVerified();

    @Query("select count(1) from AppealPO appeal where appeal.deleted = 0 and appeal.appealType = 1 and appeal.appealVerify = 1")
    Long countPersonUnVerified();

    @Query("from AppealPO appeal where appeal.deleted = 0 and appeal.appealType = 0  and appeal.appealVerify = 0 and (appeal.title like %:search% or appeal.content like %:search% ) and appeal.appealState = 0 order by :orderField asc")
    List<AppealPO> listPublicVerifiedAsc(@Param("search") String search, @Param("orderField") String orderField, Pageable pageable);

    @Query("from AppealPO appeal where appeal.deleted = 0 and appeal.appealType = 0  and appeal.appealVerify = 0 and (appeal.title like %:search% or appeal.content like %:search% ) and appeal.appealState = 0 order by :orderField desc")
    List<AppealPO> listPublicVerifiedDesc(@Param("search") String search, @Param("orderField") String orderField, Pageable pageable);

    @Query("from AppealPO appeal where appeal.deleted = 0 and appeal.appealType = 0  and appeal.appealVerify = 0 and (appeal.title like %:search% or appeal.content like %:search% ) and appeal.appealState = 1 order by :orderField asc")
    List<AppealPO> listPublicVerifiedCompletedAsc(@Param("search") String search, @Param("orderField") String orderField, Pageable pageable);

    @Query("from AppealPO appeal where appeal.deleted = 0 and appeal.appealType = 0  and appeal.appealVerify = 0 and (appeal.title like %:search% or appeal.content like %:search% ) and appeal.appealState = 1 order by :orderField desc")
    List<AppealPO> listPublicVerifiedCompletedDesc(@Param("search") String search, @Param("orderField") String orderField, Pageable pageable);

    @Query("select count(1) from AppealPO appeal where appeal.deleted = 0 and appeal.appealType = 0")
    Long countPublicAppeal();

    @Query("select count(1) from AppealPO appeal where appeal.deleted = 0 and appeal.appealType = 0 and appeal.appealVerify = 1")
    Long countPublicUnVerified();

    @Query("select count(1) from AppealPO appeal where appeal.deleted = 0 and appeal.appealType = 0 and appeal.appealVerify = 0")
    Long countPublicVerified();

    @Query("from AppealPO appeal where appeal.deleted = 0 and appeal.appealType = 0 and appeal.id =:id")
    AppealPO getPublicAppealById(@Param("id") Long id);

    @Query("from AppealPO appeal where appeal.deleted = 0 and appeal.appealType = 1 and appeal.id =:id")
    AppealPO getPersonAppealById(@Param("id") Long id);

    @Query("from AppealPO appeal where appeal.deleted = 0 and appeal.userId =:userId and appeal.title =:title and appeal.content =:content and appeal.appealType =:appealType")
    AppealPO getAppealByIdAndOther(@Param("appealType") Integer appealType, @Param("userId") Long userId, @Param("title") String title, @Param("content") String content);

    @Query("from AppealPO appeal where appeal.id =:id and appeal.deleted = 0")
    AppealPO getAppealById(@Param("id") Long id);

    @Query("from AppealPO appeal where appeal.deleted = 0 and appeal.appealType = 1 and appeal.appealVerify = 1 and (appeal.title like %:search% or appeal.content like %:search% ) order by :orderField desc")
    List<AppealPO> listPersonUnVerifiedDesc(@Param("search") String search, @Param("orderField") String orderField, Pageable pageable);

    @Query("from AppealPO appeal where appeal.deleted = 0 and appeal.appealType = 1 and appeal.appealVerify = 1 and (appeal.title like %:search% or appeal.content like %:search% ) order by :orderField asc")
    List<AppealPO> listPersonUnVerifiedAsc(@Param("search") String search, @Param("orderField") String orderField, Pageable pageable);

    @Query("from AppealPO appeal where appeal.deleted = 0 and appeal.appealType = 0 and appeal.appealVerify = 1 and (appeal.title like %:search% or appeal.content like %:search% ) order by :orderField desc")
    List<AppealPO> listPublicUnVerifiedDesc(@Param("search") String search, @Param("orderField") String orderField, Pageable pageable);

    @Query("from AppealPO appeal where appeal.deleted = 0 and appeal.appealType = 0 and appeal.appealVerify = 1 and (appeal.title like %:search% or appeal.content like %:search% ) order by :orderField asc")
    List<AppealPO> listPublicUnVerifiedAsc(@Param("search") String search, @Param("orderField") String orderField, Pageable pageable);

    @Query("select new com.xshhope.apply.model.dto.RecordCountDTO(appeal.id,appeal.userName,appeal.title,appeal.goal,appeal.completed,appeal.gmtCreated,appeal.gmtModified,appeal.appealState) from AppealPO appeal where appeal.deleted = 0 order by :orderField desc, appeal.gmtCreated desc")
//    @Query("select new com.xshhope.apply.model.dto.RecordCountDTO(appeal.id,appeal.userName,appeal.title,appeal.goal,appeal.completed,appeal.gmtCreated,appeal.gmtModified,appeal.appealState) from AppealPO appeal where appeal.deleted = 0 and (appeal.userName like %:search% or appeal.title like %:search%) order by :orderField desc, appeal.gmtCreated desc")
    List<RecordCountDTO> donateCountDesc(@Param("orderField") String orderField, Pageable pageable);

    @Query("select new com.xshhope.apply.model.dto.RecordCountDTO(appeal.id,appeal.userName,appeal.title,appeal.completed,appeal.goal,appeal.gmtCreated,appeal.gmtModified,appeal.appealState) from AppealPO appeal where appeal.deleted = 0 order by :orderField desc, appeal.gmtCreated desc")
//    @Query("select new com.xshhope.apply.model.dto.RecordCountDTO(appeal.id,appeal.userName,appeal.title,appeal.completed,appeal.goal,appeal.gmtCreated,appeal.gmtModified,appeal.appealState) from AppealPO appeal where appeal.deleted = 0 and (appeal.userName like %:search% or appeal.title like %:search%) order by :orderField desc, appeal.gmtCreated desc")
    List<RecordCountDTO> donateCountAsc(@Param("orderField") String orderField, Pageable pageable);

    @Query("select count(1) from AppealPO appeal where appeal.deleted = 0")
    Long countAppeal();

    @Query("select new com.xshhope.apply.model.dto.AppealDTO(appeal.id,appeal.title,appeal.appealCount,appeal.goal,appeal.completed,appeal.appealState,appeal.gmtModified) from AppealPO appeal where appeal.deleted = 0 and appeal.title like %:search% and appeal.appealType = 1 and appeal.appealVerify = 0")
    List<AppealDTO> listPersonVerifiedOnlyDesc(@Param("search") String search, Pageable pageable);

    @Query("select new com.xshhope.apply.model.dto.AppealDTO(appeal.id,appeal.title,appeal.appealCount,appeal.goal,appeal.completed,appeal.appealState,appeal.gmtModified) from AppealPO appeal where appeal.deleted = 0 and appeal.title like %:search% and appeal.appealType = 1 and appeal.appealVerify = 0 and appeal.gmtModified >=:beginTime "
            + "and appeal.gmtModified <=:endTime")
    List<AppealDTO> listPersonVerifiedDesc(@Param("search") String search, @Param("beginTime") Date begin, @Param("endTime") Date end, Pageable pageable);

    @Query("select new com.xshhope.apply.model.dto.AppealDTO(appeal.id,appeal.title,appeal.appealCount,appeal.goal,appeal.completed,appeal.appealState,appeal.gmtModified) from AppealPO appeal where appeal.deleted = 0 and appeal.appealType = 0 and appeal.appealVerify = 0 and appeal.title like %:search%")
    List<AppealDTO> listPublicVerifiedOnlyDesc(@Param("search") String search, Pageable pageable);

    @Query("select new com.xshhope.apply.model.dto.AppealDTO(appeal.id,appeal.title,appeal.appealCount,appeal.goal,appeal.completed,appeal.appealState,appeal.gmtModified) from AppealPO appeal where appeal.deleted = 0 and appeal.title like %:search% and appeal.appealType = 0 and appeal.appealVerify = 0 and appeal.gmtModified >=:beginTime "
            + "and appeal.gmtModified <=:endTime")
    List<AppealDTO> listPublicVerifiedDesc(@Param("search") String search, @Param("beginTime") Date begin, @Param("endTime") Date end, Pageable pageable);

    @Query("select new com.xshhope.apply.model.dto.AppealDTO(appeal.id,appeal.title,appeal.appealCount,appeal.goal,appeal.completed,appeal.appealState,appeal.gmtModified) from AppealPO appeal where appeal.deleted = 0 and appeal.title like %:search% and appeal.appealType = 1 and appeal.appealVerify = 1")
    List<AppealDTO> listPersonUnVerifiedOnlyDesc(@Param("search") String search, Pageable pageable);

    @Query("select new com.xshhope.apply.model.dto.AppealDTO(appeal.id,appeal.title,appeal.appealCount,appeal.goal,appeal.completed,appeal.appealState,appeal.gmtModified) from AppealPO appeal where appeal.deleted = 0 and appeal.title like %:search% and appeal.appealType = 1 and appeal.appealVerify = 1 and appeal.gmtModified >=:beginTime "
            + "and appeal.gmtModified <=:endTime")
    List<AppealDTO> listPersonUnVerifiedDesc(@Param("search") String search, @Param("beginTime") Date begin, @Param("endTime") Date end, Pageable pageable);

    @Query("select new com.xshhope.apply.model.dto.AppealDTO(appeal.id,appeal.title,appeal.appealCount,appeal.goal,appeal.completed,appeal.appealState,appeal.gmtModified) from AppealPO appeal where appeal.deleted = 0 and appeal.title like %:search% and appeal.appealType = 0 and appeal.appealVerify = 1")
    List<AppealDTO> listPublicUnVerifiedOnlyDesc(@Param("search") String search, Pageable pageable);

    @Query("select new com.xshhope.apply.model.dto.AppealDTO(appeal.id,appeal.title,appeal.appealCount,appeal.goal,appeal.completed,appeal.appealState,appeal.gmtModified) from AppealPO appeal where appeal.deleted = 0 and appeal.title like %:search% and appeal.appealType = 0 and appeal.appealVerify = 1 and appeal.gmtModified >=:beginTime "
            + "and appeal.gmtModified <=:endTime")
    List<AppealDTO> listPublicUnVerifiedDesc(@Param("search") String search, @Param("beginTime") Date begin, @Param("endTime") Date end, Pageable pageable);

    @Query("select count(1) from AppealPO appeal where appeal.deleted = 0 and appeal.title like %:search% and appeal.appealType = 1 and appeal.appealVerify = 0")
    Long countPersonVerifiedOnly(@Param("search") String search);

    @Query("select count(1) from AppealPO appeal where appeal.deleted = 0 and appeal.title like %:search% and appeal.appealType = 1 and appeal.appealVerify = 0 and appeal.gmtCreated >=:beginTime and appeal.gmtCreated <=:endTime")
    Long countPersonVerified(String search, Date beginTime, Date endTime);

    @Query("select count(1) from AppealPO appeal where appeal.deleted = 0 and appeal.title like %:search% and appeal.appealType = 1 and appeal.appealVerify = 1")
    Long countPersonUnVerifiedOnly(@Param("search") String search);

    @Query("select count(1) from AppealPO appeal where appeal.deleted = 0 and appeal.title like %:search% and appeal.appealType = 1 and appeal.appealVerify = 1 and appeal.gmtCreated >=:beginTime and appeal.gmtCreated <=:endTime")
    Long countPersonUnVerified(@Param("search") String search, @Param("beginTime") Date begin, @Param("endTime") Date end);

    @Query("select count(1) from AppealPO appeal where appeal.deleted = 0 and appeal.title like %:search% and appeal.appealType = 0 and appeal.appealVerify = 0")
    Long countPublicVerifiedOnly(@Param("search") String search);

    @Query("select count(1) from AppealPO appeal where appeal.deleted = 0 and appeal.title like %:search% and appeal.appealType = 0 and appeal.appealVerify = 0 and appeal.gmtCreated >=:beginTime and appeal.gmtCreated <=:endTime")
    Long countPublicVerified(@Param("search") String search, @Param("beginTime") Date begin, @Param("endTime") Date end);

    @Query("select count(1) from AppealPO appeal where appeal.deleted = 0 and appeal.title like %:search% and appeal.appealType = 0 and appeal.appealVerify = 1")
    Long countPublicUnVerifiedOnly(@Param("search") String search);

    @Query("select count(1) from AppealPO appeal where appeal.deleted = 0 and appeal.title like %:search% and appeal.appealType = 0 and appeal.appealVerify = 1 and appeal.gmtCreated >=:beginTime and appeal.gmtCreated <=:endTime")
    Long countPublicUnVerified(@Param("search") String search, @Param("beginTime") Date begin, @Param("endTime") Date end);

    @Query("select new com.xshhope.apply.model.dto.AppealApplyDTO(appeal, apply) from AppealPO appeal, ApplyPO apply where appeal.deleted = 0 and appeal.applyId = apply.id and apply.userName like %:search%")
    List<AppealApplyDTO> listDonateByReceiverOnly(@Param("search") String search, Pageable pageable);

    @Query("select new com.xshhope.apply.model.dto.AppealApplyDTO(appeal, apply) from AppealPO appeal, ApplyPO apply where appeal.deleted = 0 and appeal.applyId = apply.id and apply.title like %:search%")
    List<AppealApplyDTO> listDonateByProjectOnly(@Param("search") String search, Pageable pageable);

    @Query("select new com.xshhope.apply.model.dto.AppealApplyDTO(appeal, apply) from AppealPO appeal, ApplyPO apply where appeal.deleted = 0 and appeal.applyId = apply.id and (apply.title like %:search% or apply.userName like %:search%)")
    List<AppealApplyDTO> listDonateOnly(@Param("search") String search, Pageable pageable);

    @Query("select new com.xshhope.apply.model.dto.AppealApplyDTO(appeal, apply) from AppealPO appeal, ApplyPO apply where appeal.deleted = 0 and appeal.applyId = apply.id and apply.userName like %:search% and appeal.gmtCreated >=:beginTime and appeal.gmtCreated <=:endTime")
    List<AppealApplyDTO> listDonateByReceiver(@Param("search") String search, @Param("beginTime") Date begin, @Param("endTime") Date end, Pageable pageable);

    @Query("select new com.xshhope.apply.model.dto.AppealApplyDTO(appeal, apply) from AppealPO appeal, ApplyPO apply where appeal.deleted = 0 and appeal.applyId = apply.id and apply.title like %:search% and appeal.gmtCreated >=:beginTime and appeal.gmtCreated <=:endTime")
    List<AppealApplyDTO> listDonateByProject(@Param("search") String search, @Param("beginTime") Date begin, @Param("endTime") Date end, Pageable pageable);

    @Query("select new com.xshhope.apply.model.dto.AppealApplyDTO(appeal, apply) from AppealPO appeal, ApplyPO apply where appeal.deleted = 0 and appeal.applyId = apply.id and (apply.title like %:search% or apply.userName like %:search%) and appeal.gmtCreated >=:beginTime and appeal.gmtCreated <=:endTime")
    List<AppealApplyDTO> listDonate(@Param("search") String search, @Param("beginTime") Date begin, @Param("endTime") Date end, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update AppealPO appeal set appeal.appealState =:state, appeal.goal =:goal, appeal.title = :title, appeal.gmtModified = :date where appeal.id = :id")
    void update(@Param("state") Integer applyState, @Param("goal") Double goal, @Param("title") String title, @Param("date") Date date, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update AppealPO appeal set appeal.userName =:userName, appeal.userId =:id, appeal.appealVerify = 0 where appeal.id in :ids")
    void approve(@Param("userName") String userName, @Param("id") Long id, @Param("ids") List<Long> ids);

    @Modifying
    @Transactional
    @Query("update AppealPO appeal set appeal.userName =:userName, appeal.userId =:id, appeal.appealVerify = 1 where appeal.id in :ids")
    void deny(@Param("userName") String userName, @Param("id") Long id, @Param("ids") List<Long> ids);

    @Modifying
    @Transactional
    @Query("update AppealPO appeal set appeal.userName =:userName, appeal.userId =:id, appeal.appealVerify = 0 where appeal.id = :appealId")
    void approve(@Param("userName") String userName, @Param("id") Long id, @Param("appealId") Long appealId);

    @Modifying
    @Transactional
    @Query("update AppealPO appeal set appeal.userName =:userName, appeal.userId =:id, appeal.appealVerify = 1 where appeal.id = :appealId")
    void deny(@Param("userName") String userName, @Param("id") Long id, @Param("appealId") Long appealId);

    @Modifying
    @Transactional
    @Query("update AppealPO appeal set appeal.gmtModified =:gmtModify, appeal.deleted = 1 where appeal.id = :appealId")
    void delete(@Param("appealId") Long id, @Param("gmtModify") Date date);

//    @Query("select new com.xshhope.apply.model.dto.AppealDTO(appeal.id,appeal.title,appeal.appealCount,appeal.goal,appeal.completed,appeal.appealState,appeal.gmtModified) from AppealPO appeal where appeal.deleted = 0 and appeal.title like %:search%  and appeal.userId = :userId")
    @Query("select new com.xshhope.apply.model.dto.AppealApplyDTO(appeal, apply) from AppealPO appeal, ApplyPO apply where appeal.deleted = 0 and appeal.applyId = apply.id and (apply.title like %:search% or apply.userName like %:search%) and appeal.userId = :userId")
    List<AppealApplyDTO> getAppealByUser(@Param("userId") Long userId, @Param("search") String search, Pageable pageable);

    @Query("from AppealPO appeal where appeal.deleted = 0 and appeal.appealVerify = 0 and (appeal.title like %:search% or appeal.content like %:search% ) and appeal.appealState = 1 order by :orderField desc")
    List<AppealPO> listCompleted(@Param("search") String search, @Param("orderField") String orderField, Pageable pageable);

    @Query("from AppealPO appeal where appeal.deleted = :deleted and appeal.appealVerify = 0 and appeal.appealState = :state")
    List<AppealPO> listAppeals(@Param("deleted") Integer deleted, @Param("state") Integer state);

    @Query(nativeQuery = true, value = "select * from dt_appeal where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(gmt_created)")
    List<AppealPO> getLastWeek();

    @Query("from AppealPO appeal where appeal.applyId = :applyId")
    AppealPO getAppealByApplyId(@Param("applyId") Long applyId);

    @Query(nativeQuery = true, value = "select * from dt_appeal appeal where is_deleted = 0 and appeal_state = 0 order by gmt_created desc limit 0,6")
    List<AppealPO> pageDetail();
}
