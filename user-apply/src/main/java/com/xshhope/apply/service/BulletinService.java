package com.xshhope.apply.service;




import com.xshhope.model.apply.BulletinPO;

import java.util.List;

/**
 * @author xshhope
 */
public interface BulletinService {
    List<BulletinPO> homeBulletins(String search, String orderField, String orderDirection, int page, int size);

    List<BulletinPO> listBulletins(String search, String orderField, String orderDirection, int page, int size);

    Long countBulletin();

    BulletinPO getBulletinById(Long id);

    void deleteBulletin(Long userId, List<Long> bulletinids);

    BulletinPO showLatestBulletin();

    List<BulletinPO> listAminBulletins(String search, int page, int size);

    void offineBulletin(Long userId, Long id);

    void onlineBulletin(Long id, Long id1);

    BulletinPO addBulletin(BulletinPO bulletinPO);
}
