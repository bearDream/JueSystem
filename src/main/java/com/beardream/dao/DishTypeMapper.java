package com.beardream.dao;

import com.beardream.model.DishType;

public interface DishTypeMapper {
    int deleteByPrimaryKey(Integer dishtypeId);

    int insert(DishType record);

    int insertSelective(DishType record);

    DishType selectByPrimaryKey(Integer dishtypeId);

    int updateByPrimaryKeySelective(DishType record);

    int updateByPrimaryKey(DishType record);
}