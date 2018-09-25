package com.sell.Converter;

import com.sell.DataTransferObject.OrderDTO;
import com.sell.DateObject.OrderInfoMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/8
 * 22:56
 * #
 */
public class OrderInfoMaster2OrderDTOcvt {

    public static OrderDTO convent(OrderInfoMaster orderInfoMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderInfoMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convent(List<OrderInfoMaster> orderMasterList){
        return orderMasterList.stream()
                .map( e ->convent(e) )
                .collect(Collectors.toList());
    }
}
