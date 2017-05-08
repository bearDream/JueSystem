package com.beardream.service;

import com.beardream.dao.DishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by soft01 on 2017/5/8.
 */
@Component
@Service
public class DishService {

    @Autowired
    public DishMapper dishMapper;

}
