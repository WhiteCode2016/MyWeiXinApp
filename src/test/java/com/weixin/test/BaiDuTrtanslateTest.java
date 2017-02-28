package com.weixin.test;

import com.weixin.dto.BaiDuTranslate.ResultPair;
import com.weixin.tool.baiDuTranslate.BaiDuTranslateUtil;

import java.util.List;

/**
 * 百度翻译测试
 * Created by White on 2017/2/23.
 */
public class BaiDuTrtanslateTest {
    public static void main(String[] args) {

        StringBuffer buffer = new StringBuffer();
        List<ResultPair> resultPairs = BaiDuTranslateUtil.getTranslateResult(" 今天\naction\n昨天", "auto", "auto").getTrans_result();
        for(int i=0;i<resultPairs.size();i++) {
            buffer.append(resultPairs.get(i).getDst()).append("\n");
        }
        System.out.println(buffer.toString());
    }
}
