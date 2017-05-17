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
@RequestMapping("/business")
@Api(value = "商家服务", description = "提供RESTful风格API的商家的增删改查服务")
@PermissionModule(text = "商家管理")
public class BuisnessController {
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private BusinessService businessService;

    @ApiOperation("获取单个商家信息")
    @GetMapping
    @PermissionMethod(text = "获取商家信息")
    public Result getPage(Business business, @RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum, @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        System.out.println(business.getBusinessId());
        System.out.println(pageNum);
        System.out.println(pageSize);
        if (!TextUtil.isEmpty(pageNum) || !TextUtil.isEmpty(pageSize)) {
            return ResultUtil.error(-1, "pageNum,pageNum不能为空！");
        }
        return ResultUtil.success(businessService.getPage(pageNum, pageSize));
    }


    @ApiOperation("添加商家")
    @PostMapping
    @PermissionMethod(text = "添加商家")
    public Result post(Business business) {
        int result;
        if (business == null)
            return ResultUtil.error(-1, "没有参数");
        List<Business> b = businessMapper.findBySelective(business);
        if (b.size() > 0)
            return ResultUtil.error(-1, "添加失败，商家已存在");
        business.setAddTime(new Date());
        result = businessMapper.insertSelective(business);
        if (result > 0)
            return ResultUtil.success("添加成功");
        else
            return ResultUtil.error(-1, "添加失败");
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
    @PutMapping
    @PermissionMethod(text = "修改商家信息")
    public Result put(Business business) {
        int result;
        System.out.println(business.getBusinessId());
        business.setAddTime(new Date());
        result = businessMapper.updateByPrimaryKeySelective(business);
        if (result > 0)
            return ResultUtil.success("修改成功");
        else
            return ResultUtil.error(-1, "修改失败");
    }

 /*   @ApiOperation("分页获取商家信息")
    @GetMapping("/getpage")
    @com.beardream.ioc.Log
 *//*   public Result getPage(Role role, @RequestParam(value = "pageNum",defaultValue = "1",required = false)  int pageNum, @RequestParam(value = "pageSize",defaultValue = "10",required = false)  int pageSize, BindingResult bindingResult){
        System.out.println(pageNum);
        System.out.println(pageSize);
        if (!TextUtil.isEmpty(pageNum) || !TextUtil.isEmpty(pageSize)){
            return ResultUtil.error(-1,"pageNum,pageNum不能为空！");
        }

        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(pageNum , pageSize).setOrderBy("add_time asc");
        List<Business> businesses =businessMapper.findBySelective(new Business());
        PageInfo page = new PageInfo(businesses);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page",page);
        map.put("list",businesses);
        return ResultUtil.success(map);
    }*/
}
