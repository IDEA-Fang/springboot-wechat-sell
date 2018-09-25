package com.sell.Converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sell.DataTransferObject.OrderDTO;
import com.sell.DateObject.OrderDetail;
import com.sell.Enums.ResultEnum;
import com.sell.Exception.OrderSellException;
import com.sell.From.OrderFrom;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/11
 * 15:33
 * #
 */
@Slf4j
public class OrderFrom2OrderDTOcvt {

    public static OrderDTO convent(OrderFrom orderFrom) {

        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderFrom.getName());
        orderDTO.setBuyerPhone(orderFrom.getPhone());
        orderDTO.setBuyerAdress(orderFrom.getAddress());
        orderDTO.setBuyerOpenid(orderFrom.getOpenid());

        List<OrderDetail > orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderFrom.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        }catch (Exception e){
            log.error("[gson对象转换错误] orderFrom.getItems={}",orderFrom.getItems() );
            throw new OrderSellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

}
