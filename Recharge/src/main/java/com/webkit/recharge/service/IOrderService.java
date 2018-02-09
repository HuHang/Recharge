package com.webkit.recharge.service;

import com.webkit.recharge.bean.message.Message;
import org.springframework.stereotype.Service;

/**
 * Created by HH on 2018/2/2.
 */
@Service
public interface IOrderService {

    public Message queryOrdersByMobileTelWithState(String mobileTel,String orderState);
}
