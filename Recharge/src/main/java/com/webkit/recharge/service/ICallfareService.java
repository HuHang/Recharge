package com.webkit.recharge.service;

import com.webkit.recharge.bean.Callfare;
import com.webkit.recharge.bean.message.Message;
import org.springframework.stereotype.Service;

import java.nio.channels.Pipe;
import java.util.List;

/**
 * Created by HH on 2018/1/22.
 */
@Service
public interface ICallfareService {

    public Message query();

    public Message create(Callfare callfare);

    public Message delete(Long id);

    public Message delete(List<Long> items);
}
