package com.weixin.test;

import com.weixin.dto.AccessToken;
import com.weixin.tool.WeixinUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 获取服务器地址测试
 * Created by White on 2017/2/28.
 */
public class ServiceUrlTest {
    public static void main(String[] args) {
        AccessToken at = WeixinUtil.getWXAccessToken();
        JSONObject jsonObject = WeixinUtil.getServiceUrl(at.getAccess_Token());
        JSONArray jsonArrays = jsonObject.getJSONArray("ip_list");
        StringBuffer buffer = new StringBuffer();
        for (int i=0;i<jsonArrays.size();i++) {
            buffer.append(jsonArrays.get(i)).append("\n");
        }
        System.out.println(buffer.toString());
    }
}
