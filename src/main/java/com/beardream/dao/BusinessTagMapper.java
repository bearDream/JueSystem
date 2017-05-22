package com.beardream.dao;

import com.beardream.model.BusinessTag;
import com.beardream.model.DishTag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by soft01 on 2017/5/19.
 */
@Repository
@Mapper
public interface BusinessTagMapper {
    int deleteByPrimaryKey(Integer businessTagId);

    int insert(BusinessTag record);

    int insertSelective(BusinessTag record);

    List<BusinessTag> findBySelective(BusinessTag businessTag);

    BusinessTag selectByPrimaryKey(Integer BusinessTag);

    int updateByPrimaryKeySelective(BusinessTag record);

    int updateByPrimaryKeyWithBLOBs(BusinessTag record);

    int updateByPrimaryKey(BusinessTag record);

}
