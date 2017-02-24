package com.weixin.service;

import com.weixin.dto.message.resp.RespTextMessage;
import com.weixin.enums.EventTypeEnum;
import com.weixin.enums.MsgTypeEnum;
import com.weixin.tool.EnumUtil;
import com.weixin.tool.MenuManager;
import com.weixin.tool.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 核心服务类
 * Created by White on 2017/2/20.
 */
public class CoreService {

    private static Logger log = LoggerFactory.getLogger(CoreService.class);

    public static String processRequest(HttpServletRequest request) {
        String respMessage = null;
        try {
            //默认返回的文本消息内容
            String respContent = "请求处理异常，请稍后尝试!";
            //解析请求的XML
            Map<String, String> requestMap = MessageUtil.parseWXRequstXml(request);

            //发送方的账号id
            String fromUserName = requestMap.get("FromUserName");
            //开发者微信号
            String toUserName = requestMap.get("ToUserName");
            //消息类型
            String msgType = requestMap.get("MsgType");

            //回复的文本消息
            RespTextMessage respTextMessage = new RespTextMessage();
            respTextMessage.setToUserName(fromUserName);
            respTextMessage.setFromUserName(toUserName);
            respTextMessage.setCreateTime(new Date().getTime());
            respTextMessage.setMsgType(EnumUtil.toLowerCaseEnum(MsgTypeEnum.TEXT));
            respTextMessage.setFuncFlag(0);

            // 判断消息类型，并做出响应
            respContent = checkMsgType(msgType, respContent, requestMap);

            respTextMessage.setContent(respContent);
            respMessage = MessageUtil.respTextMessageToXml(respTextMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respMessage;
    }

    /**
     * 检查消息类型
     * @param msgType
     * @param respContent
     * @param requestMap
     */
    private static String checkMsgType(String msgType, String respContent, Map<String, String> requestMap) {
        //判断消息类型
        MsgTypeEnum msgTypeEnum = MsgTypeEnum.valueOf(MsgTypeEnum.class, msgType.toUpperCase());
        String content = requestMap.get("Content");
        switch (msgTypeEnum) {
            case TEXT:
                if(content.equals("1")) {
                    respContent = MenuManager.getHelpMenu();
                }else {
                    //1、文本消息
                    respContent = "你发送的是文本消息";
                }
                break;
            case IMAGE:
                //2、图片消息
                respContent = "你发送的是图片消息";
                break;
            case LOCATION:
                //3、地理位置信息
                respContent = "你发送的是地理位置信息";
                break;
            case LINK:
                //4、链接消息
                respContent = "你发送的是链接消息";
                break;
            case VOICE:
                //5、音频消息
                respContent = "你发送的是音频消息";
                break;
            case EVENT:
                // 事件类型
                String eventType = requestMap.get("Event");
                //判断事件类型，并作出响应
                respContent = checkEventType(eventType, respContent, requestMap);
                break;
        }
        return respContent;
    }

    /**
     * 检查事件类型
     * @param eventType
     * @param respContent
     * @param requestMap
     */
    private static String checkEventType(String eventType, String respContent, Map<String, String> requestMap) {
        EventTypeEnum eventTypeEnum = EventTypeEnum.valueOf(EventTypeEnum.class, eventType.toUpperCase());
        switch (eventTypeEnum) {
            case SUBSCRIBE:
                //1、订阅
                respContent = "谢谢小主的关注！";
                break;
            case UNSUBSCRIBE:
                //2、取消订阅
                // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
                break;
            case CLICK:
                //3、菜单点击事件
                // 事件KEY值，与创建自定义菜单时指定的KEY值对应
                String eventKey = requestMap.get("EventKey");

                //判断点击哪个菜单，并作出啊响应
                respContent = checkEventKey(eventKey, respContent);
                break;
        }
        return respContent;
    }

    /**
     * 检查菜单点击
     * @param eventKey
     * @param respContent
     */
    private static String checkEventKey (String eventKey, String respContent) {
        if (eventKey.equals("11")) {
            respContent = "天气预报菜单项被点击！";
        } else if (eventKey.equals("12")) {
            respContent = "公交查询菜单项被点击！";
        } else if (eventKey.equals("13")) {
            respContent = "百度翻译菜单项被点击！";
        } else if (eventKey.equals("14")) {
            respContent = "历史上的今天菜单项被点击！";
        } else if (eventKey.equals("21")) {
            respContent = "歌曲点播菜单项被点击！";
        } else if (eventKey.equals("22")) {
            respContent = "人脸识别菜单项被点击！";
        } else if (eventKey.equals("31")) {
            respContent = "关于我们菜单项被点击！";
        } else if (eventKey.equals("32")) {
            respContent = "使用帮助菜单项被点击！";
        }
        return respContent;
    }

}
