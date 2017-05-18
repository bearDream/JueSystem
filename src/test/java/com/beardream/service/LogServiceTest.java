package com.beardream.service;

import com.beardream.SpringbootApplicationTests;
import com.beardream.dao.LogMapper;
import com.beardream.model.Log;
import com.beardream.model.LogUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by soft01 on 2017/5/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogServiceTest {

    @Autowired
    public LogMapper mLogMapper;

    @Test
    public void getPage() throws Exception {
        PageHelper.startPage(1 , 10).setOrderBy("log_addtime asc");
        List<LogUser> logs =mLogMapper.findLogUserBySelective(new LogUser());
        PageInfo page = new PageInfo(logs);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page",page);
        System.out.println(map.get("page").toString());
    }

}