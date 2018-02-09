package com.webkit.recharge.controller;

import com.webkit.recharge.bean.interfaces.ICBCCallBackBean;
import com.webkit.recharge.bean.interfaces.RechargeRequestBaseBean;
import com.webkit.recharge.bean.interfaces.TengyueCallbackBean;
import com.webkit.recharge.bean.message.Message;
import com.webkit.recharge.service.IBankAppRechargeService;
import com.webkit.recharge.service.INotifyReceiveService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by HH on 2018/1/30.
 */

@Controller()
@RequestMapping("/callback")
public class NotifyReceiveController {

    @Resource
    private INotifyReceiveService notifyReceiveService;

    @RequestMapping(value = "/icbcNotify.do", method = RequestMethod.POST)
    @ResponseBody
    Message recharge(){
        return notifyReceiveService.icbcCallback(null);
    }



    @RequestMapping(value = "/tengyueNotify.do",method = RequestMethod.GET)
    @ResponseBody
    String tengyueCallback(@RequestParam(value = "userId",defaultValue = "") String userId,
                            @RequestParam(value = "bizId",defaultValue = "") String bizId,
                            @RequestParam(value = "ejId",defaultValue = "") String ejId,
                            @RequestParam(value = "downstreamSerialno",defaultValue = "") String downstreamSerialno,
                            @RequestParam(value = "status",defaultValue = "") String status,
                            @RequestParam(value = "sign",defaultValue = "") String sign){
        TengyueCallbackBean tengyueCallbackBean = new TengyueCallbackBean(userId,bizId,ejId,downstreamSerialno,status,sign);
        Message message = notifyReceiveService.tengyueCallBack(tengyueCallbackBean);
//        return message;
        return "success";
    }
}
