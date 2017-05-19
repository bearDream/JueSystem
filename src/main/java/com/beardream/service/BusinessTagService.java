package com.beardream.service;

import com.beardream.dao.BusinessTagMapper;
import com.beardream.dao.DishTagMapper;
import com.beardream.model.BusinessTag;
import com.beardream.model.DishTag;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by soft01 on 2017/5/19.
 */
@Component
@Service
public class BusinessTagService {
    @Autowired
    public BusinessTagMapper businessTagMapper;




    public int deleteTag(BusinessTag businessTag) {
        return businessTagMapper.deleteByPrimaryKey(businessTag.getBusinessTagId());
    }


    //获取单个标签信息
    public List find(BusinessTag businessTag){
        System.out.println(businessTagMapper.selectByPrimaryKey(1));
        List<BusinessTag> businessTags = businessTagMapper.findBySelective(businessTag);
        return businessTags;
    }


    public Map getPage(BusinessTag businessTag, int pageNum, int pageSize){
        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(pageNum , pageSize).setOrderBy("add_time asc");
        List<BusinessTag> businessTags =businessTagMapper.findBySelective(new BusinessTag());
        PageInfo page = new PageInfo(businessTags);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page",page);
        return map;
    }
}
