package com.webkit.recharge.bean.interfaces;

/**
 * Created by HH on 2018/1/26.
 */
public class TengyueResponseBean {
    private String status;//受理状态
    private String code;
    private String desc;
    private String amount;//交易总金额
    private String areaCode;//省域代码
    private String bizOrderId;//充值平台方流水号 订单号
    private String carrierType;//运营商类别
    private String itemFacePrice;//商品面值单位为厘
    private String itemId;//商品编号
    private String itemName;
    private String price;//商品单价
    private String serialno;//合作方流水号
    private Boolean success;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getBizOrderId() {
        return bizOrderId;
    }

    public void setBizOrderId(String bizOrderId) {
        this.bizOrderId = bizOrderId;
    }

    public String getCarrierType() {
        return carrierType;
    }

    public void setCarrierType(String carrierType) {
        this.carrierType = carrierType;
    }

    public String getItemFacePrice() {
        return itemFacePrice;
    }

    public void setItemFacePrice(String itemFacePrice) {
        this.itemFacePrice = itemFacePrice;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }
}
