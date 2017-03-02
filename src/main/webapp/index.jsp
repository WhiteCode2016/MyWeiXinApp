<%--
  Created by IntelliJ IDEA.
  User: White
  Date: 2017/2/24
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>TEST</title>
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
    <script type="text/javascript">
        wx.config({
            debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: 'wxff19d17ccdebd7bd', // 必填，公众号的唯一标识
            timestamp: new Date().getTime()/1000, // 必填，生成签名的时间戳
            nonceStr: '', // 必填，生成签名的随机串
            signature: '',// 必填，签名，见附录1
            jsApiList: [] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
    </script>
  </head>
  <body>
    欢迎来到微信公众平台...
  </body>
</html>
