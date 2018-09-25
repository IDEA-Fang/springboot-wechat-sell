package com.sell.Enums;

import lombok.Getter;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/7
 * 20:52
 * #
 */
@Getter
public enum ResultEnum implements CodeEnum {

    PARAM_ERROR(1,"参数不正确"),

    CART_EMPTY(2,"购物车为空"),

    PRODUCT_NOT_ENOUGH(9,"商品不足"),

    PRODUCT_NOT_EXIST(10,"商品不存在"),

    PRODUCT_STOCK_ERROR(11,"商品库存错误"),

    ORDER_NOT_EXIST(12,"该订单不存在"),

    ORDER_STATUS_ERROR(13,"订单状态错误"),

    ORDER_CANCLE_ERROR(14,"取消订单失败"),

    ORDER_DETAIL_EMPTY(15,"取消订单失败,订单中无商品详情"),

    ORDER_UPDATE_FAILED(16,"更新订单失败"),

    ORDER_PAY_STATUS_ERROR(17,"订单支付状态错误"),

    ORDER_OWNNER_ERROR(18,"该订单不属于当前用户")

    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
