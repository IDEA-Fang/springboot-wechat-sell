package com.sell.Service.IMPL;

import com.sell.DataTransferObject.OrderDTO;
import com.sell.Enums.ResultEnum;
import com.sell.Exception.OrderSellException;
import com.sell.Service.BuyerService;
import com.sell.Service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/12
 * 11:32
 * #
 */
@Service
@Slf4j
public class BuyerServiceIMPL implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOneOrder(String orderId, String openid) {

        return checkOrderOwnner(orderId, openid);
    }

    @Override
    public OrderDTO cancleOrder(String orderId, String openid) {
        OrderDTO orderDTO = checkOrderOwnner(orderId, openid);
        if (orderDTO==null){
            log.error("[取消订单] 订单为空 orderDTO={}",orderDTO);
            throw new OrderSellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancle(orderDTO);
    }

    public OrderDTO checkOrderOwnner(String orderId,String openid){
        OrderDTO orderDTO = orderService.findOneOrder(orderId);
        if (orderDTO==null){
            return null;
        }
        System.out.println("====="+orderDTO);
        if (!orderDTO.getBuyerOpenid().equals(openid)){
            log.error("[查询订单] 买家openid不正确 orderId={},orderDTO={}",orderId,orderDTO );
            throw new OrderSellException(ResultEnum.ORDER_OWNNER_ERROR);
        }
        return orderDTO;
    }
}
