package com.xshhope.common.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xshhope
 */
@Data
@Component
public class StsAccount {

    @Value("${aliyun.access.id}")
    private String accessId;

    @Value("${aliyun.access.key}")
    private String accessKey;

    @Value("${aliyun.oss.endpoint}")
    private String ossEndpoint;

    @Value("${aliyun.oss.bucket}")
    private String ossBucket;

    private String securityToken;
}
