package com.weixin.dto;

/**
 * JsapiTicket类
 * Created by White on 2017/3/2.
 */
public class JsapiTicket {
    private int errcode;
    private String errmsg;
    private String ticket;
    //凭证有效时间
    private int expires_in;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public String toString() {
        return "JsapiTicket{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                ", ticket='" + ticket + '\'' +
                ", expires_in=" + expires_in +
                '}';
    }
}
