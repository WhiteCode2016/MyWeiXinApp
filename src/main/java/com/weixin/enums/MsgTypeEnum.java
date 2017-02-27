package com.weixin.enums;

/**
 * 消息类型
 * Created by White on 2017/2/24.
 */
public enum MsgTypeEnum {
    //请求类型
    TEXT,          //文本消息
    IMAGE,         //图片消息
    LINK,          //链接消息
    LOCATION,     //地理位置信息
    VOICE,        //音频信息
    EVENT,        //推送消息

    //响应类型
    NEWS,         //图文消息
    MUSIC         //音乐消息
}
