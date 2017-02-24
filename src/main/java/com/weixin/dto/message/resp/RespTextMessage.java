package com.weixin.dto.message.resp;

/**
 * 文本消息体
 * Created by White on 2017/2/20.
 */
public class RespTextMessage extends RespBaseMessage {
    //回复的消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
