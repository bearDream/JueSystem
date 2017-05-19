package com.beardream.model;

import java.util.Date;

/**
 * Created by soft01 on 2017/5/19.
 */
public class BusinessTag {
    private Integer businessTagId;

    private String name;

    private String content;

    private Byte businessTagType;

    private Date addTime;

    public Integer getBusinessTagId() {
        return businessTagId;
    }

    public void setBusinessTagId(Integer businessTagId) {
        this.businessTagId = businessTagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Byte getBusinessTagType() {
        return businessTagType;
    }

    public void setBusinessTagType(Byte businessTagType) {
        this.businessTagId = businessTagId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
