package com.beardream.Controller;

import com.beardream.Utils.ResultUtil;
import com.beardream.dao.DishMapper;
import com.beardream.ioc.PermissionMethod;
import com.beardream.ioc.PermissionModule;
import com.beardream.model.Dish;
import com.beardream.model.DishBusiness;
import com.beardream.model.Result;
import com.beardream.model.User;
import com.beardream.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Created by laxzh on 2017/5/6.
 * 菜品控制器
 */
@RestController
@RequestMapping("/dish")
@Api(value = "菜品服务",description = "提供RESTful风格API的菜品的增删改查服务")
@PermissionModule(text = "菜品管理")
public class DishController {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishService mDishService;

    @ApiOperation("获取单个菜品信息")
    @GetMapping
    @PermissionMethod(text = "获取菜品信息")
    public Result get(Dish dish, BindingResult bindingResult){
        System.out.println(dish.getDishId());
        return ResultUtil.success("请求GetMapping成功");
    }

    @ApiOperation("添加菜品")
    @PostMapping
    @PermissionMethod(text = "添加菜品信息")
    public Result add(){
        System.out.println("this is dish/postMAPPING/");
        return ResultUtil.success("请求postMAPPING成功");
    }

    @ApiOperation("删除菜品")
    @DeleteMapping
    @PermissionMethod(text = "删除菜品")
    public Result delete(){
        System.out.println("this is dish/postMAPPING/");
        return ResultUtil.success("请求DeleteMapping成功");
    }

    @ApiOperation("更新菜品")
    @PutMapping
    @PermissionMethod(text = "更新菜品信息")
    public Result update(){
        System.out.println("this is dish/postMAPPING/");
        return ResultUtil.success("请求PutMapping成功");
    }
}
