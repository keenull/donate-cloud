package com.xshhope.apply.controller;

import com.google.common.collect.Maps;
import com.xshhope.apply.convert.ApplyConvert;
import com.xshhope.apply.facade.AppealFacade;
import com.xshhope.apply.facade.ApplyFacade;
import com.xshhope.apply.model.dto.AppealVO;
import com.xshhope.apply.model.dto.ApplyDTO;
import com.xshhope.apply.model.param.AppealIdParam;
import com.xshhope.apply.model.param.ApplyEditParam;
import com.xshhope.apply.model.param.ApplyParam;
import com.xshhope.common.support.ActionResult;
import com.xshhope.common.support.ActionTemplate;
import com.xshhope.common.utils.Langs;
import com.xshhope.common.utils.Pager;
import com.xshhope.model.apply.ApplyPO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author xshhope
 */
@Api(description = "管理员管理捐款模块")
@RestController
@RequestMapping(value = "/apply")
public class AdminApplyController {

    @Autowired
    private ApplyFacade applyFacade;

    @Autowired
    private AppealFacade appealFacade;

    @ApiOperation(value = "去申请求助页面", notes = "无需权限", produces = "application/json")
    @RequestMapping(value = "/apply-anon/getAllApply", method = RequestMethod.GET)
    public ActionResult getApplys(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                  @RequestParam(value = "size", required = false, defaultValue = "20") int size,
                                  @RequestParam(value = "search", required = false) String search,
                                  @RequestParam(value = "search", required = false, defaultValue = "gmtCreated") String orderField,
                                  @RequestParam(value = "orderDirection", required = false, defaultValue = "desc") String orderDirection) {

        Pager<ApplyDTO> pager = applyFacade.listApply(page, size, search.trim(), orderField, orderDirection);
        Map<String, Object> map = Maps.newHashMap();
        map.put("current", pager.getCurrentPageNumber());
        map.put("size", pager.getPerPageSize());
        map.put("total", pager.getTotalElements());
        map.put("list", pager.getData());
        return ActionTemplate.toAck("查询成功", map);
    }

    @ApiOperation(value = "得到申请详情", notes = "需要权限", produces = "application/json")
    @RequestMapping(value = "/getApply", method = RequestMethod.GET)
    public ActionResult getApply(@RequestParam("id") Long id) {
        if (Langs.isEmpty(id)) {
            return ActionTemplate.toNack("参数不能为空");
        }

        return ActionTemplate.toAck("获取详情成功", new ApplyConvert().apply(applyFacade.getApplyById(id)));
    }

    @ApiOperation(value = "保存申请", notes = "需要权限", produces = "application/json")
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public ActionResult save(@RequestBody ApplyParam param) {
        if (Langs.isEmpty(param) || Langs.isEmpty(param.getContent()) || Langs.isEmpty(param.getTitle())) {
            return ActionTemplate.toNack("参数不能为空");
        }

        return applyFacade.save(param);
    }

    @ApiOperation(value = "申请通过", notes = "需要权限", produces = "application/json")
    @PreAuthorize("hasAuthority('apply:approve')")
    @RequestMapping(value = "/approve", method = RequestMethod.POST)
    public ActionResult<Boolean> batchApprove(@RequestBody AppealIdParam param) {
        if (Langs.isEmpty(param)) {
            return ActionTemplate.toNack("参数不能为空");
        }

        return appealFacade.approve(param.getIds());
    }

    @ApiOperation(value = "申请拒绝", notes = "需要权限", produces = "application/json")
    @PreAuthorize("hasAuthority('apply:deny')")
    @RequestMapping(value = "/deny", method = RequestMethod.POST)
    public ActionResult<Boolean> batchDeny(@RequestBody AppealIdParam param) {
        if (Langs.isEmpty(param)) {
            return ActionTemplate.toNack("参数不能为空");
        }

        return appealFacade.deny(param.getIds());
    }

    @ApiOperation(value = "申请通过", notes = "需要权限", produces = "application/json")
    @PreAuthorize("hasAuthority('apply:approve')")
    @GetMapping("/approve/{id}")
    public ActionResult<Boolean> approve(@PathVariable("id") Long id) {
        if (Langs.isEmpty(id)) {
            return ActionTemplate.toNack("参数不能为空");
        }

        return appealFacade.approve(id);
    }

    @ApiOperation(value = "申请拒绝", notes = "需要权限", produces = "application/json")
    @PreAuthorize("hasAuthority('apply:deny')")
    @GetMapping("/deny/{id}")
    public ActionResult<Boolean> deny(@PathVariable("id") Long id) {
        if (Langs.isEmpty(id)) {
            return ActionTemplate.toNack("参数不能为空");
        }

        return appealFacade.deny(id);
    }

    @ApiOperation(value = "保存申请", notes = "需要权限", produces = "application/json")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ActionResult update(@RequestBody ApplyEditParam param) {
        if (Langs.isEmpty(param) || Langs.isEmpty(param.getGoal()) || Langs.isEmpty(param.getTitle())) {
            return ActionTemplate.toNack("参数不能为空");
        }

        return applyFacade.update(param);
    }

    @ApiOperation(value = "删除申请", notes = "需要权限", produces = "application/json")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ActionResult delete(@PathVariable("id") Long id) {
        if (Langs.isEmpty(id)) {
            return ActionTemplate.toNack("参数不能为空");
        }

        applyFacade.delete(id);
        return ActionTemplate.toAck("删除成功");
    }

    @GetMapping("/getApplyById/{id}")
    public ApplyPO getApplyByIdd(@PathVariable("id") Long id){
        ApplyPO applyPO = applyFacade.getApplyById(id);
        return applyPO;
    }

    @GetMapping("/apply-anon/getLastWeek/{id}")
    public ActionResult getLastWeek(@PathVariable("id") Long id){
        List<AppealVO> data = applyFacade.getLastWeek();
        return ActionTemplate.toAck("查询成功", data);
    }
}
