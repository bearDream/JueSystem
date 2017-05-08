package com.beardream.Controller;

import com.beardream.Utils.ResultUtil;
import com.beardream.ioc.PermissionMethod;
import com.beardream.ioc.PermissionModule;
import com.beardream.model.Result;
import com.beardream.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Created by laxzh on 2017/5/6.
 * 标签控制器
 */
@RestController
@RequestMapping("/tag")
@Api(value = "标签服务",description = "提供RESTful风格API的标签的增删改查服务")
@PermissionModule(text = "标签管理")
public class TagController {

    @ApiOperation("获取单个标签")
    @GetMapping
    @PermissionMethod(text = "获取标签信息")
    public Result get(User user, BindingResult bindingResult){
        return ResultUtil.success("请求GetMapping成功");
    }

    @ApiOperation("添加标签")
    @PostMapping
    @PermissionMethod(text = "添加标签")
    public Result add(){
        return ResultUtil.success("请求postMAPPING成功");
    }

    @ApiOperation("删除标签")
    @DeleteMapping
    @PermissionMethod(text = "删除标签")
    public Result delete(){
        return ResultUtil.success("请求DeleteMapping成功");
    }

    @ApiOperation("更新标签")
    @PutMapping
    @PermissionMethod(text = "更新标签")
    public Result update(){
        return ResultUtil.success("请求PutMapping成功");
    }
}
