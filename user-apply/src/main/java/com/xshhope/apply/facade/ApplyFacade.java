package com.xshhope.apply.facade;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xshhope.apply.feign.LogClient;
import com.xshhope.apply.model.dto.AppealVO;
import com.xshhope.apply.model.dto.ApplyDTO;
import com.xshhope.apply.model.dto.PersonApplyDTO;
import com.xshhope.apply.model.param.ApplyEditParam;
import com.xshhope.apply.model.param.ApplyParam;
import com.xshhope.apply.service.AppealService;
import com.xshhope.apply.service.ApplyService;
import com.xshhope.common.support.ActionResult;
import com.xshhope.common.support.ActionTemplate;
import com.xshhope.common.utils.AppUserUtil;
import com.xshhope.common.utils.Langs;
import com.xshhope.common.utils.Pager;
import com.xshhope.common.utils.UploadFileUtil;
import com.xshhope.model.apply.AppealPO;
import com.xshhope.model.apply.ApplyPO;
import com.xshhope.model.log.Log;
import com.xshhope.model.user.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * @author xshhope
 */
@Slf4j
@Service
public class ApplyFacade {

    @Autowired
    private ApplyService applyService;

    @Autowired
    private AppealService appealService;

    @Autowired
    private LogClient logClient;

    public ActionResult uploadFiles(MultipartFile[] multipartFile) {

        return ActionTemplate.toAck("保存文件成功", UploadFileUtil.uploadFiles(multipartFile));
    }

    public Pager<ApplyDTO> listApply(int page, int size, String search, String orderField, String orderDirection) {
        List<ApplyDTO> data = Lists.newArrayList();
        List<ApplyPO> applyPOS = applyService.listApplys(search, orderField, orderDirection, page, size);
        Long total = applyService.countApplys();

        applyPOS.forEach(x -> {
            ApplyDTO applyDTO = new ApplyDTO();
            applyDTO.setId(x.getId());
            applyDTO.setUserId(x.getUserId());
            applyDTO.setTitle(x.getTitle());
            applyDTO.setContent(x.getContent());
            applyDTO.setApplyType(x.getApplyType());
            applyDTO.setApplyState(x.getApplyState());
            applyDTO.setGoal(x.getGoal());
            applyDTO.setCompleted(x.getCompleted());
            applyDTO.setGmtCreated(x.getGmtCreated());
            applyDTO.setGmtModified(x.getGmtModified());
            data.add(applyDTO);
        });
        Pager<ApplyDTO> pager = new Pager<ApplyDTO>();
        pager.setData(data);
        pager.setTotalElements(total);
        return pager;
    }

    public ApplyPO getApplyById(Long id) {
        ApplyPO applyPO = applyService.getApplyById(id);
        if (applyPO == null) {
            return new ApplyPO();
        }
        return applyPO;
    }

    @Transactional
    public ActionResult save(ApplyParam param) {
        AppUser user = AppUserUtil.getLoginAppUser();
        ApplyPO applyPO = saveApplyPO(user, param);

        saveApplyLog(user.getUsername(), "申请保存");

        AppealPO appealPO = appealService.saveAppealPO(applyPO);

        if (Langs.isEmpty(applyPO.getId()) || Langs.isEmpty(appealPO.getId())) {
            return ActionTemplate.toNack("保存失败");
        }
        return ActionTemplate.toAck("保存成功");
    }


    private ApplyPO saveApplyPO(AppUser userPO, ApplyParam param) {
        ApplyPO applyPO = new ApplyPO();
        applyPO.setDeleted(0);
        applyPO.setGmtCreated(new Date());
        applyPO.setApplyState(0);

        applyPO.setGmtModified(new Date());
        applyPO.setUserId(userPO.getId());
        applyPO.setGoal(param.getGoal());
        applyPO.setCompleted(1);
        applyPO.setUserName(userPO.getUsername());
        applyPO.setTitle(param.getTitle());
        applyPO.setContent(param.getContent());
        applyPO.setApplyType(param.getApplyType());
        applyPO.setFile(param.getFile());

        /*List<String> uploadFiles = uploadFileUtil.uploadFiles(param.getFiles());

        applyPO.setFile(uploadFiles.toString());*/
        applyPO = applyService.updateApply(applyPO);

        return applyPO;
    }

    @Transactional
    public ActionResult update(ApplyEditParam param) {
        applyService.update(param);
        appealService.update(param);
        return ActionTemplate.toAck("更新成功");
    }

    @Transactional
    public void delete(Long id) {
        AppUser user = AppUserUtil.getLoginAppUser();
        applyService.delete(id, new Date());
        appealService.delete(id, new Date());
    }

    /**
     * 获取最近7天
     * @return
     */
    public List<AppealVO> getLastWeek() {
        Map<Long, Integer> data = Maps.newHashMap();
        TreeMap<Long, Integer> data2 = Maps.newTreeMap();
        // 最近7天记录
        List<AppealPO> list = appealService.getLastWeek();

        list.forEach(x -> {

            for (Integer i = 0; i < 7; i++) {
                Long aaa = getStartTimeOfDay(System.currentTimeMillis() - i * 86400000);
                Long bbb = getEndTimeOfDay(System.currentTimeMillis() - i * 86400000);
                Long ccc = x.getGmtCreated().getTime();
                Long target = getStartTimeOfDay(x.getGmtCreated().getTime());
                if (ccc > aaa && ccc < bbb) {
                    if (data.containsKey(target)) {
                        data.put(target, (data.get(target)) + 1);
                    } else {
                        data.put(target, 0);
                    }
                }
            }
        });


        for (int j = 0; j < 7; j++){
            data2.put(getStartTimeOfDay(System.currentTimeMillis() - j * 86400000), 0);
        }

        for (Map.Entry<Long, Integer> entry : data.entrySet()) {
            if (data2.containsKey(entry.getKey())){
                int result = entry.getValue() + 1;
                data2.put(entry.getKey(),result);
            }
        }

        List<AppealVO> appealVOS = Lists.newArrayList();

        for (Map.Entry<Long, Integer> entry : data2.entrySet()) {
            AppealVO appealVO = new AppealVO();
            appealVO.setTime(entry.getKey());
            appealVO.setCount(entry.getValue());
            appealVOS.add(appealVO);
        }
        return appealVOS;
    }

    /**
     * 昨天0点
     * @param interval
     * @return
     */
    public Long lastZero(Integer interval){
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) - interval, 0, 0, 0);
        return calendar.getTime().getTime();
    }

    private long getStartTimeOfDay(Long now) {
        String tz = "GMT+8";
        TimeZone curTimeZone = TimeZone.getTimeZone(tz);
        Calendar calendar = Calendar.getInstance(curTimeZone);
        calendar.setTimeInMillis(now);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    private long getEndTimeOfDay(Long now) {
        String tz = "GMT+8";
        TimeZone curTimeZone = TimeZone.getTimeZone(tz);
        Calendar calendar = Calendar.getInstance(curTimeZone);
        calendar.setTimeInMillis(now);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }

    /**
     * 昨天23点
     * @param interval
     * @return
     */
    public Long todayZero(Integer interval){
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) - interval, 23, 59, 59);
        return calendar.getTime().getTime();
    }

    public Pager<PersonApplyDTO> getApplyByUser(int page, int size, String search, String begin, String end, String desc) {
        AppUser user = AppUserUtil.getLoginAppUser();

        List<PersonApplyDTO> applyDTOS = applyService.getApplyByUser(user.getId(), page, size, search, begin, end, desc);
        Long total = Long.valueOf(applyDTOS.size());

        Pager<PersonApplyDTO> pager = new Pager<PersonApplyDTO>();
        pager.setData(applyDTOS);
        pager.setTotalElements(total);
        return pager;
    }

    /**
     * 申请保存日志
     *
     * @param name
     */
    private void saveApplyLog(String name, String remark) {
        log.info("{}申请保存", name);
        // 异步
        CompletableFuture.runAsync(() -> {
            try {
                Log log = Log.builder().username(name).module("申请保存").remark(remark).createTime(new Date())
                        .build();
                logClient.save(log);
            } catch (Exception e) {
                // do nothing
            }

        });
    }
}
