package com.xshhope.common.utils;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.Date;

/**
 * @author xshhope
 */
public class OssImgAccessUtil {
    private OSSClient ossClient;
    String bucketName = "donate-store-bucket";

    @Autowired
    private StsAccount stsAccount;

    /**
     * accessKey
     */

    /**
     * 创建OSSClient实例。
     */
    public void initOSSClient() {
        StsAccount ossAccount = StsServiceImpl.stsAccount;
        if (ossAccount.getAccessId() == null) {
            StsServiceImpl.getSTSAccount(stsAccount.getAccessId(), stsAccount.getAccessKey());
        }
        this.ossClient = new OSSClient(ossAccount.getOssEndpoint(), ossAccount.getAccessId(), ossAccount.getSecurityToken());
    }

    /**
     * 销毁
     */
    public void destory() {
        ossClient.shutdown();
    }

    public String getImgAccessUrl(String imgUrl) {
        // 设置URL过期时间为1小时。
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(bucketName, imgUrl, expiration);
        return url.toString();
    }
}
