package com.webkit.recharge.service.impl;

import com.google.gson.Gson;
import com.sun.xml.internal.bind.v2.TODO;
import com.webkit.recharge.bean.Order;
import com.webkit.recharge.bean.enums.OrderState;
import com.webkit.recharge.bean.interfaces.TengyueCallbackBean;
import com.webkit.recharge.bean.interfaces.TengyueRequestBaseBean;
import com.webkit.recharge.bean.interfaces.TengyueResponseBean;
import com.webkit.recharge.bean.message.Message;
import com.webkit.recharge.bean.util.DateUtil;
import com.webkit.recharge.bean.util.HttpclientUtil;
import com.webkit.recharge.bean.util.MD5Util;
import com.webkit.recharge.dao.IOrderDao;
import com.webkit.recharge.service.IRechargeService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.webkit.recharge.constant.http.ApiStores.tengyue_queryBizOrder;
import static com.webkit.recharge.constant.http.ApiStores.tengyue_recharge;
import static com.webkit.recharge.constant.http.InterfaceParam.tengyue_privateKey;
import static com.webkit.recharge.constant.http.InterfaceParam.tengyue_userId;

/**
 * Created by HH on 2018/1/24.
 */
@Service("rechargeService")
public class RechargeServiceImpl implements IRechargeService{

    @Resource
    IOrderDao orderDao;

    @Override
    public Message tengyueRecharge(Long orderId) {
        Message message = new Message();
        Order order = orderDao.selectById(orderId);
        if (order == null){
            return null;
        }

        Date date = new Date();
        String dtCreate = DateUtil.getDate(date,"yyyyMMddHHmmss");
        TengyueRequestBaseBean tengyueRequestBaseBean= new TengyueRequestBaseBean(
                tengyue_userId,
                order.getItemId(),
                order.getMobileTel(),
                order.getSerialNumber(),
                dtCreate,
                "");

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(dtCreate);
        stringBuffer.append(tengyueRequestBaseBean.getItemId());
        stringBuffer.append(tengyueRequestBaseBean.getSerialno());
        stringBuffer.append(tengyueRequestBaseBean.getUid());
        stringBuffer.append(tengyueRequestBaseBean.getUserId());
        stringBuffer.append(tengyue_privateKey);
//        String sign = MD5Util.HEXAndMd5(stringBuffer.toString());
        String sign =  DigestUtils.md5Hex(stringBuffer.toString());

        tengyueRequestBaseBean.setSign(sign);

        StringBuffer urlData = new StringBuffer();
        urlData.append("?sign=").append(tengyueRequestBaseBean.getSign());
        urlData.append("&uid=").append(tengyueRequestBaseBean.getUid());
        urlData.append("&dtCreate=").append(tengyueRequestBaseBean.getDtCreate());
        urlData.append("&userId=").append(tengyueRequestBaseBean.getUserId());
        urlData.append("&itemId=").append(tengyueRequestBaseBean.getItemId());
        urlData.append("&serialno=").append(tengyueRequestBaseBean.getSerialno());



        //http://47.95.239.243:8165/index.do?sign=c610ef20aab2dc7cacdb7f21fed80db1&uid=17765198634&dtCreate=20180124182224&userId=679&itemId=6606&serialno=20180124182224427

        List<String> strings = new ArrayList<>();
        strings.add(stringBuffer.toString());
        strings.add(sign);
        message.setMessages(strings);

        String url = tengyue_recharge + urlData.toString();
        String response = HttpclientUtil.doGet(url);

        Gson gson = new Gson();
        TengyueResponseBean tengyueResponseBean = new TengyueResponseBean();
        try {
            tengyueResponseBean = gson.fromJson(response,TengyueResponseBean.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        if (tengyueResponseBean.getSuccess()){
            order.setState(OrderState.RECHARGEING.getCode());
            order.setRechargeState("等待返回");
            order.setRechargeNumber(tengyueResponseBean.getBizOrderId());
        }else {
            order.setRechargeState("未受理");
            order.setState(OrderState.FAILURE_RECHARGE.getCode());
        }

        order.setDescribtion(tengyueResponseBean.getDesc());
        order.setGmtModified(date);
        try {
            orderDao.update(order);
        }catch (Exception e){
            e.printStackTrace();
        }

        message.setMessage(url);
        message.setData(order);
        return message;
    }



    @Override
    public Message tengyueQueryOrder(Long orderId) {
        Message message = new Message();

        Order order = orderDao.selectById(orderId);
        if (order == null){
            message.setMessage("订单不存在！");
            return message;
        }
        TengyueRequestBaseBean tengyueRequestBaseBean = new TengyueRequestBaseBean(
                tengyue_userId,
                order.getSerialNumber(),
                "");
//        TengyueRequestBaseBean tengyueRequestBaseBean = new TengyueRequestBaseBean(
//                "517",
//                "2018012614373443620319",
//                "");

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(tengyueRequestBaseBean.getSerialno());
        stringBuffer.append(tengyueRequestBaseBean.getUserId());
        stringBuffer.append(tengyue_privateKey);
//        stringBuffer.append("cfbfda8585895ce1759bcc496cc093f5addd5c864f0c22e2303a152f0998e3e3");
        String sign =  DigestUtils.md5Hex(stringBuffer.toString());

        tengyueRequestBaseBean.setSign(sign);

        StringBuffer urlData = new StringBuffer();
        urlData.append("?sign=").append(tengyueRequestBaseBean.getSign());
        urlData.append("&userId=").append(tengyueRequestBaseBean.getUserId());
        urlData.append("&serialno=").append(tengyueRequestBaseBean.getSerialno());

        String url = tengyue_queryBizOrder + urlData.toString();
        String response = HttpclientUtil.doGet(url);
        message.setData(response);
        //517
        //2018012614373443620319
        //cfbfda8585895ce1759bcc496cc093f5addd5c864f0c22e2303a152f0998e3e3

        return message;
    }
}
