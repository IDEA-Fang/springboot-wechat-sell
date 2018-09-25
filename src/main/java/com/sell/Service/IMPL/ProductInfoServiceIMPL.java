package com.sell.Service.IMPL;

import com.sell.DataTransferObject.CartDTO;
import com.sell.DateObject.ProductInfo;
import com.sell.Enums.ProductStatusEnum;
import com.sell.Enums.ResultEnum;
import com.sell.Exception.OrderSellException;
import com.sell.Repository.ProductInfoRepository;
import com.sell.Service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/5
 * 21:43
 * #商品
 */
@Service
public class ProductInfoServiceIMPL implements ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    private ProductInfo result,productInfo = new ProductInfo();

    @Override
    public ProductInfo findOne(String productId) {
        productInfo.setProductId(productId);
        Example<ProductInfo> example = Example.of(productInfo);
        result = productInfoRepository.findOne(example).orElse(productInfo);
        return result;
    }

    //分页查询所有
    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        Page<ProductInfo> infoPage = productInfoRepository.findAll(pageable);
        return infoPage;
    }

    @Override
    public List<ProductInfo> findUpAll() {
        List<ProductInfo> list = productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
        return list;
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        result = productInfoRepository.save(productInfo);
        return result;
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList
                ) {
            productInfo.setProductId(cartDTO.getProductId());
            Example<ProductInfo> example = Example.of(productInfo);
            productInfo =  productInfoRepository.findOne(example).orElse(productInfo);

            if (productInfo == null){ throw new OrderSellException(ResultEnum.PRODUCT_STOCK_ERROR); }

            Integer integer = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(integer);
            productInfoRepository.save(productInfo);
        }

    }

    @Override
    public void decreaseStock(CartDTO cartDTO) {
            productInfo.setProductId(cartDTO.getProductId());
            Example<ProductInfo> example = Example.of(productInfo);
            productInfo = productInfoRepository.findOne(example).orElse(productInfo);
            System.out.println("扣库存--查询"+productInfo);
            if (productInfo == null){ throw new OrderSellException(ResultEnum.PRODUCT_STOCK_ERROR); }

            Integer integer = productInfo.getProductStock() - cartDTO.getProductQuantity();
            System.out.println("存入数据"+integer);
            productInfo.setProductStock(integer);
            System.out.println("存入数据库之前"+productInfo);
            productInfoRepository.save(productInfo);
    }
}
