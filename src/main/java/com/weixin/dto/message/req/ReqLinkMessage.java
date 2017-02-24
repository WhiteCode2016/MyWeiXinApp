package com.weixin.dto.message.req;

/**
 * 链接消息体
 * Created by White on 2017/2/20.
 */
public class ReqLinkMessage extends ReqBaseMessage {
    //消息标题
    private String Title;
    //消息描述
    private String Description;
    //消息链接
    private String Url;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
