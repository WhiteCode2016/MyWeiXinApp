package com.weixin.tool.common;

import com.weixin.dto.AccessToken;
import com.weixin.dto.WeiXinInfo;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 微信基础API （包括：access_Token）
 * Created by White on 2017/3/2.
 */
public class WXBaseAPI {
    private final static Logger log = LoggerFactory.getLogger(WXBaseAPI.class);

    // 获取access_token的接口地址（GET） 限200（次/天）
    protected final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    public static AccessToken getWXAccessToken() {
        AccessToken accessToken = null;
        String requestUrl = ACCESS_TOKEN_URL.replace("APPID", WeiXinInfo.APPID).replace("APPSECRET", WeiXinInfo.APPSECRET);
        JSONObject jsonObject = HttpUtil.httpRequest(requestUrl, "GET", null);
        //如果请求成功
        if(jsonObject != null) {
            try {
                accessToken = (AccessToken) JSONObject.toBean(jsonObject, AccessToken.class);
            } catch (JSONException e) {
                //获取失败
                accessToken = null;
                log.error("Get access_token Failed! errcode:{} errmsg:{}", jsonObject.getInt("errCode"),jsonObject.getString("errmsg"));
            }
        }
        return accessToken;
    }
}
