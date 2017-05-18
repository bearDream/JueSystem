package com.beardream.Controller;

import com.beardream.Utils.ResultUtil;
import com.beardream.Utils.TextUtil;
import com.beardream.dao.DishMapper;
import com.beardream.ioc.*;
import com.beardream.model.*;
import com.beardream.service.DishService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by laxzh on 2017/5/6.
 * 菜品控制器
 */
@RestController
@RequestMapping("/api/dish")
@Api(value = "菜品服务",description = "提供RESTful风格API的菜品的增删改查服务")
@PermissionModule(text = "菜品管理")
public class DishController {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishService mDishService;

    @ApiOperation("获取单个菜品信息")
    @GetMapping(value = "/{dishId}")
    @PermissionMethod(text = "获取菜品信息")
    public Result get(Dish dish, BindingResult bindingResult){
        System.out.println(dish.getDishId());
        return ResultUtil.success(mDishService.find(dish));
    }

    @ApiOperation("添加菜品")
    @PostMapping
    @PermissionMethod(text = "添加菜品信息")
    public Result post(Dish dish){
        System.out.println(dish.getDishId());
        return ResultUtil.success(mDishService.post(dish));
    }

    @ApiOperation("删除菜品")
    @DeleteMapping
    @PermissionMethod(text = "删除菜品")
    public Result delete(Dish dish){
        System.out.println(dish.getDishId());
        return ResultUtil.success(mDishService.delete(dish));
    }

    @ApiOperation("更新菜品")
    @PutMapping
    @PermissionMethod(text = "更新菜品信息")
    public Result put(Dish dish){
        System.out.println(dish.getDishId());
        return ResultUtil.success(mDishService.put(dish));
    }

    @ApiOperation("分页获取菜品")
    @GetMapping
    @com.beardream.ioc.Log
    public Result getPage(Dish dish, @RequestParam(value = "pageNum", defaultValue = "1",required = false)  int pageNum, @RequestParam(value = "pageSize", defaultValue = "10",required = false)  int pageSize, BindingResult bindingResult){
        if (!TextUtil.isEmpty(pageNum) || !TextUtil.isEmpty(pageSize)){
            return ResultUtil.error(-1,"pageNum,pageNum不能为空！");
        }
        if (mDishService.getPage(dish, pageNum,pageSize)!=null)
            return ResultUtil.success(mDishService.getPage(dish, pageNum,pageSize));
        else
            return ResultUtil.error(-1,"系统错误");
    }
}
