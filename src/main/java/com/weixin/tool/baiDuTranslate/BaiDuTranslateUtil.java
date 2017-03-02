package com.weixin.tool.baiDuTranslate;

import com.weixin.dto.BaiDuTranslate.BaiduTranslateInfo;
import com.weixin.dto.BaiDuTranslate.ResultPair;
import com.weixin.dto.BaiDuTranslate.TranslateResult;
import com.weixin.tool.common.HttpUtil;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 百度翻译工具类
 * Created by White on 2017/2/27.
 */
public class BaiDuTranslateUtil {
    private final static Logger log = LoggerFactory.getLogger(BaiDuTranslateUtil.class);

    //百度翻译接口文档 http://api.fanyi.baidu.com/api/trans/product/apidoc
    private final static String baiduTranslate_url = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    public static TranslateResult getTranslateResult(String query, String from, String to) {
        TranslateResult translateResult = null;
        //请求参数封装
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);
        params.put("appid", BaiduTranslateInfo.APPID);
        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);
        // 签名
        String src = BaiduTranslateInfo.APPID + query + salt + BaiduTranslateInfo.SECURITYKEY; // 加密前的原文
        params.put("sign", MD5Util.md5(src));
        JSONObject jsonObject =  HttpUtil.httRequestTo3API(baiduTranslate_url, params, "GET");
        if(jsonObject != null) {
            try {
                //解决net.sf.ezmorph.bean.MorphDynaBean异常问题
                //在JSONObject.toBean的时候如果转换的类中有集合,
                // 可以先定义Map<String, Class> classMap = new HashMap<String, Class>();在classMap中put你要转换的类中的集合名
                Map<String, Class> classMap = new HashMap<String, Class>();
                classMap.put("trans_result", ResultPair.class);
                translateResult = (TranslateResult) JSONObject.toBean(jsonObject, TranslateResult.class, classMap);
            } catch (JSONException e) {
                //获取失败
                log.error("获取translate失败\n error_code_msg:{}\nclient_ip:{}", jsonObject.getInt("error_code")+" "+jsonObject.getString("error_msg"),
                        jsonObject.getJSONObject("data").getString("client_ip"));
            }
        }
        return translateResult;
    }
}
