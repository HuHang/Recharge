package com.webkit.recharge.service;

import com.webkit.recharge.bean.interfaces.ICBCCallBackBean;
import com.webkit.recharge.bean.interfaces.TengyueCallbackBean;
import com.webkit.recharge.bean.message.Message;
import org.springframework.stereotype.Service;

/**
 * Created by HH on 2018/1/30.
 */
@Service
public interface INotifyReceiveService {

    public Message icbcCallback(ICBCCallBackBean icbcCallBackBean);

    public Message tengyueCallBack(TengyueCallbackBean tengyueCallbackBean);
}
