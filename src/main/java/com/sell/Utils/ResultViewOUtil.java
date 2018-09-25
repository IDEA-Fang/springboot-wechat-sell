package com.sell.Utils;

import com.sell.ViewObject.ResultViewO;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/6
 * 19:22
 * #
 */
public class ResultViewOUtil {

    public static ResultViewO success(Object object){
        ResultViewO resultViewO =new ResultViewO();
        resultViewO.setCode(0);
        resultViewO.setMsg("成功");
        resultViewO.setData(object);
        return resultViewO;
    }

    public static ResultViewO success() { return null; }

    public static ResultViewO error(Integer code,String msg){
        ResultViewO resultViewO =new ResultViewO();
        resultViewO.setCode(code);
        resultViewO.setMsg(msg);
        return resultViewO;
    }
}
