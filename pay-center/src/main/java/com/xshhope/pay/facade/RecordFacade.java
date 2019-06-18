package com.xshhope.pay.facade;

import com.google.common.collect.Lists;
import com.xshhope.common.utils.AppUserUtil;
import com.xshhope.common.utils.Langs;
import com.xshhope.common.utils.Pager;
import com.xshhope.model.user.AppUser;
import com.xshhope.pay.feign.UserClient;
import com.xshhope.pay.model.dto.DonateListDTO;
import com.xshhope.pay.model.dto.RecordDTO;
import com.xshhope.pay.model.dto.RecordListDTO;
import com.xshhope.model.pay.RecordPO;
import com.xshhope.pay.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xshhope
 */
@Slf4j
@Service
public class RecordFacade {

    @Autowired
    private RecordService recordService;

    @Autowired
    private UserClient userClient;

    public Pager<RecordDTO> latestDontate(int page, int size, String search, String orderField, String orderDirection) {
        List<RecordDTO> data = Lists.newArrayList();
        List<RecordPO> recordPOS = recordService.listRecord(search, orderField, orderDirection, page, size);
        Long total = recordService.countRecords();

        recordPOS.forEach(x -> {
            AppUser donater = userClient.findUserByIdd(x.getCreatedUserId());
            AppUser receiver = userClient.findUserByIdd(x.getReceiver());
            RecordDTO recordDTO = new RecordDTO();
            recordDTO.setDonater(donater.getUsername());
            recordDTO.setReceiver(receiver.getUsername());
            recordDTO.setMoney(x.getMoney());
            recordDTO.setProjectName(x.getTitle());
            recordDTO.setGmtCreated(x.getGmtCreated());

            data.add(recordDTO);
        });
        Pager<RecordDTO> pager = new Pager<RecordDTO>();
        pager.setData(data);
        pager.setTotalElements(total);
        return pager;
    }

    public Pager<RecordListDTO> donateBoard(int page, int size, String search, String orderField, String orderDirection) {
        List<RecordListDTO> data = recordService.recordListDTO(search, orderField, orderDirection, page, size);
        Long total = Long.valueOf(data.size());

        Pager<RecordListDTO> pager = new Pager<RecordListDTO>();
        pager.setData(data);
        pager.setTotalElements(total);
        return pager;
    }

    public Pager<DonateListDTO> listDonate(int page, int size, Integer type, String search, String begin, String end, String desc) {

        if (Langs.isNull(type)) {
            type = 0;
        }
        List<DonateListDTO> appealDTOS = recordService.listDonate(page, size, type, search, begin, end, desc);
        Long total = Long.valueOf(appealDTOS.size());

        Pager<DonateListDTO> pager = new Pager<DonateListDTO>();
        pager.setData(appealDTOS);
        pager.setTotalElements(total);
        return pager;
    }

    public Pager<DonateListDTO> getDontateByUser(int page, int size, String search, String begin, String end, String desc) {
        AppUser user = AppUserUtil.getLoginAppUser();

        List<DonateListDTO> appealDTOS = recordService.getDontateByUser(user.getId(), page, size, search, begin, end, desc);
        Long total = recordService.countDontateByUser(user.getId());

        Pager<DonateListDTO> pager = new Pager<DonateListDTO>();
        pager.setData(appealDTOS);
        pager.setTotalElements(total);
        return pager;
    }
}
