package com.webkit.recharge.service;

import com.webkit.recharge.bean.message.Message;
import org.springframework.stereotype.Service;

/**
 * Created by HH on 2018/1/24.
 */
@Service
public interface IPaymentService {

    public Message icbcPayment(Long orderId);
}
