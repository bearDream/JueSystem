package com.beardream.Controller;

import com.beardream.Utils.ResultUtil;
import com.beardream.Utils.TextUtil;
import com.beardream.dao.BusinessMapper;
import com.beardream.dao.RoleMapper;
import com.beardream.ioc.PermissionMethod;
import com.beardream.ioc.PermissionModule;
import com.beardream.model.*;
import com.beardream.service.BusinessService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by laxzh on 2017/5/6.
 * 商家控制器
 */
@RestController
@RequestMapping("/api/business")
@Api(value = "商家服务", description = "提供RESTful风格API的商家的增删改查服务")
@PermissionModule(text = "商家管理")
public class BuisnessController {
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private BusinessService businessService;

    @ApiOperation("分页获取商家信息")
    @GetMapping
    @com.beardream.ioc.Log
    public Result getPage(Business business, @RequestParam(value = "pageNum", defaultValue = "1",required = false)  int pageNum, @RequestParam(value = "pageSize", defaultValue = "10",required = false)  int pageSize, BindingResult bindingResult){
        if (!TextUtil.isEmpty(pageNum) || !TextUtil.isEmpty(pageSize)){
            return ResultUtil.error(-1,"pageNum,pageNum不能为空！");
        }
        if (businessService.getPage(business, pageNum,pageSize)!=null)
            return ResultUtil.success(businessService.getPage(business, pageNum,pageSize));
        else
            return ResultUtil.error(-1,"系统错误");
    }

    @ApiOperation("获取单个商家信息")
    @GetMapping(value = "/get")
    @PermissionMethod(text = "获取商家信息")
    public Result get(Business business, BindingResult bindingResult){
        System.out.println(business.getBusinessId());
        return ResultUtil.success(businessService.find(business));
    }
    @ApiOperation("添加商家")
    @PostMapping
    @PermissionMethod(text = "添加商家")
    public @ResponseBody Object post(@RequestBody Business business) {
        int result;
        if (business == null)
            return ResultUtil.error(-1, "没有参数");
        return ResultUtil.success(businessService.add(business));
    }

    @ApiOperation("删除商家")
    @DeleteMapping
    @PermissionMethod(text = "删除商家")
    public Result delete(Business business) {
        int result;
        result = businessMapper.deleteByPrimaryKey(business.getBusinessId());
        if (result > 0)
            return ResultUtil.success("删除成功");
        else
            return ResultUtil.error(-1, "删除失败");
    }

    @ApiOperation("更新商家")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionMethod(text = "修改商家信息")
    public @ResponseBody Result put(@RequestBody Business business) {
        int result;
        if (business.getBusinessId() == null)
            return ResultUtil.error(-1,"商家id不能为空");
        business.setAddTime(new Date());
        result = businessMapper.updateByPrimaryKeySelective(business);
        if (result > 0)
            return ResultUtil.success("修改成功");
        else
            return ResultUtil.error(-1, "修改失败");
    }

}

