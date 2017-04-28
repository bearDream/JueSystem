package com.beardream.Controller;

import com.beardream.Utils.ResultUtil;
import com.beardream.dao.UserMapper;
import com.beardream.exception.UserException;
import com.beardream.model.User;
import com.beardream.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by soft01 on 2017/4/17.
 */
@EnableAutoConfiguration
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private Environment environment;

    @RequestMapping("/get")
    public Object get(@RequestParam(value = "user_id", required = true) int user_id) throws Exception {
        System.out.println(user_id);
        User user = new User();
        user.setUserId(user_id);
        user.setUsername("小米");
        return userService.get(user_id);
//        return  userService.get(user_id);
    }

    @PostMapping(value = "/userList")
    public Object userList(@Valid User user, BindingResult bindingResult) throws Exception {
        return userService.userList();
    }

    @PostMapping(value = "/userAdd")
    public Object userAdd(@Valid User user, BindingResult bindingResult) throws Exception {
        System.out.println(user.getUserId());
        return userService.save(user);
    }


}

