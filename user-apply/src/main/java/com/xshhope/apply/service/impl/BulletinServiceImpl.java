package com.xshhope.apply.service.impl;

import com.google.common.collect.Lists;
import com.xshhope.apply.repository.BulletinRepository;
import com.xshhope.apply.service.BulletinService;
import com.xshhope.model.apply.BulletinPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author xshhope
 */
@Service
public class BulletinServiceImpl implements BulletinService {

    @Autowired
    private BulletinRepository bulletinRepository;

    @Override
    public List<BulletinPO> homeBulletins(String search, String orderField, String orderDirection, int page, int size) {
        Sort.Order order = null;
        if ("desc".equalsIgnoreCase(orderDirection)) {
            order = new Sort.Order(Sort.Direction.DESC, orderField);
        } else if ("asc".equalsIgnoreCase(orderDirection)) {
            order = new Sort.Order(Sort.Direction.ASC, orderField);
        }

        PageRequest pageReq = PageRequest.of(page - 1, size, Sort.by(order));

        List<BulletinPO> bulletinPOS = orderDirection.equalsIgnoreCase("desc") ? this.bulletinRepository.homeBulletinsDESC(search, orderField, pageReq) : bulletinRepository.homeBulletinsASC(search, orderField, pageReq);
        return Optional.of(bulletinPOS).orElse(Lists.newArrayList());
    }

    @Override
    public List<BulletinPO> listBulletins(String search, String orderField, String orderDirection, int page, int size) {
        Sort.Order order = null;
        if ("desc".equalsIgnoreCase(orderDirection)) {
            order = new Sort.Order(Sort.Direction.DESC, orderField);
        } else if ("asc".equalsIgnoreCase(orderDirection)) {
            order = new Sort.Order(Sort.Direction.ASC, orderField);
        }

        PageRequest pageReq = PageRequest.of(page - 1, size, Sort.by(order));

        List<BulletinPO> bulletinPOS = orderDirection.equalsIgnoreCase("desc") ? this.bulletinRepository.listBulletinsDESC(search, orderField, pageReq) : bulletinRepository.listBulletinsASC(search, orderField, pageReq);
        return Optional.of(bulletinPOS).orElse(Lists.newArrayList());
    }

    @Override
    public Long countBulletin() {
        return bulletinRepository.countBulletins();
    }

    @Override
    public BulletinPO getBulletinById(Long id) {
        return bulletinRepository.getBulletinById(id);
    }

    @Transactional
    @Override
    public void deleteBulletin(Long userId,List<Long> bulletinids) {
        bulletinRepository.deleteBulletin(userId, bulletinids);
    }

    @Override
    public BulletinPO showLatestBulletin() {
        return bulletinRepository.showLatestBulletin();
    }

    @Override
    public List<BulletinPO> listAminBulletins(String search, int page, int size) {
        List<BulletinPO> bulletinPOS = this.bulletinRepository.listAminBulletins(search, PageRequest.of(page - 1, size));
        return Optional.of(bulletinPOS).orElse(Lists.newArrayList());
    }

    @Transactional
    @Override
    public void offineBulletin(Long userId, Long id) {
        bulletinRepository.offineBulletin(userId, id);
    }

    @Transactional
    @Override
    public void onlineBulletin(Long userId, Long id) {
        bulletinRepository.onlineBulletin(userId, id);
    }

    @Override
    public BulletinPO addBulletin(BulletinPO bulletinPO) {
        return bulletinRepository.save(bulletinPO);
    }
}
