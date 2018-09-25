package com.sell.Service.IMPL;

import com.sell.Converter.OrderInfoMaster2OrderDTOcvt;
import com.sell.DataTransferObject.CartDTO;
import com.sell.DataTransferObject.OrderDTO;
import com.sell.DateObject.OrderDetail;
import com.sell.DateObject.OrderInfoMaster;
import com.sell.DateObject.ProductInfo;
import com.sell.Enums.OrderStatusEnum;
import com.sell.Enums.PayStatusEnum;
import com.sell.Enums.ResultEnum;
import com.sell.Exception.OrderSellException;
import com.sell.Repository.OrderDetailRepository;
import com.sell.Repository.OrderInfoMasterRepository;
import com.sell.Service.OrderService;
import com.sell.Service.Payservice;
import com.sell.Utils.RandomKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/7
 * 20:26
 * #
 */

@Service
@Slf4j
public class OrderServiceIMPL implements OrderService {

    @Autowired
    private OrderInfoMasterRepository orderInfoMasterRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductInfoServiceIMPL productInfoService;

    OrderDTO orderDTO = new OrderDTO();
    private OrderDetail orderDetail = new OrderDetail();
    private OrderInfoMaster orderInfoMaster = new OrderInfoMaster();
    private BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

    Payservice payservice = new Payservice() {
        @Override
        public Payservice refond() {
            return null;
        }
    };

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = RandomKeyUtil.genUniqueKey();

        //1,查询商品（判断数量，价格）

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()
                ) {
            ProductInfo productInfo = productInfoService
                    .findOne(orderDetail.getProductId());

            System.out.println("///////1查询" + productInfo);
            //判断数量
            if (productInfo == null) {
                throw new OrderSellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2，计算总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(
                            orderDetail.getProductQuantity())
                            .add(orderAmount));
            //3,订单详情orderDetail入库
            BeanUtils.copyProperties(productInfo, orderDetail);

            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(RandomKeyUtil.genUniqueKey());

            System.out.println(">>>>" + orderDetail);
            orderDetailRepository.save(orderDetail);
        }


            //3，写入数据库（orderMaster和orderDetail）
            orderDTO.setOrderId(orderId);
            BeanUtils.copyProperties(orderDTO, orderInfoMaster);
            //orderInfoMaster.setOrderId(orderId);
            orderInfoMaster.setOrderAmount(orderAmount);
            orderInfoMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
            orderInfoMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

            System.out.println("........" + orderInfoMaster);
            orderInfoMasterRepository.save(orderInfoMaster);

            //4，扣库存
            List<CartDTO> cartDTOList = orderDTO.getOrderDetailList()
                    .stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                    .collect(Collectors.toList());

        System.out.println("xxxxx+++" + cartDTOList);
            for (CartDTO cartDTO : cartDTOList
                    ) {
                System.out.println("扣库存+++" + cartDTO);
                productInfoService.decreaseStock(cartDTO);
            }
        return orderDTO;
    }

    //查询订单order_infor_master
    @Override
    public OrderDTO findOneOrder(String orderId) {
        orderInfoMaster.setOrderId(orderId);
        Example<OrderInfoMaster> example = Example.of(orderInfoMaster);
        OrderInfoMaster infoMaster = orderInfoMasterRepository.findOne(example).orElse(orderInfoMaster);
        //判断订单是否存在
        if (infoMaster == null){
            throw new OrderSellException(ResultEnum.ORDER_NOT_EXIST);
        }
        System.out.println(">>>>findOneOrder"+infoMaster);
        BeanUtils.copyProperties(infoMaster,orderDTO );
        System.out.println(">>>>findOneOrder"+orderDTO);

        orderDTO.setOrderDetailList(orderDetailRepository.findByOrderId(orderId));

        return orderDTO;
    }

    //分页查找同一个买家所有的订单
    @Override
    @Transactional
    public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {

        Page<OrderInfoMaster> orderInfoMasterPage = orderInfoMasterRepository
                .findByBuyerOpenid(buyerOpenId,pageable );
        System.out.println("1+++++++"+orderInfoMasterPage);
        List<OrderDTO> orderDTOList = OrderInfoMaster2OrderDTOcvt
                .convent(orderInfoMasterPage.getContent());

        System.out.println("2+++++++"+orderDTOList);

        Page<OrderDTO> orderDTOPage = new
                PageImpl<>(orderDTOList,pageable, orderInfoMasterPage.getTotalElements());
        System.out.println(orderDTOPage);
        return orderDTOPage;
    }


    @Override
    @Transactional
    public OrderDTO cancle(OrderDTO orderDTO) {
        //判断订单状态，不能是取消的状态
        if (orderDTO.getOrderStatus().equals(OrderStatusEnum.CANCLE.getCode())){
            throw new OrderSellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态到取消
        orderDTO.setOrderStatus(OrderStatusEnum.CANCLE.getCode());
        BeanUtils.copyProperties(orderDTO, orderInfoMaster);
        System.out.println("1++++++++="+orderInfoMaster);
        OrderInfoMaster updateResult = orderInfoMasterRepository.save(orderInfoMaster);
        if (updateResult == null){
            log.error("[取消订单失败] orderInfoMaster{}"+orderInfoMaster);
            throw new OrderSellException(ResultEnum.ORDER_CANCLE_ERROR);
        }

        //返还库存
        orderDTO.getOrderDetailList();
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[取消订单失败] 订单中无商品详情 orderDTO{}"+orderDTO);
            throw new OrderSellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOList);

        //如果付款，退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            payservice.refond();
            System.out.println("退款了");
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[完结订单] 订单状态不正确 orderId={} orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new OrderSellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //更改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        //判断订单非空
        if (orderDTO==null){
            return null;
        }
        //入库
        BeanUtils.copyProperties(orderDTO,orderInfoMaster );
        OrderInfoMaster master = orderInfoMasterRepository.save(orderInfoMaster);
        if (master==null){
            log.error("[完结订单失败] 更新失败 master={}",master);
            throw  new OrderSellException(ResultEnum.ORDER_UPDATE_FAILED);
        }
        return orderDTO ;
    }

    @Override
    @Transactional
    public OrderDTO payOrder(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[完成订单支付] 订单状态不正确，不是新下单 orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new OrderSellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("[完成订单支付] 支付状态不正确，不是未支付 orderDTO={}",orderDTO);
            throw new OrderSellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        //入库
        if (orderDTO==null){
            return null;
        }
        //入库
        BeanUtils.copyProperties(orderDTO,orderInfoMaster );
        OrderInfoMaster master = orderInfoMasterRepository.save(orderInfoMaster);
        if (master==null){
            log.error("[完成订单支付] 更新失败 orderMaster={}",master);
            throw  new OrderSellException(ResultEnum.ORDER_UPDATE_FAILED);
        }
        return orderDTO ;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        return null;
    }
}
