package com.beardream.service;

import com.beardream.Utils.ResultUtil;
import com.beardream.Utils.TextUtil;
import com.beardream.dao.DishMapper;
import com.beardream.model.Dish;
import com.beardream.model.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * Created by soft01 on 2017/5/8.
 */
@Component
@Service
public class DishService {

    @Autowired
    public DishMapper dishMapper;

    public String post(Dish dish){
        int result;
        if (dish==null)
            return "没有参数";
        List<Dish> dishList = dishMapper.findBySelective(dish);
        if (dishList.size()>0)
            return "菜品已存在";
        dish.setAddTime(new Date());
        result = dishMapper.insertSelective(dish);
        if (result>0){
            return "添加成功";
        }else{
            return "添加失败";
        }
    }

    public String delete(Dish dish){
        int result;
        result = dishMapper.deleteByPrimaryKey(dish.getDishId());
        if (result > 0) {
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    public String put(Dish dish){
        int result;
        System.out.println(dish.getDishId());
        result = dishMapper.updateByPrimaryKeySelective(dish);
        if (result > 0) {
            return "更新成功";
        }else {
            return "更新失败";
        }
    }

    public Result getPage(int pageNum,int pageSize){
        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(pageNum , pageSize).setOrderBy("add_time asc");
        List<Dish> dishs =dishMapper.findBySelective(new Dish());
        PageInfo page = new PageInfo(dishs);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page",page);
        //map.put("list",dishs);
        return ResultUtil.success(map);
    }
}



