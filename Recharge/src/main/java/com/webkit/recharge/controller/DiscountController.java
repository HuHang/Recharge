package com.webkit.recharge.controller;

import com.webkit.recharge.bean.Discount;
import com.webkit.recharge.bean.message.Message;
import com.webkit.recharge.service.IDiscountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by HH on 2018/1/22.
 */
@Controller
@RequestMapping("/discounts")
public class DiscountController {



    @Resource
    private IDiscountService discountService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseBody
    Message query(){
        return  discountService.query();
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    Message create(@RequestBody Discount discount){
        return discountService.create(discount);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PATCH)
    @ResponseBody
    Message update(@PathVariable("id") Long id, @RequestBody Discount discount){
        discount.setId(id);
        return discountService.upDate(discount);
    }


}
