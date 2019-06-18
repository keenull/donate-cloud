package com.xshhope.apply.facade;

import com.google.common.collect.Lists;
import com.xshhope.apply.convert.BulletinConvert;
import com.xshhope.apply.feign.UserClient;
import com.xshhope.apply.model.dto.BulletinDTO;
import com.xshhope.apply.model.dto.BulletinListDTO;
import com.xshhope.apply.model.param.BulletinParam;
import com.xshhope.apply.service.BulletinService;
import com.xshhope.common.support.ActionResult;
import com.xshhope.common.support.ActionTemplate;
import com.xshhope.common.utils.AppUserUtil;
import com.xshhope.common.utils.Langs;
import com.xshhope.common.utils.Pager;
import com.xshhope.model.apply.BulletinPO;
import com.xshhope.model.user.AppUser;
import com.xshhope.model.user.LoginAppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @author xshhope
 */
@Service
public class BulletinFacade {

    @Autowired
    private BulletinService bulletinService;

    @Autowired
    private UserClient userClient;

    public Pager<BulletinDTO> homeBulletin(int page, int size, String search, String orderField, String orderDirection) {
        List<BulletinDTO> data = Lists.newArrayList();
        List<BulletinPO> bulletinPOS = bulletinService.homeBulletins(search, orderField, orderDirection, page, size);
        Long total = bulletinService.countBulletin();

        bulletinPOS.forEach(x -> {
            BulletinDTO bulletinDTO = new BulletinDTO();
            bulletinDTO.setId(x.getId());
            bulletinDTO.setCreatedUserId(x.getCreatedUserId());
            bulletinDTO.setModifiedUserId(x.getModifiedUserId());
            bulletinDTO.setTitle(x.getTitle());
            bulletinDTO.setContent(x.getContent());
            bulletinDTO.setGmtCreated(x.getGmtCreated());
            bulletinDTO.setGmtModified(x.getGmtModified());

            data.add(bulletinDTO);
        });
        Pager<BulletinDTO> pager = new Pager<BulletinDTO>();
        pager.setData(data);
        pager.setTotalElements(total);
        return pager;
    }

    public Pager<BulletinListDTO> listBulletin(int page, int size, String search) {
        List<BulletinListDTO> data = Lists.newArrayList();
        List<BulletinPO> bulletinPOS = bulletinService.listAminBulletins(search, page, size);
        Long total = bulletinService.countBulletin();

        bulletinPOS.forEach(x -> {
            AppUser createdUser = userClient.findUserByIdd(x.getCreatedUserId());
            AppUser modifiedUser = userClient.findUserByIdd(x.getModifiedUserId());

            BulletinListDTO bulletinDTO = new BulletinListDTO();
            bulletinDTO.setId(x.getId());
            bulletinDTO.setCreatedUser(createdUser.getNickname());
            bulletinDTO.setModifiedUser(modifiedUser.getNickname());
            bulletinDTO.setTitle(x.getTitle());
            bulletinDTO.setContent(x.getContent());
            bulletinDTO.setGmtCreated(x.getGmtCreated());
            bulletinDTO.setGmtModified(x.getGmtModified());
            bulletinDTO.setState(x.getShow());
            data.add(bulletinDTO);
        });
        Pager<BulletinListDTO> pager = new Pager<BulletinListDTO>();
        pager.setData(data);
        pager.setTotalElements(total);
        return pager;
    }

    public ActionResult getBulletinById(Long id) {
        BulletinPO bulletinPO = bulletinService.getBulletinById(id);
        if (bulletinPO == null) {
            return ActionTemplate.toNack("查询失败");
        }
        return ActionTemplate.toAck("查询成功", new BulletinConvert().apply(bulletinPO));
    }

    @Transactional
    public ActionResult deleteBulletin(List<Long> bulletinids) {
        AppUser user = AppUserUtil.getLoginAppUser();
        if (!Langs.isEmpty(bulletinids)) {
            bulletinService.deleteBulletin(user.getId(), bulletinids);
            return ActionTemplate.toAck("删除成功");
        }
        return ActionTemplate.toNack("删除失败");
    }

    @Transactional
    public ActionResult saveBulletin(BulletinParam param) {
        AppUser user = AppUserUtil.getLoginAppUser();
        BulletinPO bulletinPO = bulletinService.getBulletinById(param.getId());
        bulletinPO.setModifiedUserId(user.getId());
        bulletinPO.setTitle(param.getTitle());
        bulletinPO.setContent(param.getContent());
        bulletinPO.setGmtModified(new Date());

        bulletinService.addBulletin(bulletinPO);
        if (Langs.isEmpty(bulletinPO.getId())) {
            return ActionTemplate.toNack("保存失败");
        }
        return ActionTemplate.toAck("保存成功");
    }

    public BulletinPO showLatestBulletin() {
        BulletinPO bulletinPO = bulletinService.showLatestBulletin();
        if (bulletinPO == null) {
            return new BulletinPO();
        }
        return bulletinPO;
    }

    @Transactional
    public ActionResult offineBulletin(Long id) {
        LoginAppUser user = AppUserUtil.getLoginAppUser();
        bulletinService.offineBulletin(user.getId(), id);
        return ActionTemplate.toAck("下架公告成功");
    }

    @Transactional
    public ActionResult onlineBulletin(Long id) {
        LoginAppUser user = AppUserUtil.getLoginAppUser();
        bulletinService.onlineBulletin(user.getId(), id);
        return ActionTemplate.toAck("下架公告成功");
    }

    @Transactional
    public ActionResult addBulletin(BulletinParam param) {
        AppUser user = AppUserUtil.getLoginAppUser();
        BulletinPO bulletinPO = new BulletinPO();
        bulletinPO.setShow(1);
        bulletinPO.setContent(param.getContent());
        bulletinPO.setCreatedUserId(user.getId());
        bulletinPO.setModifiedUserId(user.getId());
        bulletinPO.setDeleted(0);
        bulletinPO.setTitle(param.getTitle());
        bulletinPO.setGmtCreated(new Date());
        bulletinPO.setGmtModified(new Date());


        bulletinService.addBulletin(bulletinPO);
        return ActionTemplate.toAck("添加公告成功");
    }
}
