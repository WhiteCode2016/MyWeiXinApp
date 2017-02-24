package com.weixin.dto.menu.MenuView;

import com.weixin.dto.menu.BaseButton;

/**
 * view类型的菜单
 * Created by White on 2017/2/22.
 */
public class ViewButton extends BaseButton {
    private String type;
    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
