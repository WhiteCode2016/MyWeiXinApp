package com.weixin.dto;

/**
 * 微信通用接口凭证类
 * Created by White on 2017/2/21.
 */
public class AccessToken {
    //获取到的凭证
    private String access_Token;
    //凭证有效时间，单位：秒
    private int expires_in;

    public String getAccess_Token() {
        return access_Token;
    }

    public void setAccess_Token(String access_Token) {
        this.access_Token = access_Token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
