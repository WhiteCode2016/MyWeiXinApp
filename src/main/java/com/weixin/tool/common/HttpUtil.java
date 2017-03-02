package com.weixin.tool.common;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;

/**
 * http工具类
 * Created by White on 2017/2/27.
 */
public class HttpUtil {
    private static Logger log = LoggerFactory.getLogger(HttpUtil.class);
    private final static String REQUEST_GET = "GET";
    private final static String REQUEST_POST = "POST";
    private final static String CODING_UTF_8 = "UTF-8";
    private final static int CONN_TIMEOUT = 30000;
    private final static int READ_TOMEOUT = 30000;

    // 获取SSLSocketFactory
    private static SSLSocketFactory getSSLSocketFactory() {
        SSLSocketFactory ssf = null;
        try {
            // 设置SSLContext
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, new TrustManager[] { new MyX509TrustManager() }, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            ssf = sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return ssf;
    }

    //微信请求调用
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            // 获取SSLSocketFactory
            SSLSocketFactory ssf = getSSLSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);
            if (REQUEST_GET.equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();
            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes(CODING_UTF_8));
                outputStream.close();
            }
            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, CODING_UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer buffer = new StringBuffer();
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            log.error("Weixin server connection timed out.");
        } catch (Exception e) {
            log.error("https request error:{}", e);
        }
        return jsonObject;
    }

    // 百度翻译 聚合菜谱 第三方API请求调用
    public static JSONObject httRequestTo3API(String requestUrl, Map<String, String> params, String method) {
        JSONObject jsonObject = null;
        try {
            // 获取SSLSocketFactory
            SSLSocketFactory ssf = getSSLSocketFactory();
            if(method==null || method.equals(REQUEST_GET)){
                requestUrl = requestUrl+"?"+urlencode(params);
            }
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            if (httpUrlConn instanceof HttpsURLConnection) {
                ((HttpsURLConnection) httpUrlConn).setSSLSocketFactory(ssf);
            }
            if(method==null || method.equals(REQUEST_GET)){
                httpUrlConn.setRequestMethod(REQUEST_GET);
            }else{
                httpUrlConn.setRequestMethod(REQUEST_POST);
                httpUrlConn.setDoOutput(true);
            }
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setConnectTimeout(CONN_TIMEOUT);
            httpUrlConn.setReadTimeout(READ_TOMEOUT);
            httpUrlConn.setInstanceFollowRedirects(false);
            httpUrlConn.connect();
            if (params!= null && method.equals(REQUEST_POST)) {
                try {
                    DataOutputStream out = new DataOutputStream(httpUrlConn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = httpUrlConn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, CODING_UTF_8));
            StringBuffer buffer = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
            jsonObject = JSONObject.fromObject(buffer.toString());
            close(br); // 关闭数据流
            close(is); // 关闭数据流
            httpUrlConn.disconnect(); // 断开连接
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    // 关闭流封装
    protected static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 将map型转为请求参数型
    public static String urlencode(Map<String,String> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
