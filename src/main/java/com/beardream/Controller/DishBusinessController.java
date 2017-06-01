package com.beardream.Controller;

import com.beardream.Utils.ResultUtil;
import com.beardream.Utils.TextUtil;
import com.beardream.dao.DishMapper;
import com.beardream.ioc.PermissionMethod;
import com.beardream.ioc.PermissionModule;
import com.beardream.model.Dish;
import com.beardream.model.DishBusiness;
import com.beardream.model.Result;
import com.beardream.service.DishBusinessService;
import com.beardream.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by laxzh on 2017/5/6.
 * 菜品控制器
 */
@RestController
@RequestMapping("/api/dishBusiness")
@Api(value = "商家菜品服务",description = "提供RESTful风格API的商家菜品的增删改查服务")
@PermissionModule(text = "商家菜品管理")
public class DishBusinessController {

    @Autowired
    private DishBusinessService mDishBusinessService;

//    @ApiOperation("获取单个菜品信息")
//    @GetMapping(value = "/get")
//    @PermissionMethod(text = "获取菜品信息")
//    public Result get(Dish dish, BindingResult bindingResult){
//        System.out.println(dish.getDishId());
//        if (mDishService.find(dish)!=null)
//            return ResultUtil.success(mDishService.find(dish));
//        else
//            return ResultUtil.error(-1,"菜品不存在");
//    }

    @ApiOperation("添加菜品")
    @PostMapping
    @PermissionMethod(text = "添加菜品信息")
    public @ResponseBody Result post(@RequestBody DishBusiness dishBusiness){
        System.out.println(dishBusiness.getDishId());
        return mDishBusinessService.add(dishBusiness);
    }

    @ApiOperation("删除商家菜品")
    @DeleteMapping
    @PermissionMethod(text = "删除商家菜品")
    public Result delete(DishBusiness dishBusiness){
        return ResultUtil.success(mDishBusinessService.delete(dishBusiness));
    }

//    @ApiOperation("更新菜品")
//    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    @PermissionMethod(text = "更新菜品信息")
//    public @ResponseBody Result put(@RequestBody Dish dish){
//        System.out.println(dish.getDishId());
//        return ResultUtil.success(mDishService.put(dish));
//    }
//
    @ApiOperation("分页获取该商家的所有菜品")
    @GetMapping
    @com.beardream.ioc.Log
    public Result getPage(Dish dish, DishBusiness dishBusiness,
                          @RequestParam(value = "pageNum", defaultValue = "1",required = false)  int pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "10",required = false)  int pageSize,
                          BindingResult bindingResult){
        if (!TextUtil.isEmpty(pageNum) || !TextUtil.isEmpty(pageSize)){
            return ResultUtil.error(-1,"pageNum,pageNum不能为空！");
        }
        Map result = mDishBusinessService.getPage(dishBusiness, pageNum,pageSize);
        if (result.get("page")!=null){
            result.put("topNum", mDishBusinessService.getTopPage(dishBusiness, pageNum,pageSize));
            return ResultUtil.success(result);
        }
        else
            return ResultUtil.error(-1,"系统错误");
    }

    @ApiOperation("分页获取除去该商家的所有菜品")
    @GetMapping("/not")
    @com.beardream.ioc.Log
    public Result getExceptPage(Dish dish, DishBusiness dishBusiness,
                          @RequestParam(value = "pageNum", defaultValue = "1",required = false)  int pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "10",required = false)  int pageSize,
                          BindingResult bindingResult){
        if (!TextUtil.isEmpty(pageNum) || !TextUtil.isEmpty(pageSize)){
            return ResultUtil.error(-1,"pageNum,pageNum不能为空！");
        }
        if (mDishBusinessService.getPage(dishBusiness, pageNum,pageSize)!=null)
            return ResultUtil.success(mDishBusinessService.getNotPage(dishBusiness, pageNum,pageSize));
        else
            return ResultUtil.error(-1,"系统错误");
    }
}
