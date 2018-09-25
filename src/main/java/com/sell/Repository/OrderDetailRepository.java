package com.sell.Repository;

import com.sell.DateObject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/7
 * 16:21
 * #
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    List<OrderDetail> findByOrderId(String orderId);
}
