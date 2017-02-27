package com.weixin.service;

import com.weixin.dto.message.Article;
import com.weixin.tool.MenuManager;
import com.weixin.tool.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
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
            //解析请求的XML
            Map<String, String> requestMap = MessageUtil.parseWXRequstXml(request);
            //获取请求的参数
            String fromUserName = requestMap.get("FromUserName");
            String toUserName = requestMap.get("ToUserName");
            String msgType = requestMap.get("MsgType");
            String content = requestMap.get("Content");

            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                //1、文本信息
//                respMessage = MessageUtil.initText(toUserName, fromUserName, "你发送的是文本消息");
                respMessage = responseTextByContent(content ,respMessage, toUserName, fromUserName);
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                //2、图片信息
                respMessage = MessageUtil.initText(toUserName, fromUserName, "你发送的是图片消息");
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                //3、地理位置信息
                respMessage = MessageUtil.initText(toUserName, fromUserName, "你发送的是地理位置信息");
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                //4、链接信息
                respMessage = MessageUtil.initText(toUserName, fromUserName, "你发送的是链接信息");
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                //5、音频信息
                respMessage = MessageUtil.initText(toUserName, fromUserName, "你发送的是音频信息");
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 获取事件推送类型
                String eventType = requestMap.get("Event");
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    //1、关注
                    respMessage = MessageUtil.initText(toUserName, fromUserName, "谢谢小主的关注!!!");
                } else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    //2、取消关注
                    // 取消关注后用户再收不到公众号发送的消息，因此不需要回复消息
                    // Do Nothing
                } else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    //3、自定义菜单事件
                    // 获取事件的key值，方便知道点击哪个菜单
                    String eventKey = requestMap.get("EventKey");
                    if (eventKey.equals("11")) {
                        respMessage = MessageUtil.initText(toUserName, fromUserName, "天气预报菜单项被点击!");
                    } else if (eventKey.equals("12")) {
                        respMessage = MessageUtil.initText(toUserName, fromUserName, "公交查询菜单项被点击!");
                    } else if (eventKey.equals("13")) {
                        respMessage = MessageUtil.initText(toUserName, fromUserName, "百度翻译菜单项被点击!");
                    } else if (eventKey.equals("14")) {
                        respMessage = MessageUtil.initText(toUserName, fromUserName, "历史上的今天菜单项被点击!");
                    } else if (eventKey.equals("21")) {
//                        respMessage = MessageUtil.initText(toUserName, fromUserName, "单图文菜单项被点击!");
                        respMessage = responseSingleNewsByClick(respMessage, toUserName, fromUserName);
                    } else if (eventKey.equals("22")) {
//                        respMessage = MessageUtil.initText(toUserName, fromUserName, "多图文菜单项被点击!");
                        respMessage = responseManyNewsByClick(respMessage, toUserName, fromUserName);
                    } else if (eventKey.equals("31")) {
                        respMessage = MessageUtil.initText(toUserName, fromUserName, "关于我们菜单项被点击!");
                    } else if (eventKey.equals("32")) {
                        respMessage = MessageUtil.initText(toUserName, fromUserName, "使用帮助菜单项被点击!");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respMessage;
    }

    /**
     * 通过content响应文本信息
     * @param content
     * @param respMessage
     * @param toUserName
     * @param fromUserName
     * @return
     */
    private static String responseTextByContent(String content, String respMessage, String toUserName, String fromUserName) {
        if ("1".equals(content)) {
            respMessage = MessageUtil.initText(toUserName, fromUserName, MenuManager.getHelpMenu());
        } else if (content.startsWith("翻译")) {
            String word = content.replaceAll("^翻译", "").trim();
            if("".equals(word)) {
                respMessage = MessageUtil.initText(toUserName, fromUserName, MenuManager.getHelpMenu());
            } else {
                // TODO Translate
            }
        } else {
            respMessage = MessageUtil.initText(toUserName, fromUserName, "你发送的是文本消息");
        }
        return respMessage;
    }

    /**
     * 通过click响应单图文
     * @param respMessage
     * @param toUserName
     * @param fromUserName
     * @return
     */
    private static String responseSingleNewsByClick(String respMessage, String toUserName, String fromUserName) {
        List<Article> articles = new ArrayList<Article>();
        Article article = new Article();
        article.setTitle("微信公众号开发教程（Java版）");
        article.setDescription("单图文消息简介！");
        article.setPicUrl("https://www.baidu.com/img/bd_logo1.png");
        article.setUrl("http://www.cnblogs.com/White7099/articles/6364922.html");
        articles.add(article);
        respMessage = MessageUtil.initNews(toUserName, fromUserName, articles);
        return respMessage;
    }

    /**
     * 通过click响应多图文
     * @param respMessage
     * @param toUserName
     * @param fromUserName
     * @return
     */
    private static String responseManyNewsByClick(String respMessage, String toUserName, String fromUserName) {
        List<Article> articles = new ArrayList<Article>();
        Article article1 = new Article();
        article1.setTitle("微信公众帐号开发教程\n引言");
        article1.setDescription("");
        article1.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");
        article1.setUrl("http://blog.csdn.net/lyq8479/article/details/8937622");

        Article article2 = new Article();
        article2.setTitle("第2篇\n微信公众帐号的类型");
        article2.setDescription("");
        article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
        article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8941577");

        Article article3 = new Article();
        article3.setTitle("第3篇\n开发模式启用及接口配置");
        article3.setDescription("");
        article3.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
        article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8944988");
        articles.add(article1);
        articles.add(article2);
        articles.add(article3);
        respMessage = MessageUtil.initNews(toUserName, fromUserName, articles);
        return respMessage;
    }
}
