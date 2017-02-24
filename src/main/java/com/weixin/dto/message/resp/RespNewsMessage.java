package com.weixin.dto.message.resp;


import com.weixin.dto.message.Article;

import java.util.List;

/**
 * 图文消息
 * Created by White on 2017/2/20.
 */
public class RespNewsMessage extends RespBaseMessage {
    //图文消息的个数
    private int ArticleCount;
    //多个图文消息，默认第一个为大图显示
    private List<Article> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<Article> getArcicles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }
}
