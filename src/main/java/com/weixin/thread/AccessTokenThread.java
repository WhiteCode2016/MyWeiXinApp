package com.weixin.thread;

import com.weixin.dto.AccessToken;
import com.weixin.dto.WeiXinInfo;
import com.weixin.tool.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 保证access_token实时更新
 * Created by White on 2017/2/23.
 */
public class AccessTokenThread implements Runnable{
    private static Logger log = LoggerFactory.getLogger(AccessTokenThread.class);

    public static AccessToken accessToken = null;

    @Override
    public void run() {
        while(true) {
            try {
                accessToken = WeixinUtil.getAccessToken(WeiXinInfo.APPID, WeiXinInfo.APPSECRET);
                if(accessToken != null) {
                    log.info("获取access_token成功，有效时长{}秒 token:{}", accessToken.getExpiresIn(), accessToken.getAccessToken());
                    //休眠7000秒
                    Thread.sleep((accessToken.getExpiresIn() - 200) * 1000);
                } else {
                    //如果access_token为null，60秒后再获取
                    Thread.sleep(60 * 1000);
                }
            } catch (InterruptedException e) {
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e1) {
                    log.error("{}",e1);
                }
                log.error("{}",e);
            }
        }
    }
}
