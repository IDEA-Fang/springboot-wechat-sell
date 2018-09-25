package com.sell.Service.IMPL;

import com.sell.DateObject.ProductCategory;
import com.sell.Service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/5
 * 16:07
 * #
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceIMPLTest {

    @Autowired
    CategoryServiceIMPL categoryService;

    @Test
    public void findOne() {
        Integer id = 3;
        ProductCategory printp = categoryService.findOne(id);
        System.out.println(printp);
    }

    @Test
    public void findAll() {
        List<ProductCategory> list = categoryService.findAll();
        for(int i=0; i<=list.size();i++) {
            System.out.println(list);
        }
}

    @Test
    public void findByCategoryTypeIn() {
       List<Integer> categoryTypeList = Arrays.asList(1,3);
        List<ProductCategory> result = categoryService.findByCategoryTypeIn(categoryTypeList);
        for(int i=0; i<=result.size();i++) {
            System.out.println(result.size());
            System.out.println(result);
        }
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory();


        productCategory.setCategoryName("饮料");
        productCategory.setCategoryType(2);
        productCategory.setCreateTime(new Date());

        categoryService.save(productCategory);

    }
}