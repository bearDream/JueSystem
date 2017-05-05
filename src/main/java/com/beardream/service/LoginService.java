package com.beardream.service;

import com.beardream.Utils.Constants;
import com.beardream.Utils.Ip;
import com.beardream.Utils.MD5;
import com.beardream.dao.UserMapper;
import com.beardream.model.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by soft01 on 2017/4/28.
 */
@Component
@Service
public class LoginService {

    @Autowired
    public UserMapper mUserMapper;

    public boolean login(User user, HttpServletRequest request, HttpSession session){

        if (user == null){
            throw new NullPointerException("user不能为空");
        }
        User user1 = mUserMapper.findByMobile(user.getTel());
        if(user1 == null){
            //用户名不存在
            return false;
        }
        System.out.println(user1.getUserId());
        System.out.println(user1.getTel());
        if(user1.getPassword().equals(MD5.GetMD5Code(user.getPassword()))){
            //生成token
//            String token = user.getTel() + user.getPassword() + System.currentTimeMillis();
//            token = MD5.GetMD5Code(token);
            user1.setLogins(user1.getLogins()+1);
            Gson gson = new Gson();

            //登录成功
            session.setAttribute(Constants.USER, gson.toJson(user1));
            session.setMaxInactiveInterval(15*24*60*60);//单位是秒   15*24*60*60十五天的过期时间
            System.out.println("登录成功");
            return true;
        }
        return false;
    }
}
