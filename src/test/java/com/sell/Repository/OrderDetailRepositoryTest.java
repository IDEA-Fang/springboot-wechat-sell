package com.sell.Repository;

import com.sell.DateObject.OrderDetail;
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
 * 2018/5/7
 * 19:37
 * #
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    private OrderDetail result, orderDetail = new OrderDetail();

    @Test
    public void findByOrderId() {

        List<OrderDetail> orderDetailList  = orderDetailRepository.findByOrderId("71784");
        System.out.println("-----"+orderDetailList);
    }

    @Test
    public void testSave(){
        orderDetail.setDetailId("2131dsads1");
        orderDetail.setOrderId("71784");
        orderDetail.setProductId("123213");
        orderDetail.setProductName("二珂");
        orderDetail.setProductPrice(new BigDecimal(123));
        orderDetail.setProductIcon("http://xxxxxx.png");
        orderDetail.setProductQuantity(99);
        orderDetailRepository.save(orderDetail);
    }


    @Test
    public void testFindOne(){
        orderDetail.setDetailId("2131dsads1");
        Example<OrderDetail> example = Example.of(orderDetail);
        result = orderDetailRepository.findOne(example).orElse(orderDetail);
        System.out.println(result);
    }
}