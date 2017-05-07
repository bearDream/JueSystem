package com.beardream.Controller;

import com.beardream.Utils.ResultUtil;
import com.beardream.dao.MethodMapper;
import com.beardream.dao.ModuleMapper;
import com.beardream.dao.RoleMapper;
import com.beardream.ioc.Log;
import com.beardream.ioc.PermissionMethod;
import com.beardream.ioc.PermissionModule;
import com.beardream.model.Result;
import com.beardream.model.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by soft01 on 2017/5/7.
 */
@RestController
@RequestMapping("/authority")
@Api(value = "权限服务",description = "提供RESTful风格API的权限的增删改查服务")
@PermissionModule(text = "权限管理")
public class AuthorityController {

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private MethodMapper methodMapper;

    /*
        Put更新数据的请求只能是参数形式，不能写在body中
     */
    @ApiOperation("获取所有权限")
    @GetMapping
    @Log
    @PermissionMethod(text = "查看权限")
    public Result get(BindingResult bindingResult){
        return ResultUtil.error(-1,"权限获取失败");
    }

    /*
        Put更新数据的请求只能是参数形式，不能写在body中
     */
    @ApiOperation("设置权限")
    @PutMapping
    @Log
    public Result update(Role role){
//        int result;
//        System.out.println(role.getRoleId());
//        role.setAddTime(new Date());
//        result = roleMapper.updateByPrimaryKeySelective(role);
//        if (result > 0)
//            return ResultUtil.success("更新成功");
//        else
        return ResultUtil.error(-1,"更新失败");
    }

}
