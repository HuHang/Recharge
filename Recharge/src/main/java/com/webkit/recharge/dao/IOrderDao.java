package com.webkit.recharge.dao;

import com.webkit.recharge.bean.Order;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by HH on 2018/1/24.
 */
@Repository
public interface IOrderDao {

    List<Order> findAll();

    List<Order> findAllByMobileTel(String mobileTel);

    List<Order> findAllSuccessByMobileTel(String mobileTel);

    List<Order> findAllFailureByMobileTel(String mobileTel);

    List<Order> findAllDoingByMobileTel(String mobileTel);

    Order selectBySerialNumber(String serialNumber);

    void create(Order order);

    Integer update(Order order);

    Order selectById(Long id);
}
