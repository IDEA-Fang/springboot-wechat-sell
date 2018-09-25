package com.sell.Controller;

import com.sell.Converter.OrderFrom2OrderDTOcvt;
import com.sell.DataTransferObject.OrderDTO;
import com.sell.Enums.ResultEnum;
import com.sell.Exception.OrderSellException;
import com.sell.From.OrderFrom;
import com.sell.Service.BuyerService;
import com.sell.Service.IMPL.BuyerServiceIMPL;
import com.sell.Service.OrderService;
import com.sell.Utils.ResultViewOUtil;
import com.sell.ViewObject.ResultViewO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/11
 * 14:14
 * #
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private OrderService orderService;


    //创建订单
    @RequestMapping("/create")
    public ResultViewO<Map<String,String>> create(@Valid OrderFrom orderFrom,
                                                  BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("[创建订单] 参数不正确 orderFrom={}",orderFrom);
            throw new OrderSellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderFrom2OrderDTOcvt.convent(orderFrom);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[创建订单 购物车为空]");
            throw new OrderSellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO orderResult = orderService.create(orderDTO);

        Map<String ,String > map = new HashMap<>();
        map.put("orderId",orderResult.getOrderId() );

        return ResultViewOUtil.success(map);
    }

    //订单列表
    @RequestMapping("/list")
public ResultViewO<List<OrderDTO>> orderList(@RequestParam("openid") String openid,
                                             @RequestParam(value = "page",defaultValue = "0") Integer page,
                                             @RequestParam(value = "size",defaultValue = "2") Integer size){
      if (openid==null){
          log.error("[查询订单列表] openid={}" ,openid );
          throw new OrderSellException(ResultEnum.PARAM_ERROR);
      }
    PageRequest pageable = new PageRequest(page,size );
      Page<OrderDTO> orderDTOPage = orderService.findList(openid,pageable);

      return ResultViewOUtil.success(orderDTOPage.getContent());
}

    //订单详情
    @RequestMapping("/detail")
    public ResultViewO<OrderDTO> detail(@RequestParam("orderId") String orderId,
                                        @RequestParam("openid") String openid){

       OrderDTO orderDTO = buyerService.findOneOrder(orderId, openid);
       return ResultViewOUtil.success(orderDTO);
    }

    //取消订单
    @RequestMapping("cancle")
    public ResultViewO cancle(@RequestParam("orderId") String orderId,
                              @RequestParam("openid") String openid){
        buyerService.cancleOrder(orderId, openid);
        return ResultViewOUtil.success();
    }


}
