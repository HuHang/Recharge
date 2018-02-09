package com.webkit.recharge.service.impl;

import com.webkit.recharge.bean.Order;
import com.webkit.recharge.bean.enums.OrderState;
import com.webkit.recharge.bean.interfaces.ICBCCallBackBean;
import com.webkit.recharge.bean.interfaces.TengyueCallbackBean;
import com.webkit.recharge.bean.message.Message;
import com.webkit.recharge.dao.IOrderDao;
import com.webkit.recharge.service.INotifyReceiveService;
import com.webkit.recharge.service.IRechargeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.Id;
import javax.xml.crypto.Data;
import java.util.Date;

import static com.webkit.recharge.constant.http.InterfaceParam.icbc_merId;
import static com.webkit.recharge.constant.http.InterfaceParam.tengyue_userId;

/**
 * Created by HH on 2018/1/30.
 */
@Service("notifyReceiveService")
public class NotifyReceiveServiceImpl implements INotifyReceiveService{

    @Resource
    IOrderDao orderDao;

    @Resource
    private IRechargeService rechargeService;

    @Override
    public Message icbcCallback(ICBCCallBackBean icbcCallBackBean) {
        Order order = orderDao.selectBySerialNumber("20180130170207825");
        order.setDescribtion("收到通知");
        order.setGmtModified(new Date());
        updateOrder(order);



//        Order order = orderDao.selectBySerialNumber(icbcCallBackBean.getNotifyData().getOrderid());
//        if (order == null) {
//            return null;
//        }
//
//        if (StringUtils.equals(icbc_merId,icbcCallBackBean.getNotifyData().getMerID())){
//            return null;
//        }
//        Date date = new Date();
//
//        if (StringUtils.equals("1",icbcCallBackBean.getNotifyData().getTranStat())){
//            order.setState(OrderState.RECHARGEING.getCode());
//            order.setDescribtion("支付成功，充值中");
//
//            rechargeService.tengyueRecharge(order.getId());
//        }else {
//            order.setState(OrderState.FAILURE_RECHARGE.getCode());
//            order.setRechargeState("错误代码 " + icbcCallBackBean.getNotifyData().getTranStat());
//            order.setDescribtion(icbcCallBackBean.getNotifyData().getComment());
//        }
//        order.setPaymentNumber(icbcCallBackBean.getNotifyData().getTranSerialNo());
//        order.setGmtModified(date);
//        updateOrder(order);
        return null;
    }

    private Integer updateOrder(Order order){
        Integer l = 0;
        try {
             l = orderDao.update(order);
        }catch (Exception e){
            e.printStackTrace();
        }
        return l;
    }


    @Override
    public Message tengyueCallBack(TengyueCallbackBean tengyueCallbackBean) {
        Message message = new Message();
        if (!StringUtils.equals(tengyueCallbackBean.getUserId(),tengyue_userId)){
            message.setMessage("用户编号错误，请求不被受理！");
            return message;
        }

        Order order = orderDao.selectBySerialNumber(tengyueCallbackBean.getDownstreamSerialno());
        if (order == null){
            message.setMessage("该流水单号不存在！");
            return message;
        }

        String status = tengyueCallbackBean.getStatus();
        if (StringUtils.equals(status,"2")){
            order.setRechargeState("成功");
            order.setDescribtion("充值成功");
            order.setState(OrderState.SUCCESS.getCode());
        }else if (StringUtils.equals(status,"3")){
            order.setRechargeState("失败");
            order.setDescribtion("充值失败");
            order.setState(OrderState.FAILURE_RECHARGE.getCode());
        }else {
            order.setRechargeState("未知");
            order.setDescribtion("未知状态");
            order.setState(OrderState.UNKNOWN.getCode());
        }
        order.setRechargeNumber(tengyueCallbackBean.getEjId());
        order.setGmtModified(new Date());
        updateOrder(order);
        message.setResult(true);
        message.setData(order);
        return message;
    }
}
