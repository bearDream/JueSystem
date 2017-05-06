package com.beardream.Utils;

import com.google.gson.Gson;

/**
 * Created by laxzh on 2017/5/6.
 * Gson将model转换为json格式
 */
public class Json {


    public static <T> String toJson(T model){
        Gson gson = new Gson();
        return gson.toJson(model);
    }
}
