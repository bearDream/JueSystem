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

import java.util.Date;
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

    public DishType find(DishType dishType){
        DishType dishTypeInfo = dishTypeMapper.selectByPrimaryKey(dishType.getDishtypeId());
        return dishTypeInfo;
    }

    public Result add(DishType dishType){
        if (dishType==null)
            return  ResultUtil.error(-1,"没有参数");
        DishType dishType1 = dishTypeMapper.selectByTypeName(dishType);
        if (dishType1 != null)
            return ResultUtil.error(-1,"该种类已存在");
        dishType.setAddTime(new Date());
        int result = dishTypeMapper.insertSelective(dishType);
        if (result > 0)
            return ResultUtil.success("添加成功");
        else
            return ResultUtil.error(-1,"添加失败");
    }

    public Map getPage(DishType dishType, int pageNum,int pageSize){
        PageHelper.startPage(pageNum , pageSize).setOrderBy("add_time asc");
        List<DishType> dishTypes =dishTypeMapper.findBySelective(dishType);
        PageInfo page = new PageInfo(dishTypes);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page",page);
        return map;
    }

    public List<DishType> getAllType(){
        List<DishType> dishTypes =dishTypeMapper.findBySelective(null);
        return dishTypes;
    }


}
