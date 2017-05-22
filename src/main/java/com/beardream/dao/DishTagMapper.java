package com.beardream.dao;

import com.beardream.model.DishTag;
import com.beardream.model.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by soft01 on 2017/5/19.
 */

@Repository
@Mapper
public interface DishTagMapper {
    int deleteByPrimaryKey(Integer dishTagId);

    int insert(DishTag record);

    int insertSelective(DishTag record);

    List<DishTag> findBySelective(DishTag dishTag);

    DishTag selectByPrimaryKey(Integer DishTag);

    int updateByPrimaryKeySelective(DishTag record);

    int updateByPrimaryKeyWithBLOBs(DishTag record);

    int updateByPrimaryKey(DishTag record);
}
