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
 * 商家控制器
 */
@RestController
@RequestMapping("/business")
@Api(value = "商家服务",description = "提供RESTful风格API的商家的增删改查服务")
public class BuisnessController {

    @ApiOperation("获取单个商家信息")
    @GetMapping
    public Result get(User user, BindingResult bindingResult){
        return ResultUtil.success("请求GetMapping成功");
    }

    @ApiOperation("添加商家")
    @PostMapping
    public Result add(){
        return ResultUtil.success("请求postMAPPING成功");
    }

    @ApiOperation("删除商家")
    @DeleteMapping
    public Result delete(){
        return ResultUtil.success("请求DeleteMapping成功");
    }

    @ApiOperation("更新商家")
    @PutMapping
    public Result update(){
        return ResultUtil.success("请求PutMapping成功");
    }
}
