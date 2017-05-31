package com.beardream.service;

import com.beardream.Utils.ResultUtil;
import com.beardream.Utils.TextUtil;
import com.beardream.dao.DishMapper;
import com.beardream.model.Dish;
import com.beardream.model.Result;
import com.beardream.model.Role;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by soft01 on 2017/5/8.
 */
@Component
@Service
public class DishService {

    @Autowired
    public DishMapper dishMapper;

    public Dish find(Dish dish){
        Dish dishInfo = dishMapper.selectByPrimaryKey(dish.getDishId());
        return dishInfo;
    }

    public Result add(Dish dish){
        int result;
        if (dish == null)
            return ResultUtil.error(-1,"请检查参数是否均已填写");
        // 查找这个菜品的菜品有没有添加过，若添加过则不能重复添加
        Dish exitDish = dishMapper.selectByDishName(dish);
        if (exitDish != null)
            return ResultUtil.error(-1,"该菜品已经添加过了");
        dish.setAddTime(new Date());
        result = dishMapper.insertSelective(dish);
        if (result>0){
            return ResultUtil.success("添加成功");
        }else{
            return ResultUtil.success("添加失败");
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

    public Map getPage(Dish dish, int pageNum, int pageSize){
        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(pageNum , pageSize).setOrderBy("add_time asc");
        List<Dish> dishs =dishMapper.findBySelective(new Dish());
        PageInfo page = new PageInfo(dishs);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page",page);
        return map;
    }

}
