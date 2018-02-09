package com.webkit.recharge.service.impl;

import com.webkit.recharge.bean.Callfare;
import com.webkit.recharge.bean.enums.PackageType;
import com.webkit.recharge.bean.message.Message;
import com.webkit.recharge.bean.util.DataSizeUtil;
import com.webkit.recharge.dao.ICallfareDao;
import com.webkit.recharge.service.ICallfareService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by HH on 2018/1/22.
 */

@Service("callfareService")
public class CallfareServiceImpl implements ICallfareService {

    @Resource
    private ICallfareDao callfareDao;

    @Override
    public Message query() {
        Message message = new Message();
        message.setResult(true);
        List<Callfare> callfareList = callfareDao.findAll();
        message.setMessage("查询成功");
        message.setData(callfareList);
        return message;
    }

    @Override
    public Message create(Callfare callfare) {
        Message message = new Message();

        Message validMsg = validate(callfare, false);
        if (!validMsg.getResult()) {
            message.setMessage(validMsg.getMessage());
            return message;
        }

        if (StringUtils.isBlank(callfare.getPackageName())) {
            callfare.setPackageName(callfare.getStandardPrice() + "元");
        }

        Date date = new Date();
        callfare.setGmtCreate(date);
        callfare.setGmtModified(date);

        try {
            callfareDao.create(callfare);
            if (callfare.getId() == 0) {
                message.setMessage("新建话费包错误！");
            } else {
                message.setResult(true);
                message.setData(callfare);
                message.setMessage("新建话费包成功！");
            }
        } catch (Exception e) {
            message.setMessage(e.getMessage());
        }
        return message;
    }

    @Override
    public Message delete(Long id) {
        Message message = new Message();

        Integer l = callfareDao.delete(id);
        if (l.intValue() == 0){
            message.setMessage("删除该话费包失败！");
            return message;
        }
        message.setResult(true);
        message.setMessage("删除成功！");
        return message;
    }

    @Override
    public Message delete(List<Long> items) {
        Message message = new Message();

        Integer l = callfareDao.batchRemove(items);
        if (l.intValue() == 0){
            message.setMessage("批量删除话费包失败！");
            return message;
        }

        int difference = items.size() - l.intValue();

        if (difference != 0){
            message.setResult(true);
            message.setMessage("删除异常：" + "成功" + l.intValue() + "条," + "失败" + difference + "条！");
            return message;
        }
        message.setResult(true);
        message.setMessage("删除成功！");
        return message;
    }

    private Message validate(Callfare callfare, boolean isUpdate) {
        Message message = new Message();
        message.setResult(false);

        if (callfare.getStandardPrice() == null) {
            message.setMessage("标准价不可为空！");
            return message;
        }


        if (callfare.getPackageType() == PackageType.GENERAL.getCode()) {
            List<Callfare> c = callfareDao.selectByName(callfare.getPackageName());
            if (c.size() != 0) {
                message.setMessage("此普通包已存在！");
                return message;
            }
        }


        message.setResult(true);
        return message;
    }
}
