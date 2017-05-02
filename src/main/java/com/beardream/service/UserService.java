package com.beardream.service;

import com.beardream.Utils.MD5;
import com.beardream.dao.UserDetailMapper;
import com.beardream.dao.UserMapper;
import com.beardream.enums.ResultEnum;
import com.beardream.exception.UserException;
import com.beardream.model.User;
import com.beardream.model.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by soft01 on 2017/4/18.
 */
@Component
@Service
public class UserService{

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public UserDetailMapper userDetailMapper;

    public List<User> userList()throws Exception{
        return userMapper.findAll();
    }

    public int save(User user)throws Exception {
        UserDetail userDetail = new UserDetail();
        user.setPassword(MD5.GetMD5Code(user.getPassword()));
        userMapper.insertSelective(user);
        System.out.println("主键id -->"+user.getUserId());
        userDetail.setUserId(user.getUserId());
        return userDetailMapper.insertSelective(userDetail);//注册成功返回1，否则会抛出异常没有返回值
    }

    //检测手机号或用户名是否有重复
    public boolean check(User user){
        System.out.println(user.getMobile());
        List<User> userList = userMapper.findSelective(user);
        System.out.println(userList.size());
        if (userList.size() != 0)
            return false;
        else
            return true;
    }

    public Object get(int userId)throws Exception {
        return userMapper.selectByPrimaryKey(userId);
    }
}
