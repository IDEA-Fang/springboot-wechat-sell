package com.sell.Repository;

import com.sell.DateObject.OrderInfoMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/7
 * 16:21
 * #
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderInfoMasterRepositoryTest {

    @Autowired
    private OrderInfoMasterRepository orderInfoMasterRepository;

    private OrderInfoMaster result, orderInfoMaster = new OrderInfoMaster();

    String openId = "2333333";

    @Test
    public void TestFindOne(){
        orderInfoMaster.setOrderId("1qaq");
        Example<OrderInfoMaster> example = Example.of(orderInfoMaster);
        orderInfoMaster = orderInfoMasterRepository.findOne(example).orElse(orderInfoMaster);
        System.out.println("-----"+ orderInfoMaster);
    }

    @Test
    public void TestSave(){
        orderInfoMaster.setOrderId("949479512");
        orderInfoMaster.setBuyerName("孜然土豆");
        orderInfoMaster.setBuyerAdress("不一样的哦");
        orderInfoMaster.setBuyerPhone("13100115566");
        orderInfoMaster.setBuyerOpenid(openId);
        orderInfoMaster.setOrderAmount(new BigDecimal(11));
        orderInfoMasterRepository.save(orderInfoMaster);

    }

    @Test
    public void testFindAll(){
        List<OrderInfoMaster> orderInfoMasterList = new ArrayList<>();
        orderInfoMasterList = orderInfoMasterRepository.findAll();
        System.out.println("++++++>>>>"+ orderInfoMasterList);
    }

    @Test
    public void testFindByBuyerOpenId(){
        PageRequest pageRequest = PageRequest.of(0,2);
        Page<OrderInfoMaster> page = orderInfoMasterRepository.findByBuyerOpenid(openId,pageRequest);
        System.out.println("______byOpenId"+ page);
    }

}