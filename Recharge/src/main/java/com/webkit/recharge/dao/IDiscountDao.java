package com.webkit.recharge.dao;

import com.webkit.recharge.bean.Discount;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HH on 2018/1/22.
 */
@Repository
public interface IDiscountDao {

    Discount selectByBusinessType(Integer businessType);

    List<Discount> findAll();

    void create(Discount discount);

    Integer update(Discount discount);

}
