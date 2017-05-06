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
 * 营养价值控制器
 */
@RestController
@RequestMapping("/nutrition")
@Api(value = "营养价值服务",description = "提供RESTful风格API的营养价值的增删改查服务")
public class NutritionController {

    @ApiOperation("获取单个营养价值")
    @GetMapping
    public Result get(User user, BindingResult bindingResult){
        return ResultUtil.success("请求GetMapping成功");
    }

    @ApiOperation("添加营养价值")
    @PostMapping
    public Result add(){
        return ResultUtil.success("请求postMAPPING成功");
    }

    @ApiOperation("删除营养价值")
    @DeleteMapping
    public Result delete(){
        return ResultUtil.success("请求DeleteMapping成功");
    }

    @ApiOperation("更新营养价值")
    @PutMapping
    public Result update(){
        return ResultUtil.success("请求PutMapping成功");
    }
}
