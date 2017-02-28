package com.weixin.dto.BaiDuTranslate;

import java.util.List;

/**
 * 百度翻译结果类
 * Created by White on 2017/2/23.
 */
public class TranslateResult {
    //实际采用的源语言
    private String from;
    //实际采用的目标语言
    private String to;
    //结果体
    private List<ResultPair> trans_result;

    public String getForm() {
        return from;
    }

    public void setForm(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<ResultPair> getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(List<ResultPair> trans_result) {
        this.trans_result = trans_result;
    }

    @Override
    public String toString() {
        return "TranslateResult{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", trans_result=" + trans_result +
                '}';
    }
}
