package com.weixin.dto.menu.MenuButton;

import com.weixin.dto.menu.BaseButton;

/**
 * 复杂按钮（父按钮）
 * Created by White on 2017/2/21.
 */
public class ComplexButton extends BaseButton {
    private BaseButton[] sub_button;

    public BaseButton[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(BaseButton[] sub_button) {
        this.sub_button = sub_button;
    }
}
