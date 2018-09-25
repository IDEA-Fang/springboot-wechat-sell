package com.sell.DataTransferObject;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/7
 * 21:57
 * #
 */
@Data
public class CartDTO {

    //商品id
    private String productId;

    //数量
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
