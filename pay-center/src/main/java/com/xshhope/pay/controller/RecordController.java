package com.xshhope.pay.controller;

import com.google.common.collect.Maps;
import com.xshhope.common.support.ActionResult;
import com.xshhope.common.support.ActionTemplate;
import com.xshhope.common.utils.Pager;
import com.xshhope.pay.facade.RecordFacade;
import com.xshhope.pay.model.dto.DonateListDTO;
import com.xshhope.pay.model.dto.RecordDTO;
import com.xshhope.pay.model.dto.RecordListDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author xshhope
 */
@Api(description = "捐款排行模块")
@RestController
@RequestMapping(value = "/record")
public class RecordController {

    @Autowired
    private RecordFacade recordFacade;

    @ApiOperation(value = "最新捐款排行榜", notes = "无需权限", produces = "application/json")
    @RequestMapping(value = "/apply-anon/latestDontate", method = RequestMethod.POST)
    public ActionResult latestDontate(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                      @RequestParam(value = "size", required = false, defaultValue = "3") int size,
                                      @RequestParam(value = "search", required = false) String search,
                                      @RequestParam(value = "orderField", required = false, defaultValue = "gmtCreated") String orderField,
                                      @RequestParam(value = "orderDirection", required = false, defaultValue = "desc") String orderDirection) {

        Pager<RecordDTO> pager = recordFacade.latestDontate(page, size, search, orderField, orderDirection);
        Map<String, Object> map = Maps.newHashMap();
        map.put("current", pager.getCurrentPageNumber());
        map.put("size", pager.getPerPageSize());
        map.put("total", pager.getTotalElements());
        map.put("list", pager.getData());
        return ActionTemplate.toAck("查询成功", map);
    }


    @ApiOperation(value = "捐款排行榜", notes = "无需权限", produces = "application/json")
    @RequestMapping(value = "/apply-anon/donateBoard", method = RequestMethod.POST)
    public ActionResult donateBoard(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                    @RequestParam(value = "size", required = false, defaultValue = "3") int size,
                                    @RequestParam(value = "search", required = false) String search,
                                    @RequestParam(value = "orderField", required = false) String orderField,
                                    @RequestParam(value = "orderDirection", required = false, defaultValue = "desc") String orderDirection) {

        Pager<RecordListDTO> pager = recordFacade.donateBoard(page, size, search, orderField, orderDirection);
        Map<String, Object> map = Maps.newHashMap();
        map.put("current", pager.getCurrentPageNumber());
        map.put("size", pager.getPerPageSize());
        map.put("total", pager.getTotalElements());
        map.put("list", pager.getData());
        return ActionTemplate.toAck("查询成功", map);
    }

    @ApiOperation(value = "捐款列表", notes = "需要权限", produces = "application/json")
    @PreAuthorize("hasAuthority('apply:donate:list')")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ActionResult listDonate(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                   @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                                   @RequestParam(value = "type", required = false) Integer type,
                                   @RequestParam(value = "search", required = false) String search,
                                   @RequestParam(value = "begin", required = false) String begin,
                                   @RequestParam(value = "end", required = false) String end) {

        Pager<DonateListDTO> pager = recordFacade.listDonate(page, size, type, search, begin, end, "desc");
        Map<String, Object> map = Maps.newHashMap();
        map.put("current", pager.getCurrentPageNumber());
        map.put("size", pager.getPerPageSize());
        map.put("total", pager.getTotalElements());
        map.put("list", pager.getData());

        return ActionTemplate.toAck("查询成功", map);
    }

    @ApiOperation(value = "捐款统计", notes = "管理权限", produces = "application/json")
    @RequestMapping(value = "/getDontateByUser", method = RequestMethod.POST)
    public ActionResult getDontateByUser(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                        @RequestParam(value = "size", required = false, defaultValue = "20") int size,
                                        @RequestParam(value = "search", required = false) String search,
                                        @RequestParam(value = "begin", required = false) String begin,
                                        @RequestParam(value = "end", required = false) String end) {

        Pager<DonateListDTO> pager = recordFacade.getDontateByUser(page, size, search.trim(), begin, end, "desc");
        Map<String, Object> map = Maps.newHashMap();
        map.put("current", pager.getCurrentPageNumber());
        map.put("size", pager.getPerPageSize());
        map.put("total", pager.getTotalElements());
        map.put("list", pager.getData());

        return ActionTemplate.toAck("查询成功", map);
    }
}
