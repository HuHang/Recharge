package com.webkit.recharge.service;

import com.webkit.recharge.bean.Discount;
import com.webkit.recharge.bean.message.Message;
import org.springframework.stereotype.Service;

/**
 * Created by HH on 2018/1/22.
 */
@Service
public interface IDiscountService {

    Message query();

    Message upDate(Discount discount);

    Message create(Discount discount);
}
