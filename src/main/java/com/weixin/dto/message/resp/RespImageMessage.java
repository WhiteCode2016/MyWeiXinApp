package com.weixin.dto.message.resp;

import com.weixin.dto.message.Image;

/**
 * Created by White on 2017/2/27.
 */
public class RespImageMessage extends RespBaseMessage {
    private Image Image;

    public com.weixin.dto.message.Image getImage() {
        return Image;
    }

    public void setImage(com.weixin.dto.message.Image image) {
        Image = image;
    }
}
