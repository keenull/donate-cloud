package com.xshhope.pay.repository;

import com.xshhope.pay.model.dto.DonateListDTO;
import com.xshhope.pay.model.dto.RecordListDTO;
import com.xshhope.model.pay.RecordPO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author xshhope
 */
@Repository
public interface RecordRepository extends JpaRepository<RecordPO, Long> {

    @Query("from RecordPO record where record.deleted = 0 and (record.title like %:search% or record.receiver like %:search% or record.createdUserName like %:search% or record.receiverName like %:search% ) order by :orderField desc")
    List<RecordPO> listRecordDESC(@Param("search") String search, @Param("orderField") String orderField, Pageable pageable);

    @Query("from RecordPO record where record.deleted = 0 and (record.title like %:search% or record.receiver like %:search% or record.createdUserName like %:search% or record.receiverName like %:search% ) order by :orderField asc")
    List<RecordPO> listRecordASC(@Param("search") String search, @Param("orderField") String orderField, Pageable pageable);

    @Query("select count(1) from RecordPO record where record.deleted = 0")
    Long countRecords();

    @Query("select new com.xshhope.pay.model.dto.RecordListDTO(record.id,record.createdUserName,sum(record.money),record.gmtCreated) from RecordPO record where record.deleted = 0 group by record.createdUserId order by record.gmtCreated desc, sum(record.money) desc")
    List<RecordListDTO> recordListDTO();

    @Query("select new com.xshhope.pay.model.dto.RecordListDTO(record.id,record.createdUserName,sum(record.money),record.gmtCreated) from RecordPO record where record.deleted = 0 and (record.createdUserName like %:search%)  group by record.createdUserId order by :orderField desc, record.gmtCreated desc, sum(record.money) desc")
    List<RecordListDTO> recordListDTOPageDesc(@Param("orderField") String orderField, @Param("search") String search, Pageable pageable);

    @Query("select new com.xshhope.pay.model.dto.RecordListDTO(record.id,record.createdUserName,sum(record.money),record.gmtCreated) from RecordPO record where record.deleted = 0 and (record.createdUserName like %:search%)  group by record.createdUserId order by :orderField asc, record.gmtCreated desc, sum(record.money) desc")
    List<RecordListDTO> recordListDTOPageAsc(@Param("orderField") String orderField, @Param("search") String search, Pageable pageable);

    @Query("select new com.xshhope.pay.model.dto.DonateListDTO(record.id,record.createdUserName,record.receiverName,record.title,record.money,record.gmtCreated) from RecordPO record where record.deleted = 0 and (record.createdUserName like %:search%) order by record.gmtCreated desc")
    List<DonateListDTO> listDonateDesc(@Param("search") String search, Pageable pageable);

    @Query("select new com.xshhope.pay.model.dto.DonateListDTO(record.id,record.createdUserName,record.receiverName,record.title,record.money,record.gmtCreated) from RecordPO record where record.deleted = 0 and (record.createdUserName like %:search%) order by record.gmtCreated desc")
    List<DonateListDTO> listDonateAsc(@Param("search") String search, Pageable pageable);

    @Query("select new com.xshhope.pay.model.dto.DonateListDTO(record.id,record.createdUserName,record.receiverName,record.title,record.money,record.gmtCreated) from RecordPO record where record.deleted = 0 and record.receiverName like %:search% and record.gmtCreated >=:beginTime "
            + "and record.gmtCreated <=:endTime")
    List<DonateListDTO> listDonateByReceiver(@Param("search") String search, @Param("beginTime") Date begin, @Param("endTime") Date end, Pageable pageable);

    @Query("select new com.xshhope.pay.model.dto.DonateListDTO(record.id,record.createdUserName,record.receiverName,record.title,record.money,record.gmtCreated) from RecordPO record where record.deleted = 0 and record.title like %:search% and record.gmtCreated >=:beginTime "
            + "and record.gmtCreated <=:endTime")
    List<DonateListDTO> listDonateByProject(@Param("search") String search, @Param("beginTime") Date begin, @Param("endTime") Date end, Pageable pageable);

    @Query("select new com.xshhope.pay.model.dto.DonateListDTO(record.id,record.createdUserName,record.receiverName,record.title,record.money,record.gmtCreated) from RecordPO record where record.deleted = 0 and (record.title like %:search% or record.receiverName like %:search% ) and record.gmtCreated >=:beginTime and record.gmtCreated <=:endTime")
    List<DonateListDTO> listDonate(@Param("search") String search, @Param("beginTime") Date begin, @Param("endTime") Date end, Pageable pageable);

    @Query("select new com.xshhope.pay.model.dto.DonateListDTO(record.id,record.createdUserName,record.receiverName,record.title,record.money,record.gmtCreated) from RecordPO record where record.deleted = 0 and record.receiverName like %:search%")
    List<DonateListDTO> listDonateByReceiverOnly(@Param("search") String search, Pageable pageable);

    @Query("select new com.xshhope.pay.model.dto.DonateListDTO(record.id,record.createdUserName,record.receiverName,record.title,record.money,record.gmtCreated) from RecordPO record where record.deleted = 0 and record.title like %:search%")
    List<DonateListDTO> listDonateByProjectOnly(@Param("search") String search, Pageable pageable);

    @Query("select new com.xshhope.pay.model.dto.DonateListDTO(record.id,record.createdUserName,record.receiverName,record.title,record.money,record.gmtCreated) from RecordPO record where record.deleted = 0 and (record.title like %:search% or record.receiverName like %:search% )")
    List<DonateListDTO> listDonateOnly(@Param("search") String search, Pageable pageable);

    @Query("select count(1) from RecordPO record where record.deleted = 0 and record.receiverName like %:search%")
    Long countDonateByReceiverOnly(@Param("search") String search);

    @Query("select count(1) from RecordPO record where record.deleted = 0 and record.title like %:search%")
    Long countDonateByProjectOnly(@Param("search") String search);

    @Query("select count(1) from RecordPO record where record.deleted = 0 and (record.title like %:search% or record.receiverName like %:search% )")
    Long countDonateOnly(@Param("search") String search);

    @Query("select count(1) from RecordPO record where record.deleted = 0 and record.receiverName like %:search% and record.gmtCreated >=:beginTime and record.gmtCreated <=:endTime")
    Long countDonateByReceiver(@Param("search") String search, @Param("beginTime") Date begin, @Param("endTime") Date end);

    @Query("select count(1) from RecordPO record where record.deleted = 0 and record.title like %:search% and record.gmtCreated >=:beginTime and record.gmtCreated <=:endTime")
    Long countDonateByProject(@Param("search") String search, @Param("beginTime") Date begin, @Param("endTime") Date end);

    @Query("select count(1) from RecordPO record where record.deleted = 0 and (record.title like %:search% or record.receiverName like %:search% ) and record.gmtCreated >=:beginTime and record.gmtCreated <=:endTime")
    Long countDonate(@Param("search") String search, @Param("beginTime") Date begin, @Param("endTime") Date end);

    @Query("select new com.xshhope.pay.model.dto.DonateListDTO(record.id,record.createdUserName,record.receiverName,record.title,record.money,record.gmtCreated) from RecordPO record where record.deleted = 0 and record.createdUserId = :userId order by record.gmtCreated desc")
    List<DonateListDTO> getDontateByUser(@Param("userId") Long userId, Pageable pageable);

    @Query("select count(1) from RecordPO record where record.deleted = 0 and record.createdUserId = :userId")
    Long countDontateByUser(@Param("userId") Long userId);
}
