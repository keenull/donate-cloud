package com.xshhope.common.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;

/**
 * @author xshhope
 */
public class StsServiceImpl {
    public static StsAccount stsAccount = new StsAccount();

    public static void getSTSAccount(String accessKeyId, String accessKeySecret) {
        String endpoint = "sts.cn-beijing.aliyuncs.com";
        String roleArn = "acs:ram::1984545964605306:root";
        String roleSessionName = "xietest";
        String policy = null;
        try {
            // 添加endpoint（直接使用STS endpoint，前两个参数留空，无需添加region ID）
            DefaultProfile.addEndpoint("", "", "Sts", endpoint);
            // 构造default profile（参数留空，无需添加region ID）
            IClientProfile profile = DefaultProfile.getProfile("", accessKeyId, accessKeySecret);
            // 用profile构造client
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setMethod(MethodType.POST);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            // Optional
            request.setPolicy(policy);
            final AssumeRoleResponse response = client.getAcsResponse(request);
            stsAccount.setOssEndpoint("oss-cn-beijing.aliyuncs.com");
            stsAccount.setAccessId(response.getCredentials().getAccessKeyId());
            stsAccount.setAccessKey(response.getCredentials().getAccessKeySecret());
            stsAccount.setSecurityToken(response.getCredentials().getSecurityToken());
        } catch (ClientException e) {
            System.out.println("Failed：");
            System.out.println("Error code: " + e.getErrCode());
            System.out.println("Error message: " + e.getErrMsg());
            System.out.println("RequestId: " + e.getRequestId());
        }
    }
}