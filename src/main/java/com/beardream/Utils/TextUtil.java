package com.beardream.Utils;

/**
 * Created by laxzh on 2017/5/6.
 */
public class TextUtil {

    public static boolean isEmpty(String text){
        if (text == null || text == ""){
            return false;
        }
        return true;
    }

    public static boolean isEmpty(int text){
        String t = text + "";
        if (t == null || t == ""){
            return false;
        }
        return true;
    }
}
