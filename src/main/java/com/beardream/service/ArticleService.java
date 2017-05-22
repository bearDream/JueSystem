package com.beardream.service;

import com.beardream.Utils.ResultUtil;
import com.beardream.Utils.TextUtil;
import com.beardream.dao.ArticleMapper;
import com.beardream.dao.DishMapper;
import com.beardream.model.Article;
import com.beardream.model.Dish;
import com.beardream.model.Result;
import com.beardream.model.Role;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by soft01 on 2017/5/8.
 */
@Component
@Service
public class ArticleService {

    @Autowired
    public ArticleMapper articleMapper;

    public List find(Article article){
        System.out.println(articleMapper.selectByPrimaryKey(1));
        List<Article> articles = articleMapper.findBySelective(article);
        return articles;
    }

    public String post(Article article){
        int result;
        if (article==null)
            return "没有参数";
        List<Article> articles = articleMapper.findBySelective(article);
        if (articles.size()>0)
            return "该文章已存在";
        article.setAddTime(new Date());
        result = articleMapper.insertSelective(article);
        if (result>0){
            return "添加成功";
        }else{
            return "添加失败";
        }
    }

    public String delete(Article article){
        int result;
        result = articleMapper.deleteByPrimaryKey(article.getArticleId());
        if (result > 0) {
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    public String put(Article article){
        int result;
        System.out.println(article.getArticleId());
        result = articleMapper.updateByPrimaryKeySelective(article);
        if (result > 0) {
            return "更新成功";
        }else {
            return "更新失败";
        }
    }

    public Map getPage(Article article, int pageNum, int pageSize){
        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(pageNum , pageSize).setOrderBy("add_time asc");
        List<Article> articles =articleMapper.findBySelective(new Article());
        PageInfo page = new PageInfo(articles);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page",page);
        return map;
    }

}
