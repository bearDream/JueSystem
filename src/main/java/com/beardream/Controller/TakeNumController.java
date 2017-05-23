package com.beardream.Controller;

import com.beardream.Utils.ResultUtil;
import com.beardream.Utils.TextUtil;
import com.beardream.model.Number;
import com.beardream.model.Result;
import com.beardream.service.TakeNumService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by laxzh on 2017/5/6.
 * 取号控制器
 */
@RestController
@RequestMapping("/api/tag")
@Api(value = "取号服务",description = "提供RESTful风格API的取号的操作")
public class TakeNumController {

//    @Autowired
//    private TakeNumService mTakeNumService;
//
//    @GetMapping
//    public Result takeNum(Number number){
//        if (!TextUtil.isEmpty(number.getBusinessId()) && !TextUtil.isEmpty(number.getPeopleNum())){
//            return ResultUtil.error(-1,"请选择商家并填写人数后又提交");
//        }
//        number.setIsExpired((byte) 1); // 设置为没有过期
//        Result num = mTakeNumService.takeNum(number);
//        if (num.getCode() != -1)
//            return ResultUtil.success(num.getData());
//        else
//            return ResultUtil.error(-1,num.getMsg());
//    }
}
