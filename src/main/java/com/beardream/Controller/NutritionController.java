package com.beardream.Controller;

import antlr.collections.List;
import com.beardream.Utils.ResultUtil;
import com.beardream.dao.NumberMapper;
import com.beardream.dao.NutritionMapper;
import com.beardream.dao.UserMapper;
import com.beardream.model.Nutrition;
import com.beardream.model.Result;
import com.beardream.model.User;
import com.beardream.service.NutritionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by laxzh on 2017/5/6.
 * 营养价值控制器
 */
@RestController
@RequestMapping("/nutrition")
@Api(value = "营养价值服务",description = "提供RESTful风格API的营养价值的增删改查服务")
public class NutritionController {

    @Autowired
    private NutritionMapper nutritionMapper;

    @Autowired
    private NutritionService mNutritionService;


    @ApiOperation("获取单个营养价值")
    @GetMapping
    public Result get(Nutrition nutrition, BindingResult bindingResult){
        System.out.println(nutrition.getNurtritionId());
        return ResultUtil.success(mNutritionService.find(nutrition));
}

    @ApiOperation("添加营养价值")
    @PostMapping
    public Result add(Nutrition nutrition){
        System.out.println(nutrition.getNurtritionId());
        return ResultUtil.success(mNutritionService.add(nutrition));
    }

    @ApiOperation("删除营养价值")
    @DeleteMapping
    public Result delete(Nutrition nutrition) {
        System.out.println(nutrition.getNurtritionId());
        return  ResultUtil.success(mNutritionService.delete(nutrition));
    }

    @ApiOperation("更新营养价值")
    @PutMapping
    public Result update(Nutrition nutrition){
        int result;
        System.out.println(nutrition.getNurtritionId());
        nutrition.setAddTime(new Date());
        result = nutritionMapper.updateByPrimaryKeySelective(nutrition);
        if (result > 0)
            return ResultUtil.success("更新成功");
        else
            return ResultUtil.error(-1,"更新失败");
    }
}
