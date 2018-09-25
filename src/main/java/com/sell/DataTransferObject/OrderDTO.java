package com.sell.DataTransferObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sell.DateObject.OrderDetail;
import com.sell.Enums.OrderStatusEnum;
import com.sell.Enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.sell.Utils.Serialize.Date2LongSerializer;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/7
 * 20:07
 * #
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    //订单id
    private String orderId;

    //买家姓名
    private String buyerName;

    //买家电话
    private String buyerPhone;

    //买家openid
    private String buyerOpenid;

    //买家地址
    private String buyerAdress;

    //订单总金额
    private BigDecimal orderAmount;

    //订单状态，0默认新下单
    private Integer orderStatus;

    //支付状态，0默认未支付
    private Integer payStatus;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    //所有的订单
    List<OrderDetail> orderDetailList;

}
