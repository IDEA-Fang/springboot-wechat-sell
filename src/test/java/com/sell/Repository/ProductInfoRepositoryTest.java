package com.sell.Repository;

import com.sell.DateObject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/5
 * 17:34
 * #
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    private ProductInfo result;
    private ProductInfo productInfo = new ProductInfo();

    @Test
    public void testFindOne(){
        productInfo.setProductId("123321");
        Example<ProductInfo> example = Example.of(productInfo);
        result = productInfoRepository.findOne(example).orElse(productInfo);
        System.out.println(result);
    }


    @Test
    public void testSave(){
        productInfo.setProductId("7799443");
        productInfo.setProductName("麻婆豆腐");
        productInfo.setProductPrice(new BigDecimal(21));
        productInfo.setProductStock(31);
        productInfo.setProductDescription("川菜");
        productInfo.setProductIcon("http://xxxxxxx.com/sas");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);

        result = productInfoRepository.save(productInfo);
        Assert.assertNotNull(result);
    }

    public void test(){}


    @Test
    public void findByProductStatus() {

        List<ProductInfo> infoList = productInfoRepository.findByProductStatus(0);
        System.out.println(infoList);
    }

    @Test
    public void testFindAll(){
        List<ProductInfo> infoList  = productInfoRepository.findAll();
        System.out.println(infoList);
    }
}