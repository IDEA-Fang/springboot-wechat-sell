package com.sell.Enums;

import lombok.Getter;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/7
 * 15:18
 * #
 */
@Getter
public enum OrderStatusEnum implements CodeEnum {
    NEW(0,"新下单"),
    FINISH(1,"完成订单"),
    CANCLE(2,"取消订单"),
    ;
    private Integer code;

    private String message;

    OrderStatusEnum (Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
