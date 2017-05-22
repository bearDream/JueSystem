package com.beardream.Controller;

import com.beardream.Utils.ResultUtil;
import com.beardream.Utils.TextUtil;
import com.beardream.dao.ArticleMapper;
import com.beardream.dao.DishMapper;
import com.beardream.ioc.PermissionMethod;
import com.beardream.ioc.PermissionModule;
import com.beardream.model.Article;
import com.beardream.model.Dish;
import com.beardream.model.Result;
import com.beardream.service.ArticleService;
import com.beardream.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by soft01 on 2017/5/19.
 */
@RestController
@RequestMapping("/api/article")
@Api(value = "图文服务",description = "提供RESTful风格API的图文的增删改查服务")
@PermissionModule(text = "图文管理")
public class ArticleController {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleService articleService;

    @ApiOperation("获取单个文章信息")
    @GetMapping(value = "/{articleId}")
    @PermissionMethod(text = "获取图文信息")
    public Result get(Article article, BindingResult bindingResult){
        System.out.println(article.getArticleId());
        return ResultUtil.success(articleService.find(article));
    }

    @ApiOperation("添加图文")
    @PostMapping
    @PermissionMethod(text = "添加图文信息")
    public Result post(Article article){
        System.out.println(article.getArticleId());
        return ResultUtil.success(articleService.post(article));
    }

    @ApiOperation("删除图文")
    @DeleteMapping
    @PermissionMethod(text = "删除图文")
    public Result delete(Article article){
        System.out.println(article.getArticleId());
        return ResultUtil.success(articleService.delete(article));
    }

    @ApiOperation("更新图文")
    @PutMapping
    @PermissionMethod(text = "更新图文")
    public @ResponseBody  Result put(@RequestBody  Article article) {
        int result;
        if (article.getArticleId()==null)
            return  ResultUtil.error(-1,"商家id不能为空");
        article.setAddTime(new Date());
        result = articleMapper.updateByPrimaryKeySelective(article);
        if (result>0)
            return  ResultUtil.success("修改成功");
        else
            return ResultUtil.error(-1,"修改失败");
    }

    @ApiOperation("分页获取图文")
    @GetMapping
    @com.beardream.ioc.Log
    public Result getPage(Article article, @RequestParam(value = "pageNum", defaultValue = "1",required = false)  int pageNum, @RequestParam(value = "pageSize", defaultValue = "10",required = false)  int pageSize, BindingResult bindingResult){
        if (!TextUtil.isEmpty(pageNum) || !TextUtil.isEmpty(pageSize)){
            return ResultUtil.error(-1,"pageNum,pageNum不能为空！");
        }
        if (articleService.getPage(article, pageNum,pageSize)!=null)
            return ResultUtil.success(articleService.getPage(article, pageNum,pageSize));
        else
            return ResultUtil.error(-1,"系统错误");
    }
}

