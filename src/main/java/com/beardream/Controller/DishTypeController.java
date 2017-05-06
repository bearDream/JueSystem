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
 * 菜品类别控制器
 */
@RestController
@RequestMapping("/dishtype")
@Api(value = "菜品分类服务",description = "提供RESTful风格API的商家的增删改查服务")
public class DishTypeController {

    @ApiOperation("获取单个菜品分类信息")
    @GetMapping
    public Result get(User user, BindingResult bindingResult){
        return ResultUtil.success("请求GetMapping成功");
    }

    @ApiOperation("添加菜品分类")
    @PostMapping
    public Result add(){
        return ResultUtil.success("请求postMAPPING成功");
    }

    @ApiOperation("删除菜品分类")
    @DeleteMapping
    public Result delete(){
        return ResultUtil.success("请求DeleteMapping成功");
    }

    @ApiOperation("更新菜品分类")
    @PutMapping
    public Result update(){
        return ResultUtil.success("请求PutMapping成功");
    }
}
