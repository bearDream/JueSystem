package com.beardream.Controller;

import com.beardream.Utils.ResultUtil;
import com.beardream.Utils.TextUtil;
import com.beardream.dao.NutritionMapper;
import com.beardream.model.*;
import com.beardream.service.NutritionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by laxzh on 2017/5/6.
 * 营养价值控制器
 */
@RestController
@RequestMapping("/api/nutrition")
@Api(value = "营养价值服务",description = "提供RESTful风格API的营养价值的增删改查服务")
public class NutritionController {

    @Autowired
    private NutritionMapper mNutritionMapper;

    @Autowired
    private NutritionService mNutritionService;


    @ApiOperation("获取单个营养价值")
    @GetMapping(value = "/get")
    public Result get(Nutrition nutrition, BindingResult bindingResult){
        System.out.println(nutrition.getNurtritionId());
        Nutrition nutrition1 = mNutritionService.find(nutrition);
        if (nutrition1 != null)
            return ResultUtil.success(nutrition1);
        else
            return ResultUtil.error(-1,"数据不存在");
    }

    @ApiOperation("分页获取营养价值")
    @GetMapping
    @com.beardream.ioc.Log
    public Result getPage(Nutrition nutrition, @RequestParam(value = "pageNum",defaultValue = "1", required = false)  int pageNum, @RequestParam(value = "pageSize",defaultValue = "1", required = false)  int pageSize, BindingResult bindingResult){
        if (!TextUtil.isEmpty(pageNum) || !TextUtil.isEmpty(pageSize)){
            return ResultUtil.error(-1,"pageNum,pageNum不能为空！");
        }
        if (mNutritionService.getPage(nutrition, pageNum, pageSize) != null)
            return ResultUtil.success(mNutritionService.getPage(nutrition, pageNum, pageSize));
        else
            return ResultUtil.error(-1,"数据不存在");
    }

    @ApiOperation("添加营养价值")
    @PostMapping
    public @ResponseBody Result post(@RequestBody Nutrition nutrition){
        System.out.println(nutrition.getNurtritionId());
        return mNutritionService.add(nutrition);
    }

    @ApiOperation("删除营养价值")
    @DeleteMapping
    public Result delete(Nutrition nutrition) {
        System.out.println(nutrition.getNurtritionId());
        return  ResultUtil.success(mNutritionService.delete(nutrition));
    }

    @ApiOperation("更新营养价值")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Result put(@RequestBody Nutrition nutrition) {
        System.out.println(nutrition.getNurtritionId());
        return ResultUtil.success(mNutritionService.put(nutrition));
    }

}
