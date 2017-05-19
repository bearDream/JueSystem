package com.beardream.service;

import com.beardream.Utils.ResultUtil;
import com.beardream.Utils.TextUtil;
import com.beardream.dao.DishTagMapper;
import com.beardream.dao.DishTypeMapper;
import com.beardream.dao.TagMapper;
import com.beardream.model.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by soft01 on 2017/5/17.
 */

@Component
@Service
public class DishTagService {
    @Autowired
    public DishTagMapper dishTagMapper;


    public int deleteByPrimaryKey(DishTag dishTag) {
        return dishTagMapper.deleteByPrimaryKey(dishTag.getDishTagId());
    }


    //获取单个标签信息
    public List find(DishTag dishTag){
        System.out.println(dishTagMapper.selectByPrimaryKey(1));
        List<DishTag> dishTagList = dishTagMapper.findBySelective(dishTag);
        return dishTagList;
    }


    public Map getPage(DishTag dishTag,int pageNum,int pageSize){
        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(pageNum , pageSize).setOrderBy("add_time asc");
        List<DishTag> dishTagList =dishTagMapper.findBySelective(new DishTag());
        PageInfo page = new PageInfo(dishTagList);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page",page);
        return map;
    }
}
