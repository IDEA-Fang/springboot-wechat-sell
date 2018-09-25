package com.sell.ViewObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/6
 * 14:18
 * #商品详情
 */
@Data
public class ProductInfoViewO {

    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;
}
