package com.beardream.Controller;

import com.beardream.Utils.ResultUtil;
import com.beardream.Utils.TextUtil;
import com.beardream.dao.BusinessMapper;
import com.beardream.dao.DishTagMapper;
import com.beardream.dao.TagMapper;
import com.beardream.ioc.PermissionMethod;
import com.beardream.ioc.PermissionModule;
import com.beardream.model.*;
import com.beardream.service.DishTagService;
import com.beardream.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by laxzh on 2017/5/6.
 * 标签控制器
 */
@RestController
@RequestMapping("/api/dishtag")
@Api(value = "菜品标签服务",description = "提供RESTful风格API的标签的增删改查服务")
@PermissionModule(text = "菜品标签管理")
public class DishTagController {
    @Autowired
    private DishTagMapper dishTagMapper;
    @Autowired
    private DishTagService dishTagService;

    @ApiOperation("获取单个菜品标签信息")
    @GetMapping(value = "/get")
    @PermissionMethod(text = "获取菜品标签信息")
    public Result get(DishTag dishTag, BindingResult bindingResult){
        System.out.println(dishTag.getDishTagId());
        return ResultUtil.success(dishTagService.find(dishTag));
    }


    @ApiOperation("添加标签")
    @PostMapping
    @PermissionMethod(text = "添加标签")
    public Result add(DishTag dishTag){
        int result;
        if (dishTag==null)
            return  ResultUtil.error(-1,"没有参数");
        List<DishTag> t = dishTagMapper.findBySelective(dishTag);
        if (t.size()>0)
            return ResultUtil.error(-1,"标签已存在");
        dishTag.setAddTime(new Date());
        result = dishTagMapper.insertSelective(dishTag);
        if (result>0)
            return ResultUtil.success("添加成功");
        else
            return  ResultUtil.error(-1,"添加失败");

    }

    @ApiOperation("删除标签")
    @DeleteMapping
    @PermissionMethod(text = "删除标签")
    public Result delete(DishTag dishTag){
        int result;
        result = dishTagMapper.deleteByPrimaryKey(dishTag.getDishTagId());
        if (result> 0 )
            return ResultUtil.success("删除成功");
        else
            return ResultUtil.error(-1,"删除失败");
    }

    @ApiOperation("更新标签")
    @PutMapping
    @PermissionMethod(text = "更新标签")
    public Result update(DishTag dishTag){
        int result;
        System.out.println(dishTag.getDishTagId());
        dishTag.setAddTime(new Date());
        result = dishTagMapper.updateByPrimaryKeySelective(dishTag);
        if (result>0)
            return  ResultUtil.success("更新成功");
        else
            return  ResultUtil.error(-1,"更新失败");
    }
    @ApiOperation("分页获取标签信息")
    @GetMapping
    @com.beardream.ioc.Log
    public Result getPage(DishTag dishTag, @RequestParam(value = "pageNum", defaultValue = "1",required = false)  int pageNum, @RequestParam(value = "pageSize", defaultValue = "10",required = false)  int pageSize, BindingResult bindingResult){
        if (!TextUtil.isEmpty(pageNum) || !TextUtil.isEmpty(pageSize)){
            return ResultUtil.error(-1,"pageNum,pageNum不能为空！");
        }
        if (dishTagService.getPage(dishTag, pageNum,pageSize)!=null)
            return ResultUtil.success(dishTagService.getPage(dishTag, pageNum,pageSize));
        else
            return ResultUtil.error(-1,"系统错误");
    }

}

