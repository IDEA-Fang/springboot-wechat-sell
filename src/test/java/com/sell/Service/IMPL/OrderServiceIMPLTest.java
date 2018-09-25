package com.sell.Service.IMPL;

import com.sell.DataTransferObject.OrderDTO;
import com.sell.DateObject.OrderDetail;
import com.sell.DateObject.OrderInfoMaster;
import com.sell.Enums.OrderStatusEnum;
import com.sell.Enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/7
 * 22:35
 * #
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceIMPLTest {

    @Autowired
    private OrderServiceIMPL orderService;

    private OrderInfoMaster orderInfoMaster = new OrderInfoMaster();

    private String orderId = "1525963418892981406";

    String buyerOpenId = "8899554";

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("订单1-天猫");
        orderDTO.setBuyerPhone("119");
        orderDTO.setBuyerAdress("杭州萧山");
        orderDTO.setBuyerOpenid(buyerOpenId);

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("213123131");
        o1.setProductQuantity(2);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("7799443");
        o2.setProductQuantity(2);

       //orderDetailList.add(o2);
       orderDetailList.add(o1);

        orderDTO.setOrderDetailList(orderDetailList);
        System.out.println("[orderDetailList]"+orderDetailList);
        log.info("[orderDetailList]"+orderDetailList);

        OrderDTO result = orderService.create(orderDTO);
        log.info("[创建订单]"+result);
        Assert.assertNotNull(result);


    }

    @Test
    public void cancle() {
        OrderDTO result ,orderDTO = new OrderDTO();
        orderDTO = orderService.findOneOrder("1525960135868191241");
        System.out.println("2<<<<<<<++++++++++++"+orderDTO);
        result = orderService.cancle(orderDTO);
        Assert.assertNotNull(result);



    }

    @Test
    public void findOneOrder() {
        OrderDTO orderDTO = orderService.findOneOrder(orderId);
        System.out.println(orderDTO);
        Assert.assertNotNull(orderDTO);

    }

    @Test
    public void findList() {
        PageRequest request = new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findList(buyerOpenId, request);
        System.out.println(orderDTOPage);
        Assert.assertNotNull(orderDTOPage);
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOneOrder(orderId);
        System.out.println(orderDTO);
        OrderDTO result = orderService.finish(orderDTO);
        System.out.println(result);
        Assert.assertEquals(OrderStatusEnum.FINISH.getCode(),orderDTO.getOrderStatus() );

    }

    @Test
    public void payOrder() {
        OrderDTO orderDTO = orderService.findOneOrder(orderId);
        System.out.println(orderDTO);
        OrderDTO result = orderService.payOrder(orderDTO);
        System.out.println(result);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus() );
    }

    @Test
    public void findList1() {
    }
}