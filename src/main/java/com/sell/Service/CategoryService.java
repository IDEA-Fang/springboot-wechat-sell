package com.sell.Service;

import com.sell.DateObject.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/5
 * 15:34
 * #类目service
 */

public interface CategoryService {

    ProductCategory findOne(Integer id);

    List<ProductCategory>  findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);



}
