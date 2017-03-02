package com.weixin.tool;

import com.weixin.dto.cookery.CookeryData;
import com.weixin.dto.cookery.CookeryResult;
import com.weixin.dto.cookery.CookerySteps;
import com.weixin.tool.common.HttpUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 聚合菜谱API
 * Created by White on 2017/3/1.
 */
public class CookeryUtil {
    private final static Logger log = LoggerFactory.getLogger(CookeryUtil.class);
    //聚合菜谱appkey
    private final static String APPKEY ="c240a4f3052f94cd0bb2d8e9b20ecde9";
    private final static String cookery_url = "http://apis.juhe.cn/cook/query.php";

    //聚合菜谱接口文档 https://www.juhe.cn/docs/api/id/46
    public static CookeryResult getCookeryResult(String menuName) throws IOException {
        CookeryResult cookeryResult = null;
        Map params = new HashMap();//请求参数
        params.put("menu",menuName);//需要查询的菜谱名
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","");//返回数据的格式,xml或json，默认json
        params.put("pn","");//数据返回起始下标
        params.put("rn","");//数据返回条数，最大30
        params.put("albums","");//albums字段类型，1字符串，默认数组
        JSONObject jsonObject = HttpUtil.httRequestTo3API(cookery_url, params, "GET");
        try {

            Map<String, Class> classMap = new HashMap<String, Class>();
            classMap.put("data", CookeryData.class);
            classMap.put("steps", CookerySteps.class);
            cookeryResult = (CookeryResult) JSONObject.toBean(jsonObject.getJSONObject("result"),CookeryResult.class, classMap);
        } catch (Exception e) {
            log.error("获取translate失败\n error_code:{}\n reason:{}",jsonObject.getInt("error_code"),jsonObject.getString("reason"));
        }
        return cookeryResult;
    }
}
