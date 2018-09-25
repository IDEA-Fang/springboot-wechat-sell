package com.sell.Enums;

import lombok.Getter;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/7
 * 15:48
 * #
 */
@Getter
public enum PayStatusEnum implements CodeEnum {

    WAIT(0,"未支付"),
    SUCCESS(1,"成功支付"),
    ;
    private Integer code;

    private String messge;

    PayStatusEnum (Integer code,String messge){
        this.code = code;
        this.messge = messge;
    }

}
