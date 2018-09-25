package com.sell.ViewObject;

import lombok.Data;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/6
 * 10:38
 * #
 */

@Data
public class ResultViewO<T> {

    //错误码
    private Integer code;

    //提示信息
    private String msg;

    //具体类容
    private T data;


}
