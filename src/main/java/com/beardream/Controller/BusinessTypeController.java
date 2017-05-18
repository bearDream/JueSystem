package com.beardream.Controller;

import com.beardream.Utils.ResultUtil;
import com.beardream.Utils.TextUtil;
import com.beardream.dao.BusinessTypeMapper;
import com.beardream.dao.DishTypeMapper;
import com.beardream.ioc.PermissionMethod;
import com.beardream.ioc.PermissionModule;
import com.beardream.model.BusinessType;
import com.beardream.model.DishType;
import com.beardream.model.Result;
import com.beardream.model.Role;
import com.beardream.service.BusinessTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by soft01 on 2017/5/17.
 */
/**
 * Created by laxzh on 2017/5/17.
 * 商家类别控制器
 */
@RestController
@RequestMapping("/api/businesstype")
@Api(value = "商家分类服务", description = "提供RESTful风格API的商家的增删改查服务")
@PermissionModule(text = "商家分类管理")
public class BusinessTypeController {
/**
 * Created by laxzh on 2017/5/17.
 * 商家类别控制器
 */
    @Autowired
    private BusinessTypeMapper businessTypeMapper;
    @Autowired
    private BusinessTypeService mBusinessTypeService;

    @ApiOperation("分页获取菜品分类信息")
    @GetMapping
    @PermissionMethod(text = "获取商家分类信息")
    public Result get(BusinessType businessType, @RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum, @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize, BindingResult bindingResult) {
        System.out.println(businessType.getBusinessTypeId());
        System.out.println(pageNum);
        System.out.println(pageSize);
        if (!TextUtil.isEmpty(pageNum) || !TextUtil.isEmpty(pageSize)) {
            return ResultUtil.error(-1, "pageNum,pageNum不能为空！");
        }
        return ResultUtil.success(mBusinessTypeService.getPage(pageNum, pageSize));
    }

    @ApiOperation("添加商家分类")
    @PostMapping
    @PermissionMethod(text = "添加商家分类")
    public Result post(BusinessType businessType) {
        int result;
        if (businessType == null)
            return ResultUtil.error(-1, "没有参数");
        List<BusinessType> b = businessTypeMapper.findBySelective(businessType);
        if (b.size() > 0)
            return ResultUtil.error(-1, "已存在此种类型！");
        businessType.setAddTime(new Date());
        result = businessTypeMapper.insertSelective(businessType);
        if (result > 0)
            return ResultUtil.success("成功添加商家分类");
        else
            return ResultUtil.error(-1, "添加失败");
    }

    @ApiOperation("删除商家分类")
    @DeleteMapping
    @PermissionMethod(text = "删除商家分类")
    public Result delete(BusinessType businessType) {
        int result;
        result = businessTypeMapper.deleteByPrimaryKey(businessType.getBusinessTypeId());
        if (result > 0)
            return ResultUtil.success("删除成功");
        else
            return ResultUtil.error(-1, "删除失败");
    }

    @ApiOperation("更新商家分类")
    @PutMapping
    @PermissionMethod(text = "更新商家分类")
    public Result update(BusinessType businessType) {
        int result;
        System.out.println(businessType.getBusinessTypeId());
        businessType.setAddTime(new Date());
        result = businessTypeMapper.updateByPrimaryKeySelective(businessType);
        if (result > 0)
            return ResultUtil.success("修改成功");
        else
            return ResultUtil.error(-1, "修改失败");
    }
}



