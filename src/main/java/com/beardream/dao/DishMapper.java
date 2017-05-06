package com.beardream.dao;

import com.beardream.model.Dish;

public interface DishMapper {
    int deleteByPrimaryKey(Integer dishId);

    int insert(Dish record);

    int insertSelective(Dish record);

    Dish selectByPrimaryKey(Integer dishId);

    int updateByPrimaryKeySelective(Dish record);

    int updateByPrimaryKeyWithBLOBs(Dish record);

    int updateByPrimaryKey(Dish record);
}