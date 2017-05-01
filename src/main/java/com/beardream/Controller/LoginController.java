package com.beardream.Controller;

import com.beardream.Utils.Constants;
import com.beardream.Utils.ResultUtil;
import com.beardream.dao.UserMapper;
import com.beardream.model.User;
import com.beardream.service.LoginService;
import com.beardream.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by soft01 on 2017/4/28.
 */
@EnableAutoConfiguration
@RestController
public class LoginController {

//    @Autowired
//    private HttpServletRequest mRequest;

    @Autowired
    private UserMapper mUserMapper;

    @Autowired
    public LoginService mLoginService;

    //user:用户名和密码   model：设备型号
    @PostMapping("/login")
    public Object login(User user, @RequestParam String model, BindingResult bindingResult, HttpServletRequest request, HttpSession session){
        System.out.println(user.getUsername() + "-----" + user.getPassword() + "-------" + model);
        if (session.getAttribute(Constants.USER) != null){
            System.out.println("已登陆 -->"+session.getAttribute(Constants.USER).toString());
            return ResultUtil.success(session.getAttribute(Constants.USER).toString());
        }
        if (mLoginService.login(user, model, request, session)){
            return ResultUtil.success(session.getAttribute(Constants.USER).toString());
        }
        return ResultUtil.error(-1,"登录失败");
    }
}
