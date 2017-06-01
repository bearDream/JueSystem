package com.beardream.service;

import com.beardream.Utils.ResultUtil;
import com.beardream.dao.DishBusinessMapper;
import com.beardream.dao.DishMapper;
import com.beardream.dao.NutritionMapper;
import com.beardream.model.Dish;
import com.beardream.model.DishBusiness;
import com.beardream.model.Nutrition;
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
 * Created by soft01 on 2017/5/8.
 */
@Component
@Service
public class DishBusinessService {

    @Autowired
    public DishBusinessMapper mDishBusinessMapper;

    public DishBusiness find(DishBusiness dishBusiness){
        DishBusiness dishInfo = mDishBusinessMapper.selectByPrimaryKey(dishBusiness.getDishId());
        return dishInfo;
    }

    // 添加一个菜品给一个商家
    public Result add(DishBusiness dishBusiness){
        if (mDishBusinessMapper.insert(dishBusiness) == 1){
            return ResultUtil.success("加入成功");
        }
        return ResultUtil.error(-1,"加入失败");
    }

    public Result delete(DishBusiness dishBusiness){
        if (mDishBusinessMapper.deleteByPrimaryKey(dishBusiness.getDishBusinessId()) == 1){
            return ResultUtil.success("删除成功");
        }
        return ResultUtil.error(-1,"删除失败");
    }

//
//    public String put(Dish dish){
//        int result;
//        System.out.println(dish.getDishId());
//        result = dishMapper.updateByPrimaryKeySelective(dish);
//        if (result > 0) {
//            return "更新成功";
//        }else {
//            return "更新失败";
//        }
//    }
//
    public Map getPage(DishBusiness dishBusiness, int pageNum, int pageSize){
        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(pageNum , pageSize).setOrderBy("addtime asc");
        List<DishBusiness> dishs =mDishBusinessMapper.findBusinessDish(dishBusiness);
        PageInfo page = new PageInfo(dishs);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page",page);
        return map;
    }

}
