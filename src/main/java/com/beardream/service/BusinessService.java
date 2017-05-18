package com.beardream.service;

import com.beardream.Utils.ResultUtil;
import com.beardream.Utils.TextUtil;
import com.beardream.dao.BusinessMapper;
import com.beardream.model.Business;
import com.beardream.model.Log;
import com.beardream.model.Result;
import com.beardream.model.Role;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by soft01 on 2017/5/17.
 */
@Component
@Service
public class BusinessService {

    @Autowired
    public BusinessMapper mBussinessMapper;


    //删除
    public int deleteBusiness(Business business) {
        return mBussinessMapper.deleteByPrimaryKey(business.getBusinessId());
    }

    @ApiOperation("分页获取商家信息")
    @GetMapping("/getpage")
    @com.beardream.ioc.Log
    public Map getPage(int pageNum,int pageSize) {
        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(pageNum , pageSize).setOrderBy("add_time asc");
        List<Business> businesses =mBussinessMapper.findBySelective(new Business());
        PageInfo page = new PageInfo(businesses);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page",page);
        return map;
    }
}