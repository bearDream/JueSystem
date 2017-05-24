package com.beardream.service;

import com.beardream.Utils.ResultUtil;
import com.beardream.dao.BusinessMapper;
import com.beardream.dao.NumberMapper;
import com.beardream.model.Business;
import com.beardream.model.Number;
import com.beardream.model.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by bearDream on 2017/5/22.
 *
 * 取号：
 * 1、先判断商家是否开放取号,判断is_take是否为1
 * 2、查找num表，根据business,is_expire取出数据
 * 3、将数据装到list中，得到前面还有几桌以及当前排到号
 */
@Component
@Service
public class TakeNumService {

    @Autowired
    private NumberMapper mNumberMapper;

    @Autowired
    private BusinessMapper mBusinessMapper;

    // 取号 该方法应该为一个同步方法，避免多用户同时操作该方法,造成取号数据不准确
    public Result takeNum(Integer businessId){

        Business business = mBusinessMapper.selectByPrimaryKey(businessId);
        Number number = new Number();
        number.setBusinessId(businessId);
        number.setIsExpired((byte) 1);

        if (business == null || business.getIsTake() == 1){
            return ResultUtil.error(-1,"商家已关闭取号，请重新刷新数据");
        }

        List<Number> queue = mNumberMapper.findBySelective(number);

        Collections.sort(queue, new Comparator<Number>() {
            @Override
            public int compare(Number o1, Number o2) {
                if(o1 instanceof Number && o2 instanceof Number){
                    Number e1 = (Number) o1;
                    Number e2 = (Number) o2;
                    return e1.getNumber().compareTo(e2.getNumber());
                }
                throw new ClassCastException("不能转换为Number类型");
            }
        });

        for (Number number1 : queue){
            System.out.println(number1.getNumber());
        }

        // 将取的号返回给控制器
        return ResultUtil.success(queue);
    }



    public Map<String, Object> getEnableTakeBusiness(Business business, int pageNum, int pageSize){

        PageHelper.startPage(pageNum , pageSize).setOrderBy("add_time asc");
        List<Business> businesses =mBusinessMapper.findBySelective(business);
        PageInfo page = new PageInfo(businesses);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page",page);
        return map;

    }
}