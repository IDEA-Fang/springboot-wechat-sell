package com.sell.Repository;

import com.sell.DateObject.OrderInfoMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/7
 * 16:18
 * #
 */
public interface OrderInfoMasterRepository extends JpaRepository<OrderInfoMaster,String> {

    Page<OrderInfoMaster> findByBuyerOpenid(String buyerOpenId,Pageable pageable);
}
