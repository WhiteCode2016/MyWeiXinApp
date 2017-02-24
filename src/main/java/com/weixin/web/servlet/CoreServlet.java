package com.weixin.web.servlet;

import com.weixin.service.CoreService;
import com.weixin.thread.AccessTokenThread;
import com.weixin.tool.SignUtil;
import com.weixin.tool.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 核心请求处理类
 * Created by White on 2017/2/20.
 */
@WebServlet(urlPatterns = "/CoreServlet")
public class CoreServlet extends HttpServlet {
    private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);

    @Override
    public void init() throws ServletException {
        log.info("weixin api appId:{}", AccessTokenThread.appId);
        log.info("weixin api appSecret:{}", AccessTokenThread.appSecret);

        //未配置appId、appSecret时给出提示
        if("".equals(AccessTokenThread.appId) || "".equals(AccessTokenThread.appSecret)) {
            log.error("appId and appSecret configuration error, please check carefully.");
        } else {
            //启动定时获取access_token的线程
            new Thread(new AccessTokenThread()).start();
        }
    }

    /**
     * 确认请求来自微信服务器
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("开始校验签名");
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            System.out.println("签名校验通过");
            out.print(echostr);
        }else {
            System.out.println("签名校验失败");
        }
        out.close();
        out = null;
    }

    /**
     * 处理微信服务器发来的消息
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //调用核心业务类接受消息并处理
        String respMessage = CoreService.processRequest(request);
        //响应消息
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
//        System.out.println("Service...");
    }
}
