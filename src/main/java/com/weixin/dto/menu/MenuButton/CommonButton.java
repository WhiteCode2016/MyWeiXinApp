package com.weixin.dto.menu.MenuButton;

import com.weixin.dto.menu.BaseButton;

/**
 * 普通按钮（子按钮）
 * Created by White on 2017/2/21.
 */
public class CommonButton extends BaseButton {
    private String type;
    private String key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
