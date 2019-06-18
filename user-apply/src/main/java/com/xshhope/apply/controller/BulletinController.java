package com.xshhope.apply.controller;

import com.google.common.collect.Maps;
import com.xshhope.apply.convert.BulletinConvert;
import com.xshhope.apply.facade.BulletinFacade;
import com.xshhope.apply.model.dto.BulletinDTO;
import com.xshhope.apply.model.dto.BulletinListDTO;
import com.xshhope.apply.model.param.BulletinListParam;
import com.xshhope.apply.model.param.BulletinParam;
import com.xshhope.common.support.ActionResult;
import com.xshhope.common.support.ActionTemplate;
import com.xshhope.common.utils.Langs;
import com.xshhope.common.utils.Pager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author xshhope
 */
@Api(description = "公告模块")
@RestController
@RequestMapping("/bulletin")
public class BulletinController {

    @Autowired
    private BulletinFacade bulletinFacade;

    @ApiOperation(value = "查询所有公告", notes = "无需权限", produces = "application/json")
    @RequestMapping(value = "/bulletin-anon/home", method = RequestMethod.POST)
    public ActionResult homeBulletin(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                       @RequestParam(value = "size", required = false, defaultValue = "20") int size,
                                       @RequestParam(value = "search", required = false) String search,
                                       @RequestParam(value = "orderField", required = false, defaultValue = "gmtCreated") String orderField,
                                       @RequestParam(value = "orderDirection", required = false, defaultValue = "desc") String orderDirection) {

        Pager<BulletinDTO> pager = bulletinFacade.homeBulletin(page, size, search, orderField, orderDirection);
        Map<String, Object> map = Maps.newHashMap();
        map.put("current", pager.getCurrentPageNumber());
        map.put("size", pager.getPerPageSize());
        map.put("total", pager.getTotalElements());
        map.put("list", pager.getData());
        return ActionTemplate.toAck("查询成功", map);
    }

    @ApiOperation(value = "查询某个公告", notes = "无需权限", produces = "application/json")
    @RequestMapping(value = "/bulletin-anon/get/{id}", method = RequestMethod.GET)
    public ActionResult<BulletinDTO> getBulletin(@PathVariable("id") Long id) {
        if (Langs.isEmpty(id)) {
            return ActionTemplate.toNack("参数不能为空");
        }

        return bulletinFacade.getBulletinById(id);
    }

    @ApiOperation(value = "删除公告", notes = "需要权限", produces = "application/json")
    @PreAuthorize("hasAuthority('bulletin:delete')")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ActionResult deleteBulletin(@RequestBody BulletinListParam bulletinids) {
        if (Langs.isEmpty(bulletinids)) {
            return ActionTemplate.toNack("参数不能为空");
        }

        return bulletinFacade.deleteBulletin(bulletinids.getBulletinids());
    }

    @ApiOperation(value = "修改公告", notes = "需要权限", produces = "application/json")
    @PreAuthorize("hasAuthority('bulletin:update')")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ActionResult saveBulletin(@RequestBody BulletinParam param) {
        if (Langs.isEmpty(param) || Langs.isEmpty(param.getContent()) || Langs.isEmpty(param.getTitle())) {
            return ActionTemplate.toNack("参数不能为空");
        }

        return bulletinFacade.saveBulletin(param);
    }

    @ApiOperation(value = "新增公告", notes = "需要权限", produces = "application/json")
    @PreAuthorize("hasAuthority('bulletin:update')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ActionResult addBulletin(@RequestBody BulletinParam param) {
        if (Langs.isEmpty(param) || Langs.isEmpty(param.getContent()) || Langs.isEmpty(param.getTitle())) {
            return ActionTemplate.toNack("参数不能为空");
        }

        return bulletinFacade.addBulletin(param);
    }

    @ApiOperation(value = "首页展示公告", notes = "无需权限", produces = "application/json")
    @RequestMapping(value = "/bulletin-anon/show", method = RequestMethod.POST)
    public ActionResult showBulletin() {

        return ActionTemplate.toAck("获取公告成功", new BulletinConvert().apply(bulletinFacade.showLatestBulletin()));
    }

    @ApiOperation(value = "查询所有公告", notes = "无需权限", produces = "application/json")
    @PreAuthorize("hasAuthority('bulletin:query')")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ActionResult searchBulletin(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                       @RequestParam(value = "size", required = false, defaultValue = "20") int size,
                                       @RequestParam(value = "search", required = false) String search) {

        Pager<BulletinListDTO> pager = bulletinFacade.listBulletin(page, size, search);
        Map<String, Object> map = Maps.newHashMap();
        map.put("current", pager.getCurrentPageNumber());
        map.put("size", pager.getPerPageSize());
        map.put("total", pager.getTotalElements());
        map.put("list", pager.getData());
        return ActionTemplate.toAck("查询成功", map);
    }

    @ApiOperation(value = "下线公告", notes = "无需权限", produces = "application/json")
    @RequestMapping(value = "/offineBulletin/{id}", method = RequestMethod.GET)
    public ActionResult bulletinOffline(@PathVariable("id") Long id) {
        if (Langs.isEmpty(id)) {
            return ActionTemplate.toNack("参数不能为空");
        }
        System.out.println("12332");
        return bulletinFacade.offineBulletin(id);
    }

    @ApiOperation(value = "上线公告", notes = "无需权限", produces = "application/json")
    @RequestMapping(value = "/onlineBulletin/{id}", method = RequestMethod.GET)
    public ActionResult onlineBulletin(@PathVariable("id") Long id) {
        if (Langs.isEmpty(id)) {
            return ActionTemplate.toNack("参数不能为空");
        }
        System.out.println("12332");
        return bulletinFacade.onlineBulletin(id);
    }
}
