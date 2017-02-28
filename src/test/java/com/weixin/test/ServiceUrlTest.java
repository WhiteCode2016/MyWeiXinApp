package com.weixin.test;

import com.weixin.dto.AccessToken;
import com.weixin.dto.WeiXinInfo;
import com.weixin.tool.WeixinUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created by White on 2017/2/28.
 */
public class ServiceUrlTest {
    public static void main(String[] args) {
        AccessToken at = WeixinUtil.getAccessToken(WeiXinInfo.APPID, WeiXinInfo.APPSECRET);
        JSONObject jsonObject = WeixinUtil.getServiceUrl(at.getAccessToken());
        JSONArray jsonArrays = jsonObject.getJSONArray("ip_list");
        StringBuffer buffer = new StringBuffer();
        for (int i=0;i<jsonArrays.size();i++) {
            buffer.append(jsonArrays.get(i)).append("\n");
        }
        System.out.println(buffer.toString());
    }
}
