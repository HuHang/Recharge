package com.webkit.recharge.service.impl;

import com.google.gson.Gson;
import com.webkit.recharge.bean.BusinessBaseBean;
import com.webkit.recharge.bean.Callfare;
import com.webkit.recharge.bean.Dataflow;
import com.webkit.recharge.bean.Order;
import com.webkit.recharge.bean.enums.*;
import com.webkit.recharge.bean.interfaces.RechargeRequestBaseBean;
import com.webkit.recharge.bean.interfaces.RechargeResponseBaseBean;
import com.webkit.recharge.bean.interfaces.TaobaoMobileSegmentResponseBean;
import com.webkit.recharge.bean.message.Message;
import com.webkit.recharge.bean.util.*;
import com.webkit.recharge.dao.ICallfareDao;
import com.webkit.recharge.dao.IDataflowDao;
import com.webkit.recharge.dao.IDiscountDao;
import com.webkit.recharge.dao.IOrderDao;
import com.webkit.recharge.service.IBankAppRechargeService;
import com.webkit.recharge.service.IPaymentService;
import com.webkit.recharge.service.IRechargeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.*;

import static com.webkit.recharge.bean.enums.BusinessType.CALLFARE;
import static com.webkit.recharge.constant.http.ApiStores.taobao_mobile_tel_segment;

/**
 * Created by HH on 2018/1/22.
 */
@Service("banckAppRechargeService")
public class BanckAppRechargeServiceImpl implements IBankAppRechargeService {

    @Resource
    private ICallfareDao callfareDao;

    @Resource
    private IDataflowDao dataflowDao;

    @Resource
    private IDiscountDao discountDao;

    @Resource
    private IOrderDao orderDao;

    @Resource
    private IRechargeService rechargeService;

    @Resource
    private IPaymentService paymentService;

    /**
     * 通过归属地 运营商获取 包
     *
     * @param numberTelInfo
     * @return
     */
    @Override
    public Message queryByMobileTelInfo(String numberTelInfo) {
        Message message = new Message();
        message.setResult(true);

        //话费折扣
        Double callforeDiscount = discountDao.selectByBusinessType(CALLFARE.getCode()).getStandardDiscount();
        //流量折扣
        Double dataflowDiscount = discountDao.selectByBusinessType(BusinessType.DATAFLOW.getCode()).getStandardDiscount();

        List<Callfare> callfareList = new ArrayList<>(0);
        List<Dataflow> dataflowList = new ArrayList<>(0);

        if (StringUtils.isBlank(numberTelInfo)) {
            callfareList = callfareDao.findAllDefault();
            message.setData(mapResponseData(callfareList, dataflowList, callforeDiscount, dataflowDiscount, 0, 0));
            return message;
        }

        //运营商
        String operator = StringUtils.substring(numberTelInfo, -2);
        Integer operatorCode = OperatorType.getCode(operator);
        //归属地
        String qCellCore = StringUtils.substring(numberTelInfo, 0, -2);
        Integer qCellCoreCode = Provices.getCode(qCellCore);

        switch (operator) {
            case "移动":
                callfareList = callfareDao.selectMobile(qCellCoreCode);
                dataflowList = dataflowDao.selectMobile(qCellCoreCode);
                break;
            case "电信":
                callfareList = callfareDao.selectTelecom(qCellCoreCode);
                dataflowList = dataflowDao.selectTelecom(qCellCoreCode);
                break;
            case "联通":
                callfareList = callfareDao.selectUnicom(qCellCoreCode);
                dataflowList = dataflowDao.selectUnicom(qCellCoreCode);
                break;
            default:
                break;
        }

        message.setData(mapResponseData(callfareList, dataflowList, callforeDiscount, dataflowDiscount, operatorCode, qCellCoreCode));
        return message;
    }


    /**
     * 通过手机号获取 包
     *
     * @param mobileTel
     * @return
     */
    @Override
    public Message queryInfoByMobileTel(String mobileTel) {
        Message message = new Message();
        if (!AccountValidatorUtil.isMobile(mobileTel)) {
            message.setMessage("电话号码不合法！");
            return message;
        }

        String response = HttpclientUtil.doGet(taobao_mobile_tel_segment + mobileTel);
        Gson gson = new Gson();
        try {
            String jsonString = response.replaceAll("^[__]\\w{14}+[_ = ]+", "");
            TaobaoMobileSegmentResponseBean taobaoMobileSegmentResponseBean = gson.fromJson(jsonString, TaobaoMobileSegmentResponseBean.class);

            Message queryDataMsg = queryByMobileTelInfo(taobaoMobileSegmentResponseBean.getCarrier());
            message.setMessage(queryDataMsg.getMessage());
            message.setResult(queryDataMsg.getResult());
            message.setData(queryDataMsg.getData());
        } catch (Exception e) {
            message.setMessage("号码信息获取失败！" + e.getMessage());
        }
        return message;
    }

    /**创建订单
     * @param rechargeRequestBaseBean
     * @return
     */
    @Override
    public Message rechargeDo(RechargeRequestBaseBean rechargeRequestBaseBean) {
        Message message = new Message();
        if (!AccountValidatorUtil.isMobile(rechargeRequestBaseBean.getMobileTel())) {
            message.setMessage("电话号码不合法！");
            return message;
        }
        Long packageId = rechargeRequestBaseBean.getPackageId();
        int businessType = rechargeRequestBaseBean.getBusinessType().intValue();
        Integer operatorId = rechargeRequestBaseBean.getOperatorId();

        BusinessBaseBean businessBaseBean = new BusinessBaseBean();
        Double price = 0.0;
        switch (businessType) {
            case 100:
                Callfare callfare = callfareDao.selectById(packageId);
                if (callfare == null) {
                    message.setMessage("该话费包不存在！");
                    return message;
                }
                BeanUtils.copyProperties(callfare, businessBaseBean);
                price = callfare.getStandardPrice();
                break;
            case 200:
                Dataflow dataflow = dataflowDao.selectById(packageId);
                if (dataflow == null) {
                    message.setMessage("该流量包不存在！");
                    return message;
                }
                BeanUtils.copyProperties(dataflow, businessBaseBean);
                price = getDataFlowPrices(null, operatorId, dataflow, discountDao.selectByBusinessType(BusinessType.DATAFLOW.getCode()).getStandardDiscount())[1];
                break;
        }

        Date date = new Date();
        Order order = new Order(
                rechargeRequestBaseBean.getBusinessType(),
                rechargeRequestBaseBean.getPackageId(),
                rechargeRequestBaseBean.getMobileTel(),
                DateUtil.getDate(date, "yyyyMMddHHmmssSSS") + RandomUtils.numberString(13),
                rechargeRequestBaseBean.getqCellCoreCode(),
                operatorId,
                businessBaseBean.getItemId(),
                businessBaseBean.getItemName(),
                price,
                price,
                OrderState.CREATE.getCode(),
                OrderState.CREATE.getName(),
                date,
                date);
        try {
            orderDao.create(order);
            if (order.getId() == 0) {
                message.setMessage("订单创建失败！");
                message.setData(order);
                return message;
            }

            //充值
            Message payCreateMsg = paymentService.icbcPayment(order.getId());
            message.setResult(payCreateMsg.getResult());
            if (payCreateMsg.getResult()) {
                message.setMessage("订单创建成功,充值中");
                message.setData(payCreateMsg.getData());

                // TODO: 2018/1/24 多线程
                Thread thread = new Thread(new payThread() {
                    @Override
                    public void run() {
                        rechargeService.tengyueRecharge(order.getId());
                    }
                });
//                thread.start();

            } else {
                message.setMessage("订单创建成功，格式化失败：" + payCreateMsg.getMessage());
                message.setData(payCreateMsg.getData());
            }

        } catch (Exception e) {
            message.setMessage("订单创建失败:" + e.getMessage());
            message.setData(order);
            return message;
        }

        return message;
    }

    /**获取手机号购买的包
     * @param mobileTel
     * @return
     */
    @Override
    public Message queryOrderByMobileTel(String mobileTel) {
        Message message = new Message();
        if (StringUtils.isBlank(mobileTel)){
            message.setMessage("请输入查询号码！");
            return message;
        }

        List<Order> orders = orderDao.findAllByMobileTel(mobileTel);
        for (Order order : orders){
            if (order.getBusinessType() == BusinessType.CALLFARE.getCode()){
                order.setPackageName(callfareDao.selectNameById(order.getPackageId()));
            }
            if (order.getBusinessType() == BusinessType.DATAFLOW.getCode()){
                order.setPackageName(dataflowDao.selectNameById(order.getPackageId()));
            }

        }
        message.setResult(true);
        message.setData(orders);

        return message;
    }

    public abstract class payThread implements Runnable {
        @Override
        public void run() {
        }
    }

    /**
     * 格式化数据
     *
     * @param callfareList     话费包
     * @param dataflowList     流量包
     * @param callforeDiscount 话费折扣
     * @param dataflowDiscount 流量折扣
     * @param operatorCode     运营商代码
     * @param qCellCoreCode    归属地代码
     * @return 数据包面数据
     */
    private Map mapResponseData(List<Callfare> callfareList, List<Dataflow> dataflowList, Double callforeDiscount, Double dataflowDiscount, Integer operatorCode, Integer qCellCoreCode) {
        Map data = new HashMap();

        //话费包
        List<RechargeResponseBaseBean> rechargeCallforeList = new ArrayList<>();
        //流量包
        List<RechargeResponseBaseBean> rechargeDataflowList = new ArrayList<>();


        for (Callfare callfare : callfareList) {

            rechargeCallforeList.add(new RechargeResponseBaseBean(
                    callfare.getId(),
                    callfare.getPackageName(),
                    callfare.getStandardPrice().toString(),
                    SumUtil.getNumber(callfare.getStandardPrice() * callforeDiscount, "######0.00"),
                    callfare.getValidTime(),
                    callforeDiscount.toString(),
                    callfare.getPackageTypeStr(),
                    callfare.getPackageType().toString(),
                    callfare.getBusinessType().toString()));
        }

        for (Dataflow dataflow : dataflowList) {
            double[] prices = getDataFlowPrices(null, operatorCode, dataflow, dataflowDiscount);
            String standardPrice = SumUtil.getNumber(prices[0], "######0.00");
            String price = SumUtil.getNumber(prices[1], "######0.00");
            rechargeDataflowList.add(new RechargeResponseBaseBean(
                    dataflow.getId(),
                    dataflow.getPackageName(),
                    standardPrice,
                    price,
                    dataflow.getValidTime(),
                    dataflowDiscount.toString(),
                    dataflow.getPackageTypeStr(),
                    dataflow.getPackageType().toString(),
                    dataflow.getBusinessType().toString()));
        }

        Map mobileTelInfo = new HashMap();
        mobileTelInfo.put("qCellCoreCode", qCellCoreCode);
        mobileTelInfo.put("qCellCore", Provices.getDescription(qCellCoreCode));
        mobileTelInfo.put("operatorCode", operatorCode);
        mobileTelInfo.put("operator", OperatorType.getDescription(operatorCode));
        data.put("mobileTelInfo", mobileTelInfo);
        data.put("callfare", rechargeCallforeList);
        data.put("dataflow", rechargeDataflowList);
        return data;
    }

    /**
     * 获取流量包的 标准价与折扣价
     *
     * @param operator
     * @param operatorId
     * @param dataflow
     * @param dataflowDiscount
     * @return
     */
    private double[] getDataFlowPrices(String operator, Integer operatorId, Dataflow dataflow, Double dataflowDiscount) {
        if (operatorId != null && operatorId != 0) {
            operator = OperatorType.getDescription(operatorId);
        }
        Double standardPrice = 0.0;
        Double price = 0.0;
        if (!StringUtils.equals(operator, null)) {
            switch (operator) {
                case "移动":
                    standardPrice = dataflow.getMobilePrice();
                    price = dataflow.getMobilePrice() * dataflowDiscount;
                    break;
                case "电信":
                    standardPrice = dataflow.getTelecomPrice();
                    price = dataflow.getTelecomPrice() * dataflowDiscount;
                    break;
                case "联通":
                    standardPrice = dataflow.getUnicomPrice();
                    price = dataflow.getUnicomPrice() * dataflowDiscount;
                    break;
                default:
                    break;
            }
        }
        double[] prices = {standardPrice, price};
        return prices;
    }
}
