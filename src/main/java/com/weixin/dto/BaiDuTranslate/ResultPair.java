package com.weixin.dto.BaiDuTranslate;

/**
 * 翻译结果
 * Created by White on 2017/2/23.
 */
public class ResultPair {
    //原文
    private String src;
    //译文
    private String dst;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }
}