package com.sell.ViewObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/6
 * 14:08
 * #返回的商品，包含类目
 */
@Data
public class ResultProductViewO {
    //类目名字
    @JsonProperty("name")
    private String categoryName;

    //类目编号 int
    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoViewO> productInfoViewO;
}
