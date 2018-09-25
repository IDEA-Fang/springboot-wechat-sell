package com.sell.DateObject;

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
 * 16:02
 * #
 */
@Table(name = "order_product_detail")
@Entity
@DynamicUpdate
@Data
public class OrderDetail {

    //表的详情id
    @Id
    private String detailId;

    //订单编号
    private String orderId;

    //商品id
    private String productId;

    //商品名字
    private String productName;

    //商品单价
    private BigDecimal productPrice;

    //商品数量
    private Integer productQuantity;

    //商品小图
    private String productIcon;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

}
