package com.xshhope.common.utils;

import com.xshhope.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xshhope
 */
@Slf4j
public class UploadFileUtil {

    public static String updateUserHeaderImg(MultipartFile file) throws BizException {
        if (file == null || file.getSize() <= 0) {
            throw new BizException("file不能为空");
        }
        OssClientUtil ossClient = new OssClientUtil();
        String name = ossClient.uploadImgHeader(file);
        String imgUrl = ossClient.getHearImgUrl(name, "user_img/");
        String[] split = imgUrl.split("\\?");
        return split[0];
    }

    public static List<String> uploadFiles(MultipartFile[] multipartFiles) {
        List<String> filePaths = new ArrayList<>();
        if (!Langs.isEmpty(multipartFiles)) {
            for (MultipartFile multipartFile : multipartFiles) {
                OssClientUtil ossClient = new OssClientUtil();
                String name = ossClient.uploadImgHeader(multipartFile);
                String imgUrl = ossClient.getHearImgUrl(name, "user_img/");
                String[] split = imgUrl.split("\\?");
                filePaths.add(split[0]);
            }
        }

        System.out.println("filePaths :   " + filePaths);

        return filePaths;
    }
}
