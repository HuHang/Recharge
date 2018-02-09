package com.webkit.recharge.controller;

import com.webkit.recharge.bean.interfaces.RechargeRequestBaseBean;
import com.webkit.recharge.bean.interfaces.TengyueCallbackBean;
import com.webkit.recharge.bean.message.Message;
import com.webkit.recharge.bean.util.InstallCert;
import com.webkit.recharge.service.IBankAppRechargeService;
import com.webkit.recharge.service.IPaymentService;
import com.webkit.recharge.service.IRechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by HH on 2018/1/22.
 */

@Controller()
@RequestMapping("/bankRecharge")
public class BankAppRechargeController {

    @Resource
    private IBankAppRechargeService bankAppRechargeService;

    @Resource
    private IPaymentService paymentService;

    @Resource
    private IRechargeService rechargeService;


    /**获取默认包
     * @param mobileTelInfo 归属地+运营商
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    Message query(@RequestParam(value = "mobileTelInfo", defaultValue = "") String mobileTelInfo) {
        return bankAppRechargeService.queryByMobileTelInfo(mobileTelInfo);
    }

    /**获取可见包
     * @param mobileTel 手机号
     * @return
     */
    @RequestMapping(value = "/{mobileTel}", method = RequestMethod.GET)
    @ResponseBody
    Message queryByNumber(@PathVariable("mobileTel") String mobileTel) {
        return bankAppRechargeService.queryInfoByMobileTel(mobileTel);
    }

    /**获取订单
     * @param mobileTel 手机号
     * @return
     */
    @RequestMapping(value = "/queryOrder/{mobileTel}", method = RequestMethod.GET)
    @ResponseBody
    Message queryOrderByNumber(@PathVariable("mobileTel") String mobileTel) {
        return bankAppRechargeService.queryOrderByMobileTel(mobileTel);
    }

    /**
     * @param rechargeRequestBaseBean
     * @return
     */
    @RequestMapping(value = "/recharge.do", method = RequestMethod.POST)
    @ResponseBody
    Message recharge(@RequestBody RechargeRequestBaseBean rechargeRequestBaseBean, HttpServletRequest request){
//        String fileName = request.getParameter("fileName");
//        String realPath = request.getSession().getServletContext().getRealPath("/ErrorImport/") + "/" + fileName;
//        System.out.println(realPath);
        return bankAppRechargeService.rechargeDo(rechargeRequestBaseBean);
    }

    @RequestMapping(value = "/{id}/recharge.do",method = RequestMethod.GET)
    @ResponseBody
    Message tengyueRecharge(@PathVariable("id") Long id){
        return rechargeService.tengyueRecharge(id);
    }


    @RequestMapping(value = "/{id}/queryOrder",method = RequestMethod.GET)
    @ResponseBody
    Message tengyueQueryOrder(@PathVariable("id") Long id){
        return rechargeService.tengyueQueryOrder(id);
    }

    @RequestMapping(value = "/{id}/payOrder.do",method = RequestMethod.GET)
    @ResponseBody
    Message payOrder(@PathVariable("id") Long id){
        return paymentService.icbcPayment(id);
    }


}
