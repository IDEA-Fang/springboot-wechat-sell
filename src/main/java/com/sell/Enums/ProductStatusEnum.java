package com.sell.Enums;

import lombok.Getter;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/5
 * 23:16
 * #
 */
@Getter
public enum  ProductStatusEnum implements CodeEnum {
    UP(0,"在架"),
    DOWN(1,"下架")
    ;

    private Integer code;

    private String message;

    ProductStatusEnum (Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
