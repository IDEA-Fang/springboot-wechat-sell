package com.sell.Service;

import com.sell.DataTransferObject.CartDTO;
import com.sell.DateObject.ProductInfo;
import com.sell.Repository.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/5
 * 17:33
 * #商品
 */

public interface ProductInfoService {

    ProductInfo findOne(String productId);

    Page<ProductInfo> findAll(Pageable pageable);

    //查询在架商品
    List<ProductInfo> findUpAll();

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(CartDTO cartDTO);



}
