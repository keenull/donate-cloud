package com.xshhope.log.controller;

import com.google.common.collect.Maps;
import com.xshhope.common.support.ActionResult;
import com.xshhope.common.support.ActionTemplate;
import com.xshhope.common.utils.Pager;
import com.xshhope.log.service.LogService;
import com.xshhope.model.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping("/logs-anon/internal")
    public void save(@RequestBody Log log) {
        logService.save(log);
    }

    /**
     * 日志查询
     *
     * @param params
     * @return
     */
    @PreAuthorize("hasAuthority('log:query')")
    @GetMapping("/logs")
    public ActionResult findLogs(@RequestParam Map<String, Object> params) {

        Pager<Log> pager = logService.findLogs(params);

        Map<String, Object> map = Maps.newHashMap();
        map.put("current", pager.getCurrentPageNumber());
        map.put("size", pager.getPerPageSize());
        map.put("total", pager.getTotalElements());
        map.put("list", pager.getData());
        return ActionTemplate.toAck("查询成功", map);
    }

}
