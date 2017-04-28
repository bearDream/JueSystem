package com.beardream.service;

import com.beardream.Utils.MD5;
import com.beardream.dao.UserMapper;
import com.beardream.enums.ResultEnum;
import com.beardream.exception.UserException;
import com.beardream.model.User;
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

    public List<User> userList()throws Exception{
        return userMapper.findAll();
    }

    public int save(User user)throws Exception {
        if(user.getUsername().equals("张弛")){
            throw new UserException(ResultEnum.Black_USERNAME);
        }
        System.out.println(user.toString());
        user.setPassword(MD5.GetMD5Code(user.getPassword()));
        return userMapper.insertSelective(user);
    }

    public Object get(int userId)throws Exception {
        return userMapper.selectByPrimaryKey(userId);
    }
}
