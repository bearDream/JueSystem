package com.beardream.Controller;

import com.beardream.Utils.ResultUtil;
import com.beardream.dao.MethodMapper;
import com.beardream.dao.ModuleMapper;
import com.beardream.dao.RoleMapper;
import com.beardream.ioc.Log;
import com.beardream.ioc.PermissionMethod;
import com.beardream.ioc.PermissionModule;
import com.beardream.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private ModuleMapper mModuleMapper;

    @Autowired
    private MethodMapper mMethodMapper;

    /*
        查看所有权限，一个module对应多个method
        先查询所有的module  再从method中遍历所有module_id响应的method
     */
    @ApiOperation("获取所有权限")
    @GetMapping
    @Log
    @PermissionMethod(text = "查看所有权限")
    public Result get(){
        //权限树的list
        List<AuthorityTree> authorityTrees = new ArrayList<>();
        List<Module> modules = mModuleMapper.findBySelective();
        for (Module modules1 : modules){
            //根据modules1的module_id查找相应的method
            AuthorityTree authorityTree = new AuthorityTree();
            authorityTree.setModuleName(modules1.getModuleName());
            authorityTree.setControllerKey(modules1.getControllerkey());
            System.out.println("模块名："+modules1.getModuleName());
            Method method = new Method();
            method.setModuleId(modules1.getModuleId());
            List<Method> methodsList = new ArrayList<>();
            List<Method> methods = mMethodMapper.selectBySelective(method);
            for (Method method1 : methods){
                System.out.println("方法名："+method1.getMethodName());
                methodsList.add(method1);
            }
            authorityTree.setMethod(methodsList);
            authorityTrees.add(authorityTree);
        }
//        for ()
        return ResultUtil.success(authorityTrees);
    }

    /*
        Put更新数据的请求只能是参数形式，不能写在body中
     */
    @ApiOperation("设置权限")
    @PutMapping
    @Log
    @PermissionMethod(text = "设置权限")
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
