package com.beardream.Controller;

import com.beardream.Utils.Json;
import com.beardream.Utils.ResultUtil;
import com.beardream.Utils.TextUtil;
import com.beardream.dao.RoleMapper;
import com.beardream.model.Result;
import com.beardream.model.Role;
import com.beardream.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by laxzh on 2017/5/6.
 * 权限控制器
 */
@RestController
@RequestMapping("/authority")
@Api(value = "权限服务",description = "提供RESTful风格API的权限的增删改查服务")
public class AuthorityController {

    @Autowired
    private RoleMapper roleMapper;

    @ApiOperation("获取单个权限")
    @GetMapping
    public Result get(Role role, BindingResult bindingResult){
        System.out.println(role.getRoleId());
        List<Role> roles = roleMapper.findBySelective(role);
        return ResultUtil.success(roles);
    }

    @ApiOperation("添加权限")
    @PostMapping
    public Result add(){
        return ResultUtil.success("请求postMAPPING成功");
    }

    @ApiOperation("删除权限")
    @DeleteMapping
    public Result delete(){
        return ResultUtil.success("请求DeleteMapping成功");
    }

    @ApiOperation("更新权限")
    @PutMapping
    public Result update(){
        return ResultUtil.success("请求PutMapping成功");
    }

    //需要分页
    // 需要两个参数： 当前所在页pageSize 需要几条数据limit
    @ApiOperation("获取单个权限")
    @GetMapping("/getpage")
    public Result getPage(Role role,@RequestParam int pageNum, @RequestParam int pageSize, BindingResult bindingResult){
        System.out.println(role.getRoleId());

        if (TextUtil.isEmpty(pageNum) || TextUtil.isEmpty(pageSize)){
            return ResultUtil.error(-1,"pageNum,pageNum不能为空！");
        }

        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(pageNum , pageSize);
        List<Role> roles =roleMapper.findBySelective(new Role());
        PageInfo page = new PageInfo(roles);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page",page);
        map.put("list",roles);

        PageHelper.startPage(pageNum , pageSize);
        PageHelper.orderBy("blog_ID desc");

//        List<Role> roles = roleMapper.findBySelective(role);
        return ResultUtil.success(roles);
    }
}
