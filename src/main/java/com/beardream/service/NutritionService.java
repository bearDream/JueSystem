package com.beardream.service;

import com.beardream.Utils.ResultUtil;
import com.beardream.dao.NutritionMapper;
import com.beardream.model.Nutrition;
import com.beardream.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by soft01 on 2017/5/8.
 */
@Component
@Service
public class NutritionService {
    @Autowired
    public NutritionMapper nutritionMapper;

    public List find(Nutrition nutrition){
        List<Nutrition> nutritionList = nutritionMapper.findBySelective(nutrition);
        return nutritionList;
    }

    public String add(Nutrition nutrition){
        int result;
        result = nutritionMapper.insertSelective(nutrition);
        nutrition.setAddTime(new Date());
        if (result>0){
            return String.valueOf(ResultUtil.success("添加成功"));
        }else{
            return String.valueOf(ResultUtil.error(-1,"添加失败"));
        }
    }

    public String delete(Nutrition nutrition){
        int result;
        result = nutritionMapper.deleteByPrimaryKey(nutrition.getNurtritionId());
        if (result > 0)
            return String.valueOf(ResultUtil.success("删除成功"));
        else
            return String.valueOf(ResultUtil.success("删除失败"));
    }

}
