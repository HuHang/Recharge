package com.webkit.recharge.service;

import com.webkit.recharge.bean.interfaces.TengyueCallbackBean;
import com.webkit.recharge.bean.interfaces.TengyueRequestBaseBean;
import com.webkit.recharge.bean.message.Message;
import org.springframework.stereotype.Service;

/**
 * Created by HH on 2018/1/24.
 */
@Service
public interface IRechargeService {

    public Message tengyueRecharge(Long orderId);


    public Message tengyueQueryOrder(Long orderId);
}
