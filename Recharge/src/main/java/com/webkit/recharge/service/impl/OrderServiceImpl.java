package com.webkit.recharge.service.impl;

import com.webkit.recharge.bean.Order;
import com.webkit.recharge.bean.message.Message;
import com.webkit.recharge.dao.IOrderDao;
import com.webkit.recharge.service.IOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HH on 2018/2/2.
 */
@Service("orderService")
public class OrderServiceImpl implements IOrderService {
    @Resource
    IOrderDao orderDao;

    @Override
    public Message queryOrdersByMobileTelWithState(String mobileTel, String orderState) {
        Message message = new Message();

        if (StringUtils.isBlank(mobileTel)) {
            message.setMessage("查询信息不完整！");
            return message;
        }

        List<Order> orders;
        switch (orderState) {
            case "success":
                orders = orderDao.findAllSuccessByMobileTel(mobileTel);
                break;
            case "failure":
                orders = orderDao.findAllFailureByMobileTel(mobileTel);
                break;
            case "inhand":
                orders = orderDao.findAllDoingByMobileTel(mobileTel);
                break;
            default:
                orders = orderDao.findAllByMobileTel(mobileTel);
                break;
        }
        message.setResult(true);
        message.setData(orders);
        return message;
    }
}
