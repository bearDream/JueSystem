package com.beardream.service;

import com.beardream.Utils.ResultUtil;
import com.beardream.dao.NutritionMapper;
import com.beardream.model.Nutrition;
import com.beardream.model.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by soft01 on 2017/5/8.
 */
@Component
@Service
public class NutritionService {

    @Autowired
    public NutritionMapper nutritionMapper;

    public String add(Nutrition nutrition){
        int result;
        nutrition.setAddTime(new Date());
        result = nutritionMapper.insertSelective(nutrition);
        if (result>0){
            return "添加成功";
        }else{
            return "添加失败";
        }
    }

    public String delete(Nutrition nutrition){
        int result;
        result = nutritionMapper.deleteByPrimaryKey(nutrition.getNurtritionId());
        if (result > 0) {
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    public String put(Nutrition nutrition){
        int result;
        System.out.println(nutrition.getNurtritionId());
        result = nutritionMapper.updateByPrimaryKeySelective(nutrition);
        if (result > 0) {
            return "更新成功";
        }else {
            return "更新失败";
        }
    }

    public Result getPage(int pageNum,int pageSize){
        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(pageNum , pageSize).setOrderBy("add_time asc");
        List<Nutrition> nutritions =nutritionMapper.findBySelective(new Nutrition());
        PageInfo page = new PageInfo(nutritions);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page",page);
        map.put("list",nutritions);
        return ResultUtil.success(map);
    }

}


