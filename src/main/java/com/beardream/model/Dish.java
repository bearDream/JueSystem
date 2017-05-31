package com.beardream.model;

import java.util.Date;

public class Dish {
    private Integer dishId;

    private String dishName;

    private String dishDesc;

    private Integer dishtypeId;

    private String dishImage;

    private Date addTime;

    private String dishContent;

    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getDishtypeId() {
        return dishtypeId;
    }

    public void setDishtypeId(Integer dishtypeId) {
        this.dishtypeId = dishtypeId;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName == null ? null : dishName.trim();
    }

    public String getDishDesc() {
        return dishDesc;
    }

    public void setDishDesc(String dishDesc) {
        this.dishDesc = dishDesc == null ? null : dishDesc.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getDishContent() {
        return dishContent;
    }

    public void setDishContent(String dishContent) {
        this.dishContent = dishContent == null ? null : dishContent.trim();
    }

    public String getDishImage() {
        return dishImage;
    }

    public void setDishImage(String dishImage) {
        this.dishImage = dishImage;
    }
}