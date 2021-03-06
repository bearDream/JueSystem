package com.beardream.service;

import com.beardream.Utils.MD5;
import com.beardream.Utils.ResultUtil;
import com.beardream.dao.UserMapper;
import com.beardream.model.Result;
import com.beardream.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by soft01 on 2017/4/18.
 */
@Component
@Service
public class UserService{

    @Autowired
    public UserMapper userMapper;

    public User find(User user)throws Exception{
        User userInfo = userMapper.selectByPrimaryKey(user.getUserId());
        return  userInfo;
    }

    public int save(User user)throws Exception {
        user.setPassword(MD5.GetMD5Code(user.getPassword()));
        user.setUsername(MimeUtility.encodeText(user.getUsername()));
        System.out.println("主键id -->"+user.getUserId());
        return userMapper.insertSelective(user);//注册成功返回1，否则会抛出异常没有返回值
    }

    //检测手机号或用户名是否有重复
    public boolean check(User user){
        System.out.println(user.getTel());
        List<User> userList = userMapper.findSelective(user);
        System.out.println(userList.size());
        if (userList.size() != 0)
            return false;
        else
            return true;
    }

    public Object get(User user)throws Exception {
        return userMapper.findSelective(user);
    }

    public Map getPage(User user,int pageNum, int pageSize){

        PageHelper.startPage(pageNum , pageSize).setOrderBy("user_id asc");
        List<User> users =userMapper.findSelective(user);
        // 将用户名解码
        try {
            for (User user1 : users)
                user1.setUsername(MimeUtility.decodeText(user1.getUsername()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        PageInfo page = new PageInfo(users);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page",page);
        return map;
    }

    public Map getFuzzyPage(User user, int pageNum, int pageSize){
        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(pageNum , pageSize).setOrderBy("user_id asc");
        List<User> users =userMapper.findFuzzySelective(user);
        // 将用户名解码
        try {
            for (User user1 : users)
                user1.setUsername(MimeUtility.decodeText(user1.getUsername()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        PageInfo page = new PageInfo(users);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page",page);
        return map;
    }
}
