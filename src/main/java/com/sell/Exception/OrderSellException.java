package com.sell.Exception;

import com.sell.Enums.ResultEnum;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/7
 * 20:45
 * #
 */
public class OrderSellException extends RuntimeException {


    private Integer code;

    //private String message;
    public OrderSellException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public OrderSellException(Integer code,String message){
        super(message);
        this.code = code;
    }
}
