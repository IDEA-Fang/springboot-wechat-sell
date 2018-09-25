package com.sell.DateObject;

import com.sell.Enums.OrderStatusEnum;
import com.sell.Enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/7
 * 15:04
 * #
 */
@Table(name = "order_infor")
@Entity
@DynamicUpdate
@Data
public class OrderInfoMaster {

    //订单id
    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    //支付状态，0默认未支付
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private Date createTime;

    private Date updateTime;

}
