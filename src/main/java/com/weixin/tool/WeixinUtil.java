package com.weixin.tool;

import com.weixin.dto.AccessToken;
import com.weixin.dto.menu.MenuButton.Menu;
import com.weixin.tool.common.HttpUtil;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 微信公众平台通用接口工具类
 * Created by White on 2017/2/21.
 */
public class WeixinUtil {
    private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);

    // 获取access_token的接口地址（GET） 限200（次/天）
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    // 菜单创建（POST） 限100（次/天）
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * 获取access_token
     * @param appId
     * @param appSecret
     * @return
     */
    public static AccessToken getAccessToken(String appId, String appSecret) {
        AccessToken accessToken = null;
        String requestUrl = access_token_url.replace("APPID", appId).replace("APPSECRET", appSecret);
        JSONObject jsonObject = HttpUtil.httpRequest(requestUrl, "GET", null);
        //如果请求成功
        if(jsonObject != null) {
            try {
                accessToken = new AccessToken();
                accessToken.setAccessToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                //获取失败
                accessToken = null;
                log.error("获取access_token失败 errcode:{} errmsg:{}", jsonObject.getInt("errCode"),jsonObject.getString("errmsg"));
            }
        }
        return accessToken;
    }

    /**
     * 创建菜单
     * @param menu
     * @param accessToken
     * @return
     */
    public static int createMenu(Menu menu, String accessToken) {
        int result = 0;
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        String jsonMenu = JSONObject.fromObject(menu).toString();
        JSONObject jsonObject = HttpUtil.httpRequest(url, "POST", jsonMenu);
        if(jsonObject != null) {
            if(jsonObject.getInt("errcode") != 0) {
                result = jsonObject.getInt("errcode");
                log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errCode"),jsonObject.getString("errmsg"));
            }
        }
        return result;
    }
}
