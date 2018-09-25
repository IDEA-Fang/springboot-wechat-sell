package com.sell.Service.IMPL;

import com.sell.DataTransferObject.CartDTO;
import com.sell.DateObject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/5
 * 23:36
 * #
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceIMPLTest {

    @Autowired
    private ProductInfoServiceIMPL productInfoService;

    private ProductInfo result,productInfo = new ProductInfo();

    @Test
    public void findOne() {
        String productId = "123321";
        productInfo = productInfoService.findOne(productId);
        System.out.println(productInfo);
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(1, 2);
        Page<ProductInfo> infoPage = productInfoService.findAll(pageRequest);
        System.out.println(infoPage);
        Assert.assertNotEquals(0,infoPage.getTotalElements());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> infoList = productInfoService.findUpAll();
        System.out.println(infoList);
    }

    @Test
    public void save() {

        productInfo.setProductId("dnsaiewq213");
        productInfo.setProductName("鸡翅包饭");
        productInfo.setProductPrice(new BigDecimal(50.12));
        productInfo.setProductStock(33);
        productInfo.setProductDescription("超级好吃哟");
        productInfo.setProductIcon("http://xxxxxxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);

        result = productInfoService.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void  decreaseStock(){
        CartDTO cartDTO1 = new CartDTO("1333",2 );
       // CartDTO cartDTO2 = new CartDTO("213123131",2 );
        //List<CartDTO> cartDTOList = new ArrayList<>();
        //cartDTOList.add(cartDTO1);
        //cartDTOList.add(cartDTO2);
        System.out.println("111111111111"+cartDTO1);
        productInfoService.decreaseStock(cartDTO1);
    }
}