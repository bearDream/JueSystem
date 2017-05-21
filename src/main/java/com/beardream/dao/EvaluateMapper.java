package com.beardream.dao;

import com.beardream.model.Evaluate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface EvaluateMapper {
    int deleteByPrimaryKey(Integer evaluateId);

    int insert(Evaluate record);

    int insertSelective(Evaluate record);

    Evaluate selectByPrimaryKey(Integer evaluateId);

    int updateByPrimaryKeySelective(Evaluate record);

    int updateByPrimaryKeyWithBLOBs(Evaluate record);

    int updateByPrimaryKey(Evaluate record);
}