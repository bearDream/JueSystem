package com.beardream.service;

import com.beardream.Utils.ResultUtil;
import com.beardream.Utils.TextUtil;
import com.beardream.dao.TagMapper;
import com.beardream.model.Result;
import com.beardream.model.Role;
import com.beardream.model.Tag;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by soft01 on 2017/5/17.
 */

@Component
@Service
public class TagService {
    @Autowired
    public TagMapper mTagMapper;


    public int deleteTag(Tag tag) {
        return mTagMapper.deleteByPrimaryKey(tag.getTagId());
    }

    public  Result getPage(int pageNum,int pageSize){
//        System.out.println(role.getRoleId());
        System.out.println(pageNum);
        System.out.println(pageSize);
        if (!TextUtil.isEmpty(pageNum) || !TextUtil.isEmpty(pageSize)){
            return ResultUtil.error(-1,"pageNum,pageNum不能为空！");
        }

        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(pageNum , pageSize).setOrderBy("add_time asc");
        List<Tag> tags =mTagMapper.findBySelective(new Tag());
        PageInfo page = new PageInfo(tags);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page",page);
        map.put("list",tags);
        return ResultUtil.success(map);
    }
}