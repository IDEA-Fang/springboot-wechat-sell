package com.sell.Service;

import com.sell.DataTransferObject.OrderDTO;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/12
 * 11:25
 * #
 */
public interface BuyerService {

    //查寻一个订单
    OrderDTO findOneOrder(String orderId,String openid);

    //取消一个订单
    OrderDTO cancleOrder(String orderId,String openid);
}
