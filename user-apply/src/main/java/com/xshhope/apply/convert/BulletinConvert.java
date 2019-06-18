package com.xshhope.apply.convert;


import com.xshhope.apply.model.dto.BulletinDTO;
import com.xshhope.model.apply.BulletinPO;

/**
 * @author xshhope
 */
public class BulletinConvert implements Convert<BulletinPO, BulletinDTO> {
    @Override
    public BulletinDTO apply(BulletinPO source) {
        BulletinDTO bulletinDTO = new BulletinDTO();
        bulletinDTO.setId(source.getId());
        bulletinDTO.setCreatedUserId(source.getCreatedUserId());
        bulletinDTO.setModifiedUserId(source.getModifiedUserId());
        bulletinDTO.setTitle(source.getTitle());
        bulletinDTO.setContent(source.getContent());
        bulletinDTO.setGmtCreated(source.getGmtCreated());
        bulletinDTO.setState(source.getShow());
        return bulletinDTO;
    }
}
