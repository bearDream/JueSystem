package com.beardream.dao;

import com.beardream.model.Nutrition;

public interface NutritionMapper {
    int deleteByPrimaryKey(Integer nurtritionId);

    int insert(Nutrition record);

    int insertSelective(Nutrition record);

    Nutrition selectByPrimaryKey(Integer nurtritionId);

    int updateByPrimaryKeySelective(Nutrition record);

    int updateByPrimaryKey(Nutrition record);
}