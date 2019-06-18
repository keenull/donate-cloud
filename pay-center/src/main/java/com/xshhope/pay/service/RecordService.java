package com.xshhope.pay.service;



import com.xshhope.pay.model.dto.DonateListDTO;
import com.xshhope.pay.model.dto.RecordListDTO;
import com.xshhope.model.pay.PayPO;
import com.xshhope.model.pay.RecordPO;

import java.util.List;


/**
 * @author xshhope
 */
public interface RecordService {
    void saveRecordByPay(PayPO payPO);

    List<RecordPO> listRecord(String search, String orderField, String orderDirection, int page, int size);

    Long countRecords();

    List<RecordListDTO> recordListDTO(String search, String orderField, String orderDirection, int page, int size);

    List<DonateListDTO> listDonate(int page, int size, Integer type, String search, String begin, String end, String desc);

    List<DonateListDTO> getDontateByUser(Long id, int page, int size, String search, String begin, String end, String desc);

    Long countDontateByUser(Long id);
}
