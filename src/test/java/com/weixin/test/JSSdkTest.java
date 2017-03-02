package com.weixin.test;

import com.weixin.dto.AccessToken;
import com.weixin.tool.WeixinUtil;

/**
 * 获取jsapi_ticket测试
 * Created by White on 2017/3/2.
 */
public class JSSdkTest {
    public static void main(String[] args) {
        AccessToken at = WeixinUtil.getWXAccessToken();
        System.out.println(WeixinUtil.getTicket(at.getAccess_Token()).toString());
    }
}
