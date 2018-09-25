package com.sell.Service.IMPL;

import com.sell.DateObject.ProductCategory;
import com.sell.Repository.ProductCategoryRepository;
import com.sell.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/5
 * 15:45
 * #
 */
@Service
public class CategoryServiceIMPL implements CategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    ProductCategory productCategory = new ProductCategory();

    @Override
    public ProductCategory findOne(Integer id) {
        productCategory.setCategoryId(id);
        Example< ProductCategory> example = Example.of(productCategory);
        ProductCategory result = productCategoryRepository.findOne(example).orElse(productCategory);
        return result;
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        List<ProductCategory> result =  productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
        return result;
    }

  /*  @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        List<ProductCategory> result =  productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
        return result;
    }*/

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return  productCategoryRepository.save(productCategory);
    }
}
