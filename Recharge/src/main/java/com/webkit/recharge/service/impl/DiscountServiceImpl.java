package com.webkit.recharge.service.impl;

import com.webkit.recharge.bean.Discount;
import com.webkit.recharge.bean.message.Message;
import com.webkit.recharge.dao.IDiscountDao;
import com.webkit.recharge.service.IDiscountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by HH on 2018/1/22.
 */

@Service("discountService")
public class DiscountServiceImpl implements IDiscountService{

    @Resource
    IDiscountDao discountDao;

    @Override
    public Message query() {
        Message message = new Message();
        message.setResult(true);
        List<Discount> discountList = discountDao.findAll();
        message.setMessage("查询成功！");
        message.setData(discountList);
        return message;
    }

    @Override
    public Message create(Discount discount) {
        Message message = new Message();

        Message validMsg = validate(discount,false);
        if (!validMsg.getResult()){
            message.setMessage(validMsg.getMessage());
            return message;
        }

        Date date = new Date();
        discount.setGmtCreate(date);
        discount.setGmtModified(date);

        try {
            discountDao.create(discount);
            if (discount.getId() == 0){
                message.setMessage("新建折扣错误！");
            }else {
                message.setResult(true);
                message.setData(discount);
                message.setMessage("新建折扣成功！");
            }
        }catch (Exception e){
            message.setMessage(e.getMessage());
        }
        return message;
    }

    @Override
    public Message upDate(Discount discount) {

        Message message = new Message();
        Date date = new Date();
        discount.setGmtModified(date);
        try {
            Integer l = discountDao.update(discount);
            if (l.intValue() == 0){
                message.setMessage("更新失败！");
            }else {
                message.setResult(true);
                message.setMessage("更新成功！");
            }
        }catch (Exception e){
            message.setMessage(e.getMessage());
        }
        return message;

    }

    private Message validate(Discount discount, boolean isUpdate){
        Message message = new Message();

        if (discount.getBusinessType() == null){
            message.setMessage("包类型不可为空！");
            return message;
        }


        if (discount.getStandardDiscount() == null){
            message.setMessage("折扣值不可为空！");
            return message;
        }

        if (!isUpdate){
            Discount d = discountDao.selectByBusinessType(discount.getBusinessType());
            if (d != null){
                message.setMessage("折扣类型已存在！");
                return message;
            }
        }

        message.setResult(true);
        return message;
    }
}
