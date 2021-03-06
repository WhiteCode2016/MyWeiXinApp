package com.weixin.tool;

import com.weixin.dto.AccessToken;
import com.weixin.dto.menu.BaseButton;
import com.weixin.dto.menu.MenuButton.CommonButton;
import com.weixin.dto.menu.MenuButton.ComplexButton;
import com.weixin.dto.menu.MenuButton.Menu;
import com.weixin.dto.menu.MenuView.ViewButton;
import com.weixin.tool.common.EmojiUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 菜单管理
 * Created by White on 2017/2/24.
 */
public class MenuManager {

    private static Logger log = LoggerFactory.getLogger(MenuManager.class);

    public static void main(String[] args) {
        initMenu();
    }

    //初始化菜单
    private static void initMenu() {;

        // 调用接口获取access_token
        AccessToken at = WeixinUtil.getWXAccessToken();

        if (null != at) {
            // 调用接口创建菜单
            int result = WeixinUtil.createMenu(getMenu(), at.getAccess_Token());

            // 判断菜单创建结果
            if (0 == result) {
                log.info("Create Menu Success！");
            } else {
                log.info("Create Menu Failed，errCode：" + result);
            }
        }
    }

    //构造菜单结构
    private static Menu getMenu() {
        CommonButton btn11 = new CommonButton();
        btn11.setName("天气预报");
        btn11.setType("click");
        btn11.setKey("11");

        CommonButton btn12 = new CommonButton();
        btn12.setName("公交查询");
        btn12.setType("click");
        btn12.setKey("12");

        CommonButton btn13 = new CommonButton();
        btn13.setName("百度翻译");
        btn13.setType("click");
        btn13.setKey("13");

        CommonButton btn14 = new CommonButton();
        btn14.setName("历史上的今天");
        btn14.setType("click");
        btn14.setKey("14");

        CommonButton btn15 = new CommonButton();
        btn15.setName("歌曲点播");
        btn15.setType("click");
        btn15.setKey("15");

        CommonButton btn21 = new CommonButton();
        btn21.setName("单图文");
        btn21.setType("click");
        btn21.setKey("21");

        CommonButton btn22 = new CommonButton();
        btn22.setName("多图文");
        btn22.setType("click");
        btn22.setKey("22");

        CommonButton btn23 = new CommonButton();
        btn23.setName("获取IP地址");
        btn23.setType("click");
        btn23.setKey("23");

        CommonButton btn31 = new CommonButton();
        btn31.setName("关于我们");
        btn31.setType("click");
        btn31.setKey("31");

        ViewButton btn32 = new ViewButton();
        btn32.setName("使用帮助");
        btn32.setType("view");
        btn32.setUrl("http://www.baidu.com/");

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("生活助手");
        mainBtn1.setSub_button(new BaseButton[] { btn11, btn12, btn13, btn14 });

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("基础教程");
        mainBtn2.setSub_button(new BaseButton[] { btn21, btn22, btn23 });

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("更多");
        mainBtn3.setSub_button(new BaseButton[] { btn31, btn32 });

        /**
         * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br>
         *
         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
         * menu.setButton(new BaseButton[] { mainBtn1, mainBtn2, btn33 });
         */
        Menu menu = new Menu();
        menu.setButton(new BaseButton[] {mainBtn1, mainBtn2, mainBtn3 });

        return menu;
    }

    //订阅成功，显示的菜单
    public static String getHelpMenu() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("谢谢\ue32e小主\ue32e的关注"+ EmojiUtil.unifiedEmoji(0x2764)).append("\n");
        buffer.append("[玫瑰]/玫瑰/:rose").append("\n");
        buffer.append("\ue312\ue312\ue312").append("\n");
        buffer.append("例如：").append("\n");
        buffer.append(" 输入“翻译+中文或英文”").append("\n");
        buffer.append(" 输入“菜谱+菜名”").append("\n");
        buffer.append(" 点击单图文”").append("\n");
        buffer.append(" 点击多图文”").append("\n");
        return buffer.toString();
    }
}
