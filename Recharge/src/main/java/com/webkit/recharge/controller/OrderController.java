package com.webkit.recharge.controller;

import com.webkit.recharge.bean.message.Message;
import com.webkit.recharge.service.IOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by HH on 2018/2/2.
 */
@Controller()
@RequestMapping("/order")
public class OrderController {
    @Resource
    IOrderService orderService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    Message queryByMobileTelWithState(@RequestParam(value = "mobileTel", defaultValue = "") String mobileTel,
                                      @RequestParam(value = "state", defaultValue = "") String state) {
        return orderService.queryOrdersByMobileTelWithState(mobileTel, state);
    }

}


