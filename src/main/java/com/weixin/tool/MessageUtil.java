package com.weixin.tool;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.weixin.dto.message.Article;
import com.weixin.dto.message.Image;
import com.weixin.dto.message.Music;
import com.weixin.dto.message.resp.RespImageMessage;
import com.weixin.dto.message.resp.RespMusicMessage;
import com.weixin.dto.message.resp.RespNewsMessage;
import com.weixin.dto.message.resp.RespTextMessage;
import com.weixin.enums.MsgTypeEnum;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息工具类
 * Created by White on 2017/2/20.
 */
public class MessageUtil {

    //请求消息类型：文本
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    //请求消息类型：图片
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    //请求消息类型：链接
    public static final String REQ_MESSAGE_TYPE_LINK = "link";
    //请求消息类型：地理位置
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    //请求消息类型：音频
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    //请求消息类型：推送
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    //返回消息类型：文本
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";
    //返回消息类型：音乐
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
    //返回消息类型：图文
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";

    //事件类型：subscribe(订阅)
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    //事件类型：unsubscribe(取消订阅)
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    //事件类型：CLICK(自定义菜单点击事件)
    public static final String EVENT_TYPE_CLICK = "CLICK";

    /**
     * 解析微信发送来的请求（XML格式）
     * @param request
     * @return
     * @throws Exception
     */
    public static Map<String, String> parseWXRequstXml(HttpServletRequest request) throws Exception{
        Map<String, String> map = new HashMap<String, String>();
        InputStream inputStream = request.getInputStream();
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        for(Element e : elementList) {
            map.put(e.getName(),e.getText());
        }
        inputStream.close();
        inputStream = null;
        return  map;
    }

    /**
     * 文本消息对象转换为XML
     * @param resTextMessage
     * @return
     */
    public static String respTextMessageToXml(RespTextMessage resTextMessage) {
        xstream.alias("xml",resTextMessage.getClass());
        return xstream.toXML(resTextMessage);
    }

    /**
     * 图片消息对象转换成XML
     * @param respImageMessage
     * @return
     */
    public static String respImageMessageToXml(RespImageMessage respImageMessage){
        xstream.alias("xml", respImageMessage.getClass());
        return xstream.toXML(respImageMessage);
    }

    /**
     * 音乐消息对象转换成XML
     * @param resMusicMessage
     * @return
     */
    public static String respMusicMessageToXml(RespMusicMessage resMusicMessage) {
        xstream.alias("xml",resMusicMessage.getClass());
        return xstream.toXML(resMusicMessage);
    }

    /**
     * 图文消息对象转换成XML
     * @param resNewsMessage
     * @return
     */
    public static String respNewsMessageToXml(RespNewsMessage resNewsMessage) {
        xstream.alias("xml", resNewsMessage.getClass());
        xstream.alias("item", new Article().getClass());
        return xstream.toXML(resNewsMessage);
    }

    /**
     * 扩展xstream，使其支持CDATA块
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
              boolean cdata = true;
              public void startNode(String name, Class clazz) {
                  super.startNode(name, clazz);
              }

              protected void writeText(QuickWriter writer, String text) {
                  if(cdata) {
                      writer.write("<![CDATA[");
                      writer.write(text);
                      writer.write("]]>");
                  }else {
                      writer.write(text);
                  }
              }
            };
        }
    });

    /**
     * 组装文本消息
     * @param toUserName
     * @param fromUserName
     * @param content
     * @return
     */
    public static String initText(String toUserName, String fromUserName, String content) {
        RespTextMessage respTextMessage = new RespTextMessage();
        respTextMessage.setFromUserName(toUserName);
        respTextMessage.setToUserName(fromUserName);
        respTextMessage.setMsgType(EnumUtil.toLowerCaseEnum(MsgTypeEnum.TEXT));
        respTextMessage.setCreateTime(new Date().getTime());
        respTextMessage.setContent(content);
        return respTextMessageToXml(respTextMessage);
    }

    /**
     * 组装图片消息
     * @param toUserName
     * @param fromUserName
     * @param mediaId
     * @return
     */
    public static String initImage(String toUserName, String fromUserName, String mediaId) {
        Image image = new Image();
        image.setMediaId(mediaId);
        RespImageMessage respImageMessage = new RespImageMessage();
        respImageMessage.setFromUserName(toUserName);
        respImageMessage.setToUserName(fromUserName);
        respImageMessage.setMsgType(EnumUtil.toLowerCaseEnum(MsgTypeEnum.IMAGE));
        respImageMessage.setCreateTime(new Date().getTime());
        respImageMessage.setImage(image);
        return respImageMessageToXml(respImageMessage);
    }

    /**
     * 组装音乐消息
     * @param toUserName
     * @param fromUserName
     * @param music
     * @return
     */
    public static String initMusic(String toUserName, String fromUserName, Music music) {
        RespMusicMessage respMusicMessage = new RespMusicMessage();
        respMusicMessage.setFromUserName(toUserName);
        respMusicMessage.setToUserName(fromUserName);
        respMusicMessage.setMsgType(EnumUtil.toLowerCaseEnum(MsgTypeEnum.MUSIC));
        respMusicMessage.setCreateTime(new Date().getTime());
        respMusicMessage.setMusic(music);
        return respMusicMessageToXml(respMusicMessage);
    }

    /**
     * 组装图文消息
     * @param toUserName
     * @param fromUserName
     * @param articles
     * @return
     */
    public static String initNews(String toUserName, String fromUserName, List<Article> articles) {
        RespNewsMessage respNewsMessage = new RespNewsMessage();
        respNewsMessage.setFromUserName(toUserName);
        respNewsMessage.setToUserName(fromUserName);
        respNewsMessage.setMsgType(EnumUtil.toLowerCaseEnum(MsgTypeEnum.NEWS));
        respNewsMessage.setCreateTime(new Date().getTime());
        respNewsMessage.setArticleCount(articles.size());
        for(int i=0;i<articles.size();i++) {
            Article article = new Article();
            article.setTitle(articles.get(i).getTitle());
            article.setDescription(articles.get(i).getDescription());
            article.setPicUrl(articles.get(i).getPicUrl());
            article.setUrl(articles.get(i).getUrl());
        }
        respNewsMessage.setArticles(articles);
        return respNewsMessageToXml(respNewsMessage);
    }
}