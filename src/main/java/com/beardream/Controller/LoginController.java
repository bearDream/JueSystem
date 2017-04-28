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

    @PostMapping("/login")
    public Object login(User user, BindingResult bindingResult, HttpServletRequest request, HttpSession session){
        if (session.getAttribute(Constants.USER) != null){
            System.out.println(session.getAttribute(Constants.USER).toString());
        }
        if (mLoginService.login(user, request, session)){
            return ResultUtil.success("登录成功");
        }
        return ResultUtil.success("登录失败");
    }
}
