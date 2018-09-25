package com.sell.Repository;

import com.sell.DateObject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/5
 * 11:55
 * #
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    private ProductCategory productCategory = new ProductCategory();

    @Test
    public void testFindOne(){

        productCategory.setCategoryId(1);
      Example<ProductCategory> example = Example.of(productCategory);
        ProductCategory result = productCategoryRepository.findOne(example).orElse(productCategory);
      System.out.println(">>>>>>>>>"+result);
    }

    @Test
    public void testSave(){
        productCategory.setCategoryId(3);
        productCategory.setCategoryName("女生最爱");
        productCategory.setCategoryType(3);
        ProductCategory result = productCategoryRepository.save(productCategory);
        Assert.assertNotEquals(null, result);
    }


    @Test
    public void testUpdate(){

        productCategory.setCategoryId(1);
        Example<ProductCategory> example = Example.of(productCategory);
        productCategory = productCategoryRepository.findOne(example).orElse(productCategory);
        System.out.println("+++++++++++"+productCategory);

        productCategory.setCategoryId(2);
        productCategory.setCategoryName("最新折扣");
        productCategory.setCategoryType(2);
        productCategory.setCreateTime(new Date());
        ProductCategory result = productCategoryRepository.save(productCategory);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void testFindByCategoryTypeIn(){
        List<Integer> categoryList = Arrays.asList(1,2,3,4,5);
        List<ProductCategory> result = productCategoryRepository.findByCategoryTypeIn(categoryList);
        Assert.assertNotEquals(0,result.size());
        System.out.println(1+"-----------------------");
        System.out.println(result);

    }
}