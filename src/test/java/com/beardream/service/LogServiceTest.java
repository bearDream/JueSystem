package com.beardream.service;

import com.beardream.dao.LogMapper;
import com.beardream.model.Log;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by soft01 on 2017/5/17.
 */
public class LogServiceTest {

    @Autowired
    public LogMapper mLogMapper;

    @Test
    public void getPage() throws Exception {

    }

}