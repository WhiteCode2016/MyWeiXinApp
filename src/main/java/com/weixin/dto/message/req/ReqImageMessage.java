package com.weixin.dto.message.req;

/**
/**
 * 图片消息体
 * Created by White on 2017/2/20.
 */
public class ReqImageMessage extends ReqBaseMessage {
    private String PicUrl;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}
