package com.webkit.recharge.service;

import com.webkit.recharge.bean.Callfare;
import com.webkit.recharge.bean.Dataflow;
import com.webkit.recharge.bean.message.Message;
import org.springframework.stereotype.Service;

/**
 * Created by HH on 2018/1/22.
 */
@Service
public interface IDataflowService {
    public Message query();

    public Message create(Dataflow dataflow);
}
