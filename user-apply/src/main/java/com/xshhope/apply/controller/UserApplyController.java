package com.xshhope.apply.controller;

import com.google.common.collect.Maps;
import com.xshhope.apply.facade.ApplyFacade;
import com.xshhope.apply.model.dto.PersonApplyDTO;
import com.xshhope.apply.model.param.ApplyParam;
import com.xshhope.common.support.ActionResult;
import com.xshhope.common.support.ActionTemplate;
import com.xshhope.common.utils.Langs;
import com.xshhope.common.utils.Pager;
import com.xshhope.common.utils.UploadFileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xshhope
 */
@Api(description = "用户捐款模块")
@RestController
@RequestMapping(value = "/apply")
public class UserApplyController {

    @Autowired
    private ApplyFacade applyFacade;

    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    @ApiOperation(value = "用户申请", notes = "需要权限", produces = "application/json")
    @RequestMapping(value = "/toApply", method = RequestMethod.POST)
    public ActionResult toApply(@RequestBody ApplyParam param) {
        if (Langs.isEmpty(param) || Langs.isEmpty(param.getContent())
                || Langs.isEmpty(param.getTitle())
                || Langs.isEmpty(param.getFile())) {

            return ActionTemplate.toNack("参数不能为空");
        }

        return applyFacade.save(param);
    }

    @ApiOperation(value = "上传求助信息", notes = "文件MultipartFile类型", produces = "application/from-data")
    @RequestMapping(value = "/apply-anon/uploadFiles", method = RequestMethod.POST)
    public ActionResult uploadFiles(@RequestParam("files") MultipartFile[] multipartFiles) {
        if (Langs.isEmpty(multipartFiles)) {
            return ActionTemplate.toNack("至少选择一个文件");
        }

        return applyFacade.uploadFiles(multipartFiles);
    }

    @ApiOperation(value = "上传求助信息", notes = "文件MultipartFile类型", produces = "application/from-data")
    @RequestMapping(value = "/apply-anon/uploadFiles2", method = RequestMethod.POST)
    public void uploadFiles2(@RequestParam("upload") MultipartFile[] multipartfiles, HttpServletResponse response) throws IllegalStateException, IOException {
        Map<String, String> map = new HashMap<String, String>();
        List<String> urls = UploadFileUtil.uploadFiles(multipartfiles);
        for (int i = 0; i < urls.size(); i++) {
            map.put("fileName", multipartfiles[i].getOriginalFilename());
            map.put("url", urls.get(i));
        }
        JSONObject jsonObject = JSONObject.fromObject(map);
        response.setContentType(CONTENT_TYPE);
        response.getWriter().write(jsonObject.toString());
    }

    @ApiOperation(value = "个人申请记录", notes = "管理权限", produces = "application/json")
    @RequestMapping(value = "/getApplyByUser", method = RequestMethod.POST)
    public ActionResult getAppealByUser(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                        @RequestParam(value = "size", required = false, defaultValue = "20") int size,
                                        @RequestParam(value = "search", required = false) String search,
                                        @RequestParam(value = "begin", required = false) String begin,
                                        @RequestParam(value = "end", required = false) String end) {

        Pager<PersonApplyDTO> pager = applyFacade.getApplyByUser(page, size, search, begin, end, "desc");
        Map<String, Object> map = Maps.newHashMap();
        map.put("current", pager.getCurrentPageNumber());
        map.put("size", pager.getPerPageSize());
        map.put("total", pager.getTotalElements());
        map.put("list", pager.getData());

        return ActionTemplate.toAck("查询成功", map);
    }
}
