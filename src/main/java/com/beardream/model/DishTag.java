package com.beardream.model;

import java.util.Date;

/**
 * Created by soft01 on 2017/5/19.
 */
public class DishTag {
    private Integer dishTagId;

    private String name;

    private String content;

    private Byte dishTagType;

    private Date addTime;

    public Integer getDishTagId() {
        return dishTagId;
    }

    public void setDishTagId(Integer dishTagId) {
        this.dishTagId = dishTagId;
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

    public Byte getDishTagType() {
        return dishTagType;
    }

    public void setDishTagType(Byte dishTagType) {
        this.dishTagType = dishTagType;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
