package com.beardream.Controller;

import com.beardream.Utils.Constants;
import com.beardream.Utils.Json;
import com.beardream.Utils.ResultUtil;
import com.beardream.dao.UserMapper;
import com.beardream.model.User;
import com.beardream.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by soft01 on 2017/4/28.
 * 登陆控制器
 */
@RestController
@RequestMapping("/api/login")
@CrossOrigin
public class LoginController {

//    @Autowired
//    private HttpServletRequest mRequest;

    @Autowired
    private UserMapper mUserMapper;

    @Autowired
    public LoginService mLoginService;

    //user:用户名和密码   model：设备型号
    @PostMapping()
    public Object loginPost(User user, BindingResult bindingResult, HttpServletRequest request, HttpSession session){
        System.out.println(user.getTel() + "-----" + user.getPassword() + "-------" );
        if (session.getAttribute(Constants.USER) != null){
            System.out.println("已登陆 -->"+session.getAttribute(Constants.USER).toString());
            return ResultUtil.success(session.getAttribute(Constants.USER).toString());
        }
        if (mLoginService.login(user, request, session)){
            return ResultUtil.success(session.getAttribute(Constants.USER).toString());
        }
        return ResultUtil.error(-1,"用户名和密码错误！");
    }

    @GetMapping()
    public Object loginGet(User user, BindingResult bindingResult, HttpServletRequest request, HttpSession session){
        System.out.println(user.getTel() + "-----" + user.getPassword() + "-------" );
        if (session.getAttribute(Constants.USER) != null){
            System.out.println("已登陆 -->"+session.getAttribute(Constants.USER).toString());
            return ResultUtil.success(session.getAttribute(Constants.USER).toString());
        }
        if (mLoginService.login(user, request, session)){
            return ResultUtil.success(session.getAttribute(Constants.USER).toString());
        }
        return ResultUtil.error(-1,"用户名和密码错误！");
    }

    @PostMapping("/isLogin")
    public Object isLogin(HttpSession session){
        System.out.println(session.getAttribute(Constants.USER));
        if (session.getAttribute(Constants.USER) == null){
            return ResultUtil.error(-1,"未登录");
        }else {
            User user = Json.fromJson((String) session.getAttribute(Constants.USER), User.class);
            return ResultUtil.success(user);
        }
    }

    @GetMapping("/isLogin")
    public Object isLoginGet(HttpSession session){
        System.out.println(session.getAttribute(Constants.USER));
        if (session.getAttribute(Constants.USER) == null){
            return ResultUtil.error(-1,"未登录");
        }else {
            User user = Json.fromJson((String) session.getAttribute(Constants.USER), User.class);
            return ResultUtil.success(user);
        }
    }

    @GetMapping("/logout")
    public Object logout(User user, BindingResult bindingResult, HttpSession session){
        session.invalidate();
        return ResultUtil.error(0,"注销成功");
    }

}
