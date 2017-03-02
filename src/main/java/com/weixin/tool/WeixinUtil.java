package com.weixin.tool;

import com.weixin.dto.JsapiTicket;
import com.weixin.dto.menu.MenuButton.Menu;
import com.weixin.tool.common.WXBaseAPI;
import com.weixin.tool.common.HttpUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 微信公众平台通用接口工具类
 * Created by White on 2017/2/21.
 */
public class WeixinUtil extends WXBaseAPI {
    private final static Logger log = LoggerFactory.getLogger(WeixinUtil.class);

    // 获取access_token的接口地址（GET） 限200（次/天）
    protected final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    // 菜单创建（POST） 限100（次/天）
    protected final static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    // 获取微信服务器地址
    protected final static String service_url = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN";
    // 获取jsapi_ticket的接口地址
    protected final static String ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";


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

    public static JSONObject getServiceUrl(String accessToken) {
        String url = service_url.replace("ACCESS_TOKEN", accessToken);
        JSONObject jsonObject = HttpUtil.httpRequest(url, "POST", null);
        return jsonObject;
    }

    public static JsapiTicket getTicket(String accessToken) {
        String url = ticket_url.replace("ACCESS_TOKEN", accessToken);
        JSONObject jsonObject = HttpUtil.httpRequest(url, "GET", null);
        return (JsapiTicket) JSONObject.toBean(jsonObject, JsapiTicket.class);
    }
}
