package com.weixin.test;

import com.weixin.dto.cookery.CookeryData;
import com.weixin.dto.cookery.CookeryResult;
import com.weixin.tool.CookeryUtil;

import java.io.IOException;

/**
 * 菜谱查询测试
 * Created by White on 2017/3/1.
 */
public class CookeryTest {

    public static void main(String[] args) throws IOException {
        CookeryResult cookeryResult = CookeryUtil.getCookeryResult("秘制红烧肉");
        CookeryData data = cookeryResult.getData().get(0);
        System.out.println(data.getTitle()+" "+data.getTags()+" "+data.getAlbums().get(0)+" "+data.getSteps().get(0).getImg());
    }
}
