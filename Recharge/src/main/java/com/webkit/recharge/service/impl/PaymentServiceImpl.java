package com.webkit.recharge.service.impl;

import cn.com.infosec.icbc.ReturnValue;
import com.icbc.b2c.model.FormData;
import com.icbc.b2c.model.OrderEntity;
import com.icbc.b2c.pay.OrderProc;
import com.webkit.recharge.bean.Order;
import com.webkit.recharge.bean.enums.OrderState;
import com.webkit.recharge.bean.interfaces.ICBCRequestBean;
import com.webkit.recharge.bean.message.Message;
import com.webkit.recharge.bean.util.Base64Util;
import com.webkit.recharge.bean.util.DateUtil;
import com.webkit.recharge.bean.util.HttpclientUtil;
import com.webkit.recharge.bean.util.RandomUtils;
import com.webkit.recharge.dao.IOrderDao;
import com.webkit.recharge.service.IPaymentService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.security.*;
import java.text.DateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.webkit.recharge.constant.http.ApiStores.icbc_ServURI;
import static com.webkit.recharge.constant.http.InterfaceParam.*;

/**
 * Created by HH on 2018/1/24.
 */
@Service("paymentService")
public class PaymentServiceImpl implements IPaymentService {

    @Resource
    IOrderDao orderDao;

    /**
     * icbcz支付
     *
     * @param orderId
     * @return
     */
    @Override
    public Message icbcPayment(Long orderId) {
        Message message = new Message();
        Order order = orderDao.selectById(orderId);
        if (order == null) {
            return null;
        }
        OrderProc orderProc = new OrderProc();
        FormData formData = null;


        try {
            formData = orderProc.orderProcess(getTranDataJson(order));
            String interfaceName = formData.getInterfaceName(); //接口名
            String interfaceVersion = formData.getInterfaceVersion(); //接口版本号
            String s = formData.getTranData();
            String tranData = new String(ReturnValue.base64enc(formData.getTranData().getBytes("GBK"))).toString();//交易报文数据
            String merSignMsg = formData.getMerCert(); //证书
            String merCert = formData.getMerSignMsg(); //签名数据

            Map param = new HashMap();
            param.put("interfaceName", interfaceName);
            param.put("interfaceVersion", interfaceVersion);
            param.put("tranData", tranData);
            param.put("merSignMsg", merSignMsg);
            param.put("merCert", merCert);
            param.put("clientType", "0");
            String responseStr = HttpclientUtil.icbcPost(icbc_ServURI, param);


            boolean result = false;
            // TODO: 2018/1/30 格式化返回值
            if (result){
                message.setResult(true);
                message.setMessage("支付中！" + responseStr);
                message.setData(param);
                order.setState(OrderState.PAYING.getCode());
                order.setDescribtion("支付中");

            }else {
                order.setState(OrderState.FAILURE_PAY.getCode());
                order.setDescribtion(responseStr);
                message.setMessage("银行受理失败！" + responseStr);
                message.setData(param);
            }

        } catch (Exception e) {
            e.printStackTrace();
            order.setState(OrderState.FAILURE_PAY.getCode());
            order.setDescribtion(e.getMessage());
            message.setMessage("支付失败" + e.getMessage());
        }

        Date date = new Date();
        order.setGmtModified(date);
        try {
             orderDao.update(order);
        }catch (Exception e){
            e.printStackTrace();
        }
        return message;
    }

    private OrderEntity getTranDataJson(Order order) {

        OrderEntity orderEntity = new OrderEntity();

        Date date = new Date();

        String crtPath = PaymentServiceImpl.class.getResource("/").getFile() + icbc_crt_path;

        String keyPath = PaymentServiceImpl.class.getResource("/").getFile() + icbc_key_path;

        orderEntity.setProproty(OrderEntity.USER_CRTPATH,  crtPath);//"com/webkit/recharge/doc/shc.e.0403.crt"
        orderEntity.setProproty(OrderEntity.USER_KEYPATH,  keyPath);//"com/webkit/recharge/doc/shc.e.0403.key"


//        orderEntity.setProproty(OrderEntity.USER_CRTPATH,  icbc_crt_path47);
//        orderEntity.setProproty(OrderEntity.USER_KEYPATH,  icbc_key_path47);

        orderEntity.setProproty(OrderEntity.USER_KEYPASSWORD, icbc_keypass);

//        orderEntity.setOrderData(OrderEntity.ORDER_DATE, DateUtil.getDate(date, "yyyyMMddHHmmss"));  //交易日期时间，必输，格式为：YYYYMMDDHHmmss目前要求在银行系统当前时间的前后十分钟范围内，否则判定交易时间非法
        orderEntity.setOrderData(OrderEntity.ORDER_DATE, "20180228" + DateUtil.getDate(date,"HHmmss"));


        orderEntity.setOrderData(OrderEntity.ORDER_ID, order.getSerialNumber());                            //订单号，必输，30位数字，每笔订单都需要有不同的订单号；客户支付后商户网站产生的一个唯一的订单号，该订单号应该在相当长的时间内不重复。工行通过订单号加订单日期来唯一确认一笔订单的重复性
        orderEntity.setOrderData(OrderEntity.AMOUNT, String.valueOf((int) (order.getTransactionSum() * 100)));                            //订单金额，必输，每笔订单一个；客户支付订单的总金额，一笔订单一个，以分为单位。不可以为零，必需符合金额标准
        orderEntity.setOrderData(OrderEntity.INSTALLMENT_TIMES, "1");                //分期期数，必输，每笔订单一个；取值：1、3、6、9、12、18、24；1代表全额付款，必须为以上数值，否则订单校验不通过
        orderEntity.setOrderData(OrderEntity.MER_ACCT, icbc_merAccet);                            //商城账号，必输，每笔订单一个，可以相同；商户入账账号，只能交易时指定。（商户付给银行手续费的账户，可以在开户的时候指定，也可以用交易指定方式；用交易指定方式则使用此商户账号）
        orderEntity.setOrderData(OrderEntity.GOODS_ID, "");                            //商品ID，选输，每笔订单一个
        orderEntity.setOrderData(OrderEntity.GOODS_NAME, "");                        //商品名称，选输，每笔订单一个
        orderEntity.setOrderData(OrderEntity.GOODS_NUM, "");                            //商品数量，选输，每笔订单一个
        orderEntity.setOrderData(OrderEntity.CARRIAGE_AMT, "0");                    //运费，选输，每笔订单一个
        orderEntity.setOrderData(OrderEntity.VERIFY_JOIN_FLAG, "0");                    //联名校验标志，必输， 取值“1”：客户支付时，网银判断该客户是否与商户联名，是则按上送金额扣帐，否则展现未联名错误；我行联名商户可送“1”；取值“0”：不检验客户是否与商户联名，按上送金额扣帐。非联名商户请送“0”；
        orderEntity.setOrderData(OrderEntity.LANGUAGE, "zh_CN");                    //语言，选输，默认为中文版取值：“EN_US”为英文版；取值：“ZH_CN”或其他为中文版。注意：大小写敏感。
        orderEntity.setOrderData(OrderEntity.CUR_TYPE, "001");                        //币种，必输，用来区分一笔支付的币种，目前工行只支持使用人民币（001）支付。取值： “001”
        orderEntity.setOrderData(OrderEntity.MER_ID, icbc_merId);                            //商城代码，必输，唯一确定一个商户的代码，由商户在工行开户时，由工行告知商户。
        orderEntity.setOrderData(OrderEntity.NOTIFY_TYPE, "HS");                    //通知类型，必输在交易转账处理完成后把交易结果通知商户的处理模式。取值“HS”：在交易完成后实时将通知信息以HTTP协议POST方式，主动发送给商户，发送地址为商户端随订单数据提交的接收工行支付结果的URL即表单中的merURL字段；取值“AG”：在交易完成后不通知商户。商户需使用浏览器登录工行的B2C商户服务网站，或者使用工行提供的客户端程序API主动获取通知信息。
        orderEntity.setOrderData(OrderEntity.RESULT_TYPE, "0");                        //通知结果类型，选输取值“0”：无论支付成功或者失败，银行都向商户发送交易通知信息；取值“1”，银行只向商户发送交易成功的通知信息。只有通知方式为HS时此值有效，如果使用AG方式，可不上送此项，但签名数据中必须包含此项，取值可为空。
        orderEntity.setOrderData(OrderEntity.MER_HINT, "");                        //商城提示，选输
        orderEntity.setOrderData(OrderEntity.REMARK1, "");                        //备注1，选输
        orderEntity.setOrderData(OrderEntity.REMARK2, "");                        //备注2，选输
        orderEntity.setOrderData(OrderEntity.MER_URL, icbc_callback_url);        //商城通知地址，必输，必须合法的URL，交易结束，将客户引导到商户的此url，即通过客户浏览器post交易结果信息到商户的此URL
        orderEntity.setOrderData(OrderEntity.MER_VAR, "");                        //商城备注，选输
        orderEntity.setOrderData(OrderEntity.BACKUP1, "");                        //备用1，选输，此字段仅供电商平台订单使用，电商平台订单必输，其他订单不输入此字段
        orderEntity.setOrderData(OrderEntity.BACKUP2, "");                        //备用2，不输，预留字段
        orderEntity.setOrderData(OrderEntity.BACKUP3, "");                        //备用3，不输，预留字段
        orderEntity.setOrderData(OrderEntity.BACKUP4, "");                        //备用4，不输，预留字段
        return orderEntity;
    }



    /**
     * merCert二进制读取并base64编码
     *
     * @return
     * @throws Exception
     */
    private String getCertBase64String() throws Exception {
        FileInputStream fileKey = null;
        String CertFile = PaymentServiceImpl.class.getResource("/").getFile() + icbc_crt_path;
        String certString = "";
        try {
            certString = new String(Base64Util.base64Encoder(getFileReadByBinary(CertFile)));
            return certString;
        } catch (IOException e) {
            if (fileKey != null) {
                fileKey.close();
            }
            return e.getMessage();
        }
    }

    /**
     * 二进制方式读文件
     *
     * @param filePath 文件路径
     * @return byte[]
     * @throws Exception
     */
    private byte[] getFileReadByBinary(String filePath) throws Exception {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            fileInputStream.close();
            return bytes;
        } catch (IOException e) {
            throw e;
        }
    }
}
