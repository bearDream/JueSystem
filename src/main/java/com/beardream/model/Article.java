package com.beardream.model;

import java.util.Date;

public class Article {
    private Integer articleId;

    private Integer userId;

    private String coverImage;

    private Byte isShow;

    private String content;

    private Date addTime;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage == null ? null : coverImage.trim();
    }

    public Byte getIsShow() {
        return isShow;
    }

    public void setIsShow(Byte isShow) {
        this.isShow = isShow;
    }

    public String getContent() {
        return content;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}