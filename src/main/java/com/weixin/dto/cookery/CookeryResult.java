package com.weixin.dto.cookery;

import java.util.List;

/**
 * Created by White on 2017/3/1.
 */
public class CookeryResult {
    List<CookeryData> data;

    public List<CookeryData> getData() {
        return data;
    }

    public void setData(List<CookeryData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CookeryResult{" +
                "data=" + data +
                '}';
    }
}
