package com.weixin.tool;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.weixin.dto.message.Article;
import com.weixin.dto.message.resp.RespMusicMessage;
import com.weixin.dto.message.resp.RespNewsMessage;
import com.weixin.dto.message.resp.RespTextMessage;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息工具类
 * Created by White on 2017/2/20.
 */
public class MessageUtil {

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
}
