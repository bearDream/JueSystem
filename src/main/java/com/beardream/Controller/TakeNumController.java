package com.beardream.Controller;

import com.beardream.Utils.ResultUtil;
import com.beardream.Utils.TextUtil;
import com.beardream.dao.BusinessMapper;
import com.beardream.model.Business;
import com.beardream.model.Number;
import com.beardream.model.Result;
import com.beardream.service.TakeNumService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by laxzh on 2017/5/6.
 * 取号控制器
 */
@RestController
@RequestMapping("/api/take")
@Api(value = "取号服务",description = "提供RESTful风格API的取号的操作")
public class TakeNumController {

    @Autowired
    private TakeNumService mTakeNumService;

    // 获取所有开启排队的商家列表
    @GetMapping
    public Result takeNum(@RequestParam(value = "pageNum", defaultValue = "1",required = false)  int pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "10",required = false)  int pageSize){

        Business business = new Business();
        // 查找的是开启排号的商家
        business.setIsTake((byte) 0);
        Map<String, Object> enableTakeList = mTakeNumService.getEnableTakeBusiness(business, pageNum, pageSize);

        return ResultUtil.success(enableTakeList);
    }

    // 获取商家的所有排号(注意这里返回的不是分页数据，只是一个list数据)
    @GetMapping(value = "/getBusinessNum", params = {"businessId"})
    public Result getNum(@RequestParam("businessId") Integer businessId){

        if (!TextUtil.isEmpty(businessId)) {
            return ResultUtil.error(-1,"商家id不能为空");
        }

        Result numList = mTakeNumService.takeNum(businessId);
        if (numList.getCode() != -1){
            return ResultUtil.success(numList.getData());
        }
        return ResultUtil.error(-1,numList.getMsg());

    }
}
