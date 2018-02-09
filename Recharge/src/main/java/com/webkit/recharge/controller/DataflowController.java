package com.webkit.recharge.controller;

import com.webkit.recharge.bean.Callfare;
import com.webkit.recharge.bean.Dataflow;
import com.webkit.recharge.bean.message.Message;
import com.webkit.recharge.service.IDataflowService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by HH on 2018/1/22.
 */
@Controller
@RequestMapping("/dataflows")
public class DataflowController {

    @Resource
    private IDataflowService dataflowService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseBody
    Message query(){
        return  dataflowService.query();
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    Message create(@RequestBody Dataflow dataflow){
        return dataflowService.create(dataflow);
    }
}
