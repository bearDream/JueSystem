package com.beardream.Controller;

import com.beardream.Utils.ResultUtil;
import com.beardream.Utils.TextUtil;
import com.beardream.dao.DishMapper;
import com.beardream.ioc.*;
import com.beardream.model.*;
import com.beardream.service.DishBusinessService;
import com.beardream.service.DishService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
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

    @Autowired
    private DishBusinessService mDishBusinessService;

    @ApiOperation("获取单个菜品信息")
    @GetMapping(value = "/get")
    @PermissionMethod(text = "获取菜品信息")
    public Result get(Dish dish, BindingResult bindingResult){
        System.out.println(dish.getDishId());
        if (mDishService.find(dish)!=null)
            return ResultUtil.success(mDishService.find(dish));
        else
            return ResultUtil.error(-1,"菜品不存在");
    }

    @ApiOperation("添加菜品")
    @PostMapping
    @PermissionMethod(text = "添加菜品信息")
    public @ResponseBody Result post(@RequestBody Dish dish){
        System.out.println(dish.getDishId());
        Result result = (mDishService.add(dish));
        return result;
    }

    @ApiOperation("删除菜品")
    @DeleteMapping
    @PermissionMethod(text = "删除菜品")
    public Result delete(Dish dish){
        System.out.println(dish.getDishId());
        return ResultUtil.success(mDishService.delete(dish));
    }

    @ApiOperation("更新菜品")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionMethod(text = "更新菜品信息")
    public @ResponseBody Result put(@RequestBody Dish dish){
        System.out.println(dish.getDishId());
        return ResultUtil.success(mDishService.put(dish));
    }

    @ApiOperation("分页获取菜品")
    @GetMapping
    @com.beardream.ioc.Log
    public Result getPage(Dish dish, DishBusiness dishBusiness, @RequestParam(value = "pageNum", defaultValue = "1",required = false)  int pageNum, @RequestParam(value = "pageSize", defaultValue = "10",required = false)  int pageSize, BindingResult bindingResult){
        if (!TextUtil.isEmpty(pageNum) || !TextUtil.isEmpty(pageSize)){
            return ResultUtil.error(-1,"pageNum,pageNum不能为空！");
        }
        // 1、如果传了商家id，则需要联合business_dish表查询，否则不需要
        if (dishBusiness.getBusinessId() == null){
            if (mDishService.getPage(dish, pageNum,pageSize)!=null)
                return ResultUtil.success(mDishService.getPage(dish, pageNum,pageSize));
            else
                return ResultUtil.error(-1,"系统错误");
        }

        // 2、联合business_dish表查询,调用的是dishBusinessService来查询,获取的是该店铺没有的所有菜品
        if (mDishBusinessService.getPage(dishBusiness, pageNum,pageSize)!=null)
            return ResultUtil.success(mDishBusinessService.getPage(dishBusiness, pageNum,pageSize));
        else
            return ResultUtil.error(-1,"系统错误");
    }
}
