package com.sell.Service;

import com.sell.DataTransferObject.OrderDTO;
import com.sell.DateObject.OrderInfoMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/7
 * 19:55
 * #
 */
public interface OrderService {

    //创建订单
    OrderDTO create(OrderDTO orderDTO);

    //取消订单
    OrderDTO cancle(OrderDTO orderDTO);

    //查询单个订单
    OrderDTO findOneOrder(String orderId);

    //查询买家整个订单
    Page<OrderDTO> findList(String buyerOpenId,Pageable pageable);

    //完结订单
    OrderDTO finish(OrderDTO orderDTO);

    //支付订单
    OrderDTO payOrder(OrderDTO orderDTO);

    //查询所有订单列表
    Page<OrderDTO> findList(Pageable pageable);

}
