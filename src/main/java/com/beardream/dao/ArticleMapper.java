package com.beardream.dao;

import com.beardream.model.Article;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer articalId);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer articalId);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
}