package com.beardream.dao;

import com.beardream.model.Log;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LogMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKeyWithBLOBs(Log record);

    int updateByPrimaryKey(Log record);
}