package com.beardream.service;

import com.beardream.Utils.ResultUtil;
import com.beardream.Utils.TextUtil;
import com.beardream.dao.RoleMapper;
import com.beardream.model.Result;
import com.beardream.model.Role;
import com.beardream.model.Tag;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by soft01 on 2017/5/9.
 */
@Component
@Service
public class RoleService {

    @Autowired
    private RoleMapper mRoleMapper;

    public List<Role> findRole(Role role) {
        List<Role> roles = mRoleMapper.findBySelective(role);
        return roles;
    }

    public int addRole(Role role) {
        int result;
        List<Role> exit = mRoleMapper.findBySelective(role);
        if (exit.size() > 0)
            return -2;
        role.setAddTime(new Date());
        result = mRoleMapper.insertSelective(role);
        return result;
    }

    public int deleteRole(Role role) {
        int result;
        result = mRoleMapper.deleteByPrimaryKey(role.getRoleId());
        return result;
    }

    public int updateRole(Role role) {
        role.setAddTime(new Date());
        int result = mRoleMapper.updateByPrimaryKeySelective(role);
        return result;
    }

    //分页获取角色
    public Result getPage(int pageNum, int pageSize){
        System.out.println(pageNum);
        System.out.println(pageSize);
        if (!TextUtil.isEmpty(pageNum) || !TextUtil.isEmpty(pageSize)){
            return ResultUtil.error(-1,"pageNum,pageNum不能为空！");
        }

        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(pageNum , pageSize).setOrderBy("add_time asc");
        List<Role> roles =mRoleMapper.findBySelective(new Role());
        PageInfo page = new PageInfo(roles);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page",page);
        map.put("list",roles);
        return ResultUtil.success(map);
    }
}