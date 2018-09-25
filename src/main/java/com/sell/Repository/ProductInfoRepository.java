package com.sell.Repository;

import com.sell.DateObject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/5
 * 17:29
 * #
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
