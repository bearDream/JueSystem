package com.beardream.Controller;

import com.beardream.Utils.ResultUtil;
import com.beardream.Utils.TextUtil;
import com.beardream.dao.BusinessMapper;
import com.beardream.dao.BusinessTagMapper;
import com.beardream.dao.DishTagMapper;
import com.beardream.dao.TagMapper;
import com.beardream.ioc.PermissionMethod;
import com.beardream.ioc.PermissionModule;
import com.beardream.model.*;
import com.beardream.service.BusinessTagService;
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
 * Created by laxzh on 2017/5/19.
 * 标签控制器
 */
@RestController
@RequestMapping("/api/businesstag")
@Api(value = "商家标签服务",description = "提供RESTful风格API的标签的增删改查服务")
@PermissionModule(text = "商家标签管理")
public class BusinessTagController {
    @Autowired
    private BusinessTagMapper businessTagMapper;
    @Autowired
    private BusinessTagService businessTagService;

    @ApiOperation("获取单个菜品标签信息")
    @GetMapping(value = "/get")
    @PermissionMethod(text = "获取菜品标签信息")
    public Result get(BusinessTag businessTag, BindingResult bindingResult){
        System.out.println(businessTag.getBusinessTagId());
        return ResultUtil.success(businessTagService.find(businessTag));
    }


    @ApiOperation("添加标签")
    @PostMapping
    @PermissionMethod(text = "添加标签")
    public Result add(BusinessTag businessTag){
        int result;
        if (businessTag==null)
            return  ResultUtil.error(-1,"没有参数");
        List<BusinessTag> t = businessTagMapper.findBySelective(businessTag);
        if (t.size()>0)
            return ResultUtil.error(-1,"标签已存在");
        businessTag.setAddTime(new Date());
        result = businessTagMapper.insertSelective(businessTag);
        if (result>0)
            return ResultUtil.success("添加成功");
        else
            return  ResultUtil.error(-1,"添加失败");

    }

    @ApiOperation("删除标签")
    @DeleteMapping
    @PermissionMethod(text = "删除标签")
    public Result delete(BusinessTag businessTag){
        int result;
        result = businessTagMapper.deleteByPrimaryKey(businessTag.getBusinessTagId());
        if (result> 0 )
            return ResultUtil.success("删除成功");
        else
            return ResultUtil.error(-1,"删除失败");
    }

    @ApiOperation("更新标签")
    @PutMapping
    @PermissionMethod(text = "更新标签")
    public Result update(BusinessTag businessTag){
        int result;
        System.out.println(businessTag.getBusinessTagId());
        businessTag.setAddTime(new Date());
        result = businessTagMapper.updateByPrimaryKeySelective(businessTag);
        if (result>0)
            return  ResultUtil.success("更新成功");
        else
            return  ResultUtil.error(-1,"更新失败");
    }
    @ApiOperation("分页获取标签信息")
    @GetMapping
    @com.beardream.ioc.Log
    public Result getPage(BusinessTag businessTag, @RequestParam(value = "pageNum", defaultValue = "1",required = false)  int pageNum, @RequestParam(value = "pageSize", defaultValue = "10",required = false)  int pageSize, BindingResult bindingResult){
        if (!TextUtil.isEmpty(pageNum) || !TextUtil.isEmpty(pageSize)){
            return ResultUtil.error(-1,"pageNum,pageNum不能为空！");
        }
        if (businessTagService.getPage(businessTag, pageNum,pageSize)!=null)
            return ResultUtil.success(businessTagService.getPage(businessTag, pageNum,pageSize));
        else
            return ResultUtil.error(-1,"系统错误");
    }

}

