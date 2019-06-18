package com.xshhope.user.service;


import com.xshhope.model.user.AppUser;
import com.xshhope.model.user.WechatUserInfo;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public interface WechatService {

    /**
     * 获取微信授权url
     *
     * @param app
     * @param request
     * @param toUrl
     * @return
     */
    String getWechatAuthorizeUrl(String app, HttpServletRequest request, String toUrl)
            throws UnsupportedEncodingException;

    /**
     * 获取微信个人用户信息
     *
     * @param app
     * @param request
     * @param code
     * @param state
     * @return
     */
    WechatUserInfo getWechatUserInfo(String app, HttpServletRequest request, String code, String state);

    String getToUrl(String toUrl, WechatUserInfo wechatUserInfo);

    void bindingUser(AppUser appUser, String tempCode, String openid);

    WechatUserInfo checkAndGetWechatUserInfo(String tempCode, String openid);
}
