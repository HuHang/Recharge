package com.webkit.recharge.service;

import com.webkit.recharge.bean.interfaces.RechargeRequestBaseBean;
import com.webkit.recharge.bean.message.Message;
import org.springframework.stereotype.Service;

/**
 * Created by HH on 2018/1/22.
 */
@Service
public interface IBankAppRechargeService {

    public Message queryByMobileTelInfo(String mobileTelInfo);

    public Message queryInfoByMobileTel(String mobileTel);

    public Message rechargeDo(RechargeRequestBaseBean rechargeRequestBaseBean);

    public Message queryOrderByMobileTel(String mobileTel);


}
