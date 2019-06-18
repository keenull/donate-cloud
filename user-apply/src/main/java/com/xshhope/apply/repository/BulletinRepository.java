package com.xshhope.apply.repository;

import com.xshhope.model.apply.BulletinPO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author xshhope
 */
@Repository
public interface BulletinRepository extends JpaRepository<BulletinPO, Long> {

    @Query("from BulletinPO bulletin where bulletin.deleted = 0 and bulletin.show = 0 and (bulletin.title like %:search% or bulletin.content like %:search% ) order by :orderField desc")
    List<BulletinPO> homeBulletinsDESC(@Param("search") String search, @Param("orderField") String orderField, Pageable pageable);

    @Query("from BulletinPO bulletin where bulletin.deleted = 0 and bulletin.show = 0 and (bulletin.title like %:search% or bulletin.content like %:search% ) order by :orderField asc")
    List<BulletinPO> homeBulletinsASC(@Param("search") String search, @Param("orderField") String orderField, Pageable pageable);

    @Query("from BulletinPO bulletin where bulletin.deleted = 0 and (bulletin.title like %:search% or bulletin.content like %:search% ) order by :orderField asc")
    List<BulletinPO> listBulletinsASC(@Param("search") String search, @Param("orderField") String orderField, Pageable pageable);

    @Query("from BulletinPO bulletin where bulletin.deleted = 0 and (bulletin.title like %:search% or bulletin.content like %:search% ) order by :orderField desc")
    List<BulletinPO> listBulletinsDESC(@Param("search") String search, @Param("orderField") String orderField, Pageable pageable);

    @Query("select count(1) from BulletinPO bulletin where bulletin.deleted = 0")
    Long countBulletins();

    @Query("from BulletinPO bulletin where bulletin.deleted = 0 and bulletin.id =:id")
    BulletinPO getBulletinById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update BulletinPO bulletin set bulletin.deleted = 1,bulletin.modifiedUserId =:modifiedUserId where bulletin.id in :bulletins")
    void deleteBulletin(@Param("modifiedUserId") Long userId, @Param("bulletins") List<Long> bulletinids);

    @Query(nativeQuery = true, value = "from BulletinPO bulletin where bulletin.deleted = 0 and bulletin.show = 0 order by bulletin.gmtModified desc limit 0,1")
    BulletinPO showLatestBulletin();

    @Query("from BulletinPO bulletin where bulletin.deleted = 0 and (bulletin.title like %:search% or bulletin.content like %:search% )")
    List<BulletinPO> listAminBulletins(@Param("search") String search, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update BulletinPO bulletin set bulletin.show = 1,bulletin.modifiedUserId =:modifiedUserId where bulletin.id = :bulletinId")
    void offineBulletin(@Param("modifiedUserId") Long userId, @Param("bulletinId") Long id);

    @Modifying
    @Transactional
    @Query("update BulletinPO bulletin set bulletin.show = 0,bulletin.modifiedUserId =:modifiedUserId where bulletin.id = :bulletinId")
    void onlineBulletin(@Param("modifiedUserId") Long userId, @Param("bulletinId") Long id);
}
