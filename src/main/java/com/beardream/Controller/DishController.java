package com.beardream.Controller;

import com.beardream.Utils.ResultUtil;
import com.beardream.model.Result;
import com.beardream.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
public class DishController {

    @ApiOperation("获取单个菜品信息")
    @GetMapping
    public Result get(User user, BindingResult bindingResult){
        System.out.println("this is dish/get/");
        return ResultUtil.success("请求GetMapping成功");
    }

    @ApiOperation("添加菜品")
    @PostMapping
    public Result add(){
        System.out.println("this is dish/postMAPPING/");
        return ResultUtil.success("请求postMAPPING成功");
    }

    @ApiOperation("删除菜品")
    @DeleteMapping
    public Result delete(){
        System.out.println("this is dish/postMAPPING/");
        return ResultUtil.success("请求DeleteMapping成功");
    }

    @ApiOperation("更新菜品")
    @PutMapping
    public Result update(){
        System.out.println("this is dish/postMAPPING/");
        return ResultUtil.success("请求PutMapping成功");
    }
}
