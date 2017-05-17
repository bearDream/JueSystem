package com.beardream.dao;

import com.beardream.model.Business;
import com.beardream.model.BusinessType;
import com.beardream.model.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BusinessTypeMapper {
    int deleteByPrimaryKey(Integer businessTypeId);

    int insert(BusinessType record);

    int insertSelective(BusinessType record);

    BusinessType selectByPrimaryKey(Integer businessTypeId);

    List<BusinessType> findBySelective(BusinessType businessType);

    int updateByPrimaryKeySelective(BusinessType record);

    int updateByPrimaryKeyWithBLOBs(BusinessType record);

    int updateByPrimaryKey(BusinessType record);
}