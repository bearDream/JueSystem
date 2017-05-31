package com.beardream.Controller;

import com.beardream.Utils.ResultUtil;
import com.beardream.Utils.TextUtil;
import com.beardream.dao.BusinessMapper;
import com.beardream.dao.DishTypeMapper;
import com.beardream.ioc.PermissionMethod;
import com.beardream.ioc.PermissionModule;
import com.beardream.model.*;
import com.beardream.service.DishTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by laxzh on 2017/5/6.
 * 菜品类别控制器
 */
@RestController
@RequestMapping("/api/dishtype")
@Api(value = "菜品分类服务",description = "提供RESTful风格API的商家的增删改查服务")
@PermissionModule(text = "菜品分类管理")
public class DishTypeController {

    @Autowired
    private DishTypeMapper dishTypeMapper;

    @Autowired
    private DishTypeService dishTypeService;

    @ApiOperation("获取单个菜品分类信息")
    @GetMapping(value = "/get")
    @PermissionMethod(text = "获取菜品分类信息")
    public Result get(DishType dishType, BindingResult bindingResult){
        System.out.println(dishType.getDishtypeId());
        DishType dishType1 = dishTypeService.find(dishType);
        if (dishType1!=null)
            return ResultUtil.success(dishType1);
        else
            return ResultUtil.error(-1,"分类不存在");
    }


    @ApiOperation("添加菜品分类")
    @PostMapping
    @PermissionMethod(text = "添加菜品分类")
    public @ResponseBody Result post(@RequestBody DishType dishType){
        return dishTypeService.add(dishType);
    }

    @ApiOperation("删除菜品分类")
    @DeleteMapping
    @PermissionMethod(text = "删除菜品分类")
    public Result delete(DishType dishType){
        int result;
        result = dishTypeMapper.deleteByPrimaryKey(dishType.getDishtypeId());
        if (result>0)
            return  ResultUtil.success("删除成功");
        else
            return ResultUtil.error(-1,"删除失败");
    }

    @ApiOperation("更新菜品分类")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionMethod(text = "更新菜品分类")
    public @ResponseBody Result update(@RequestBody DishType dishType){
        int result;
        System.out.println(dishType.getDishtypeId());
         dishType.setAddTime(new Date());
         result = dishTypeMapper.updateByPrimaryKeySelective(dishType);
         if (result>0)
             return ResultUtil.success("修改成功");
         else
             return ResultUtil.error(-1,"修改失败");
    }

    @ApiOperation("分页获取菜品种类")
    @GetMapping
    @com.beardream.ioc.Log
    public Result getPage(DishType dishType,
                          @RequestParam(value = "pageNum",defaultValue = "1",required = false)  int pageNum,
                          @RequestParam(value = "pageSize",defaultValue = "10",required = false)  int pageSize,
                          BindingResult bindingResult){
        if (!TextUtil.isEmpty(pageNum) || !TextUtil.isEmpty(pageSize)){
            return ResultUtil.error(-1,"pageNum,pageNum不能为空！");
        }
        Map resultMap = dishTypeService.getPage(dishType,pageNum,pageSize);
        if (resultMap.get("page") !=null )
            return ResultUtil.success(resultMap);
        else
            return ResultUtil.error(-1,"系统错误");
    }

    @ApiOperation("获取所有菜品种类")
    @GetMapping("/getAll")
    @com.beardream.ioc.Log
    public Result getAllType(){
        List<DishType> allType = dishTypeService.getAllType();
        if (allType != null)
            return ResultUtil.success(allType);
        else
            return ResultUtil.error(-1,"系统错误");
    }
}

