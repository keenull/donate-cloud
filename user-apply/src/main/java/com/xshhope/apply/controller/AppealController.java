package com.xshhope.apply.controller;

import com.google.common.collect.Maps;
import com.xshhope.apply.convert.AppealDetailConvert;
import com.xshhope.apply.facade.AppealFacade;
import com.xshhope.apply.model.dto.*;
import com.xshhope.common.support.ActionResult;
import com.xshhope.common.support.ActionTemplate;
import com.xshhope.common.utils.Langs;
import com.xshhope.common.utils.Pager;
import com.xshhope.model.apply.AppealPO;
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
@Api(description = "用户求助模块")
@RestController
@RequestMapping(value = "/appeal")
public class AppealController {

    @Autowired
    private AppealFacade appealFacade;

    @ApiOperation(value = "首页前端个人求助列表", notes = "无需权限", produces = "application/json")
    @RequestMapping(value = "/apply-anon/person", method = RequestMethod.POST)
    public ActionResult listPerson(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                   @RequestParam(value = "size", required = false, defaultValue = "20") int size,
                                   @RequestParam(value = "search", required = false) String search,
                                   @RequestParam(value = "orderField", required = false, defaultValue = "gmtCreated") String orderField,
                                   @RequestParam(value = "orderDirection", required = false, defaultValue = "desc") String orderDirection) {

        Pager<AppealDTO> pager = appealFacade.listPerson(page, size, search, orderField, orderDirection);
        Map<String, Object> map = Maps.newHashMap();
        map.put("current", pager.getCurrentPageNumber());
        map.put("size", pager.getPerPageSize());
        map.put("total", pager.getTotalElements());
        map.put("list", pager.getData());

        return ActionTemplate.toAck("查询成功", map);
    }

    @ApiOperation(value = "首页前端个人求助列表", notes = "无需权限", produces = "application/json")
    @RequestMapping(value = "/apply-anon/personCompleted", method = RequestMethod.POST)
    public ActionResult listPersonCompleted(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                   @RequestParam(value = "size", required = false, defaultValue = "20") int size,
                                   @RequestParam(value = "search", required = false) String search,
                                   @RequestParam(value = "orderField", required = false, defaultValue = "gmtCreated") String orderField,
                                   @RequestParam(value = "orderDirection", required = false, defaultValue = "desc") String orderDirection) {

        Pager<AppealDTO> pager = appealFacade.listPersonCompleted(page, size, search, orderField, orderDirection);
        Map<String, Object> map = Maps.newHashMap();
        map.put("current", pager.getCurrentPageNumber());
        map.put("size", pager.getPerPageSize());
        map.put("total", pager.getTotalElements());
        map.put("list", pager.getData());

        return ActionTemplate.toAck("查询成功", map);
    }

    @ApiOperation(value = "个人求助通过列表", notes = "管理权限", produces = "application/json")
    @PreAuthorize("hasAuthority('apply:person:verify')")
    @PostMapping("/personVerified")
    public ActionResult listPersonVerified(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                           @RequestParam(value = "size", required = false, defaultValue = "20") int size,
                                           @RequestParam(value = "search", required = false) String search,
                                           @RequestParam(value = "begin", required = false) String begin,
                                           @RequestParam(value = "end", required = false) String end) {

        if (Langs.isNull(begin) || Langs.isNull(end)) {
            begin = "";
            end = "";
        }
        Pager<AppealDTO> pager = appealFacade.listPersonVerified(page, size, search.trim(), begin, end, "desc");
        Map<String, Object> map = Maps.newHashMap();
        map.put("current", pager.getCurrentPageNumber());
        map.put("size", pager.getPerPageSize());
        map.put("total", pager.getTotalElements());
        map.put("list", pager.getData());

        return ActionTemplate.toAck("查询成功", map);
    }

    @ApiOperation(value = "个人求助列未通过表", notes = "管理权限", produces = "application/json")
    @PreAuthorize("hasAuthority('apply:person:unverify')")
    @PostMapping("/personUnVerified")
    public ActionResult listPersonUnVerified(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                             @RequestParam(value = "size", required = false, defaultValue = "20") int size,
                                             @RequestParam(value = "search", required = false) String search,
                                             @RequestParam(value = "begin", required = false) String begin,
                                             @RequestParam(value = "end", required = false) String end) {

        if (Langs.isNull(begin) || Langs.isNull(end)) {
            begin = "";
            end = "";
        }

        Pager<AppealDTO> pager = appealFacade.listPersonUnVerified(page, size, search.trim(), begin, end, "desc");
        Map<String, Object> map = Maps.newHashMap();
        map.put("current", pager.getCurrentPageNumber());
        map.put("size", pager.getPerPageSize());
        map.put("total", pager.getTotalElements());
        map.put("list", pager.getData());

        return ActionTemplate.toAck("查询成功", map);
    }

    @ApiOperation(value = "首页前端公益求助列表", notes = "无需权限", produces = "application/json")
    @RequestMapping(value = "/apply-anon/public", method = RequestMethod.POST)
    public ActionResult listPublic(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                   @RequestParam(value = "size", required = false, defaultValue = "20") int size,
                                   @RequestParam(value = "search", required = false) String search,
                                   @RequestParam(value = "orderField", required = false) String orderField,
                                   @RequestParam(value = "orderDirection", required = false, defaultValue = "desc") String orderDirection) {

        Pager<AppealDTO> pager = appealFacade.listPublicVerified(page, size, search.trim(), orderField, orderDirection);
        Map<String, Object> map = Maps.newHashMap();
        map.put("current", pager.getCurrentPageNumber());
        map.put("size", pager.getPerPageSize());
        map.put("total", pager.getTotalElements());
        map.put("list", pager.getData());

        return ActionTemplate.toAck("查询成功", map);
    }

    @ApiOperation(value = "首页前端公益求助列表", notes = "无需权限", produces = "application/json")
    @RequestMapping(value = "/apply-anon/publicCompleted", method = RequestMethod.POST)
    public ActionResult listPublicCompleted(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                   @RequestParam(value = "size", required = false, defaultValue = "20") int size,
                                   @RequestParam(value = "search", required = false) String search,
                                   @RequestParam(value = "orderField", required = false) String orderField,
                                   @RequestParam(value = "orderDirection", required = false, defaultValue = "desc") String orderDirection) {

        Pager<AppealDTO> pager = appealFacade.listPublicVerifiedCompleted(page, size, search.trim(), orderField, orderDirection);
        Map<String, Object> map = Maps.newHashMap();
        map.put("current", pager.getCurrentPageNumber());
        map.put("size", pager.getPerPageSize());
        map.put("total", pager.getTotalElements());
        map.put("list", pager.getData());

        return ActionTemplate.toAck("查询成功", map);
    }

    @ApiOperation(value = "公益求助通过列表", notes = "管理权限", produces = "application/json")
    @PreAuthorize("hasAuthority('apply:public:verify')")
    @RequestMapping(value = "/publicVerified", method = RequestMethod.POST)
    public ActionResult listPublicVerified(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                           @RequestParam(value = "size", required = false, defaultValue = "20") int size,
                                           @RequestParam(value = "search", required = false) String search,
                                           @RequestParam(value = "begin", required = false) String begin,
                                           @RequestParam(value = "end", required = false) String end) {

        if (Langs.isNull(begin) || Langs.isNull(end)) {
            begin = "";
            end = "";
        }

        Pager<AppealDTO> pager = appealFacade.listPublicVerified(page, size, search.trim(), begin, end, "desc");
        Map<String, Object> map = Maps.newHashMap();
        map.put("current", pager.getCurrentPageNumber());
        map.put("size", pager.getPerPageSize());
        map.put("total", pager.getTotalElements());
        map.put("list", pager.getData());

        return ActionTemplate.toAck("查询成功", map);
    }

    @ApiOperation(value = "公益求助未通过列表", notes = "管理权限", produces = "application/json")
    @PreAuthorize("hasAuthority('apply:public:unverify')")
    @RequestMapping(value = "/publicUnVerified", method = RequestMethod.POST)
    public ActionResult listPublicUnVerified(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                             @RequestParam(value = "size", required = false, defaultValue = "20") int size,
                                             @RequestParam(value = "search", required = false) String search,
                                             @RequestParam(value = "begin", required = false) String begin,
                                             @RequestParam(value = "end", required = false) String end) {

        if (Langs.isNull(begin) || Langs.isNull(end)) {
            begin = "";
            end = "";
        }

        Pager<AppealDTO> pager = appealFacade.listPublicUnVerified(page, size, search.trim(), begin, end, "desc");
        Map<String, Object> map = Maps.newHashMap();
        map.put("current", pager.getCurrentPageNumber());
        map.put("size", pager.getPerPageSize());
        map.put("total", pager.getTotalElements());
        map.put("list", pager.getData());

        return ActionTemplate.toAck("查询成功", map);
    }

    @ApiOperation(value = "个人求助详情", notes = "无需权限", produces = "application/json")
    @RequestMapping(value = "/apply-anon/personDetail/{id}", method = RequestMethod.GET)
    public ActionResult getPersonAppeal(@PathVariable("id") Long id) {

        if (Langs.isEmpty(id)) {
            return ActionTemplate.toNack("id不能为空");
        }

        AppealDetailDTO appealDetailDTO = appealFacade.getPersonAppealById(id);

        return ActionTemplate.toAck("获取个人求助信息成功", appealDetailDTO);
    }


    @ApiOperation(value = "公益求助详情", notes = "无需权限", produces = "application/json")
    @RequestMapping(value = "/apply-anon/publicDetail/{id}", method = RequestMethod.GET)
    public ActionResult getPublicAppeal(@PathVariable("id") Long id) {

        if (Langs.isEmpty(id)) {
            return ActionTemplate.toNack("id不能为空");
        }

        return ActionTemplate.toAck("获取公益求助信息成功", new AppealDetailConvert().apply(appealFacade.getPublicAppealById(id)));
    }

    @ApiOperation(value = "捐款统计", notes = "管理权限", produces = "application/json")
    @PreAuthorize("hasAuthority('apply:donate:count')")
    @RequestMapping(value = "/donateCount", method = RequestMethod.POST)
    public ActionResult donateCount(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                    @RequestParam(value = "size", required = false, defaultValue = "20") int size,
                                    @RequestParam(value = "type", required = false) Integer type,
                                    @RequestParam(value = "search", required = false) String search,
                                    @RequestParam(value = "begin", required = false) String begin,
                                    @RequestParam(value = "end", required = false) String end) {

        Pager<RecordCountDTO> pager = appealFacade.donateAdminCount(page, size, type, search, begin, end, "desc");
        Map<String, Object> map = Maps.newHashMap();
        map.put("current", pager.getCurrentPageNumber());
        map.put("size", pager.getPerPageSize());
        map.put("total", pager.getTotalElements());
        map.put("list", pager.getData());

        return ActionTemplate.toAck("查询成功", map);
    }

    @ApiOperation(value = "个人申请记录", notes = "管理权限", produces = "application/json")
    @RequestMapping(value = "/getAppealByUser", method = RequestMethod.POST)
    public ActionResult getAppealByUser(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                    @RequestParam(value = "size", required = false, defaultValue = "20") int size,
                                    @RequestParam(value = "search", required = false) String search,
                                    @RequestParam(value = "begin", required = false) String begin,
                                    @RequestParam(value = "end", required = false) String end) {

        Pager<PersonApplyDTO> pager = appealFacade.getAppealByUser(page, size, search, begin, end, "desc");
        Map<String, Object> map = Maps.newHashMap();
        map.put("current", pager.getCurrentPageNumber());
        map.put("size", pager.getPerPageSize());
        map.put("total", pager.getTotalElements());
        map.put("list", pager.getData());

        return ActionTemplate.toAck("查询成功", map);
    }

    @ApiOperation(value = "首页前端已完成求助列表", notes = "无需权限", produces = "application/json")
    @RequestMapping(value = "/apply-anon/completed", method = RequestMethod.POST)
    public ActionResult listCompleted(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                   @RequestParam(value = "size", required = false, defaultValue = "20") int size,
                                   @RequestParam(value = "search", required = false) String search,
                                   @RequestParam(value = "orderField", required = false, defaultValue = "gmtCreated") String orderField,
                                   @RequestParam(value = "orderDirection", required = false, defaultValue = "desc") String orderDirection) {

        Pager<AppealDTO> pager = appealFacade.listCompleted(page, size, search, orderField, orderDirection);
        Map<String, Object> map = Maps.newHashMap();
        map.put("current", pager.getCurrentPageNumber());
        map.put("size", pager.getPerPageSize());
        map.put("total", pager.getTotalElements());
        map.put("list", pager.getData());

        return ActionTemplate.toAck("查询成功", map);
    }

    @ApiOperation(value = "根据ID得到详情", notes = "无需权限", produces = "application/json")
    @GetMapping("/apply/getAppealById/{id}")
    public ActionResult<AppealPO> getAppealById(@PathVariable("id") Long id){
        AppealPO appealPO = appealFacade.getAppealById(id);
        return ActionTemplate.toAck("查询成功", appealPO);
    }

    @GetMapping("/getAppealById/{id}")
    public AppealPO getAppealByIdd(@PathVariable("id") Long id){
        AppealPO appealPO = appealFacade.getAppealById(id);
        return appealPO;
    }

    @ApiOperation(value = "首页轮播", notes = "管理权限", produces = "application/json")
    @RequestMapping(value = "/apply-anon/pageDetail/{id}", method = RequestMethod.GET)
    public ActionResult pageDetail(@PathVariable("id") Long id) {

        List<AppealDetailVO> appealDetailVOS = appealFacade.pageDetail();

        return ActionTemplate.toAck("查询成功", appealDetailVOS);
    }
}
