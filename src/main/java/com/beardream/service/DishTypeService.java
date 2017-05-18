package com.beardream.service;

import com.beardream.Utils.ResultUtil;
import com.beardream.dao.DishTypeMapper;
import com.beardream.model.DishType;
import com.beardream.model.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by soft01 on 2017/5/17.
 */
@Component
@Service
public class DishTypeService {

    @Autowired
    private DishTypeMapper dishTypeMapper;

    public Result getPage(int pageNum,int pageSize){
        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(pageNum , pageSize).setOrderBy("add_time asc");
        List<DishType> dishTypes =dishTypeMapper.findBySelective(new DishType());
        PageInfo page = new PageInfo(dishTypes);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page",page);
        //map.put("list",dishTypes);
        return ResultUtil.success(map);
    }

}
