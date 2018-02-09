package com.webkit.recharge.service.impl;

import com.webkit.recharge.bean.Callfare;
import com.webkit.recharge.bean.Dataflow;
import com.webkit.recharge.bean.enums.PackageType;
import com.webkit.recharge.bean.message.Message;
import com.webkit.recharge.bean.util.DataSizeUtil;
import com.webkit.recharge.dao.IDataflowDao;
import com.webkit.recharge.service.IDataflowService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by HH on 2018/1/22.
 */

@Service("dataflowService")
public class DataflowServiceImpl implements IDataflowService {

    @Resource
    private IDataflowDao dataflowDao;

    @Override
    public Message query() {
        Message message = new Message();
        message.setResult(true);
        List<Dataflow> dataflowList = dataflowDao.findAll();
        message.setMessage("查询成功！");
        message.setData(dataflowList);
        return message;
    }

    @Override
    public Message create(Dataflow dataflow) {
        Message message = new Message();

        Message validMsg = validate(dataflow, false);
        if (!validMsg.getResult()) {
            message.setMessage(validMsg.getMessage());
            return message;
        }

        dataflow.getPackageName().toUpperCase();

        if (!dataflow.getIsMobile()){
            dataflow.setMobilePrice(0.00);
        }

        if (!dataflow.getIsTelecom()){
            dataflow.setTelecomPrice(0.00);
        }

        if (!dataflow.getIsUnicom()){
            dataflow.setUnicomPrice(0.00);
        }


        dataflow.setPackageSize(DataSizeUtil.getSizeString(dataflow.getPackageName()));

        Date date = new Date();
        dataflow.setGmtCreate(date);
        dataflow.setGmtModified(date);

        try {
            dataflowDao.create(dataflow);
            if (dataflow.getId() == 0) {
                message.setMessage("新建流量包错误！");
            } else {
                message.setResult(true);
                message.setData(dataflow);
                message.setMessage("新建流量包成功！");
            }
        } catch (Exception e) {
            message.setMessage(e.getMessage());
        }
        return message;
    }

    private Message validate(Dataflow dataflow, boolean isUpdate) {
        Message message = new Message();
        message.setResult(false);

        if (StringUtils.isBlank(dataflow.getPackageName())) {
            message.setMessage("包名不可为空！");
            return message;
        }

        if (!dataflow.getIsUnicom() && !dataflow.getIsTelecom() && !dataflow.getIsMobile()){
            message.setMessage("至少为该流量包分配一个运营商！");
            return message;
        }


        if (dataflow.getIsMobile() && (dataflow.getMobilePrice() == null || dataflow.getMobilePrice() == 0)) {
            message.setMessage("移动价格不可为空！");
            return message;
        }

        if (dataflow.getIsTelecom() && (dataflow.getTelecomPrice() == null || dataflow.getTelecomPrice() == 0)) {
            message.setMessage("电信价格不可为空！");
            return message;
        }

        if (dataflow.getIsUnicom() && (dataflow.getUnicomPrice() == null || dataflow.getUnicomPrice() == 0)) {
            message.setMessage("联通价格不可为空！");
            return message;
        }
        if (dataflow.getPackageType() == PackageType.GENERAL.getCode()) {
            List<Dataflow> d = dataflowDao.selectPackageName(dataflow.getPackageName());
            if (d.size() != 0) {
                message.setMessage("此普通包已存在！");
                return message;
            }
        }


        message.setResult(true);
        return message;
    }
}
