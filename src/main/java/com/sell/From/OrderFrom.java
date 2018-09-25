package com.sell.From;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/11
 * 15:02
 * #
 */
@Data
public class OrderFrom {

    //买家姓名 name必填
    @NotEmpty(message = "姓名必填")
    private String name;

    //买家电话 phone必填
    @NotEmpty(message = "电话必填")
    private String phone;

    //买家地址 address必填
    @NotEmpty(message = "地址必填")
    private String address;

    //买家微信openid 用户openid必填
    @NotEmpty(message = "openid必填")
    private String openid;

    //购物车items 购物车不能为空
    @NotEmpty(message = "购物车蹦不能为空")
    private String items;
}
