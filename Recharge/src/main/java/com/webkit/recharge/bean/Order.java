package com.webkit.recharge.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.webkit.recharge.bean.enums.BusinessType;
import com.webkit.recharge.bean.enums.OperatorType;
import com.webkit.recharge.bean.enums.OrderState;
import com.webkit.recharge.bean.enums.Provices;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created by HH on 2018/1/24.
 */
public class Order {
    private long id;

    private String mobileTel;//手机号

    private Long packageId;//包编号
    private String packageName;//包名称

    private Integer businessType;//业务类型
    private String businessTypeStr;//业务名

    private String serialNumber;//流水号

    private Integer qCellCoreCode;//归属地编码
    private String qCellCore;//归属地

    private Integer operatorCode;//运营商编号
    private String operator;//运营商

    private String itemId;//商品编号
    private String itemName;//商品名称

    private Double transactionSum;//交易金额
    private Double costPrice;//平台成本

    private String paymentNumber;//支付单号
    private String rechargeNumber;//充值单号

    private Integer state;//状态码
    private String stateStr;//状态

    private String rechargeState;//充值状态

    private String describtion;//描述

    private Date gmtCreate;//创建时间
    private Date gmtModified;//更新时间

    public Order() {
    }

    public Order(Integer state, String rechargeState, String describtion, Date gmtModified) {
        this.state = state;
        this.rechargeState = rechargeState;
        this.describtion = describtion;
        this.gmtModified = gmtModified;
    }

    public Order(Integer businessType,Long packageId, String mobileTel, String serialNumber, Integer qCellCoreCode, Integer operatorCode, String itemId, String itemName, Double transactionSum, Double costPrice, Integer state, String describtion, Date gmtCreate, Date gmtModified) {
        this.businessType = businessType;
        this.packageId = packageId;
        this.mobileTel = mobileTel;
        this.serialNumber = serialNumber;
        this.qCellCoreCode = qCellCoreCode;
        this.operatorCode = operatorCode;
        this.itemId = itemId;
        this.itemName = itemName;
        this.transactionSum = transactionSum;
        this.costPrice = costPrice;
        this.state = state;
        this.describtion = describtion;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMobileTel() {
        return mobileTel;
    }

    public void setMobileTel(String mobileTel) {
        this.mobileTel = mobileTel;
    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getBusinessTypeStr() {
        return StringUtils.isBlank(businessTypeStr) ? BusinessType.getDescription(getBusinessType()) : businessTypeStr;
    }

    public void setBusinessTypeStr(String businessTypeStr) {
        this.businessTypeStr = businessTypeStr;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getqCellCoreCode() {
        return qCellCoreCode == null ? Provices.getCode(getqCellCore()) : qCellCoreCode;
    }

    public void setqCellCoreCode(Integer qCellCoreCode) {
        this.qCellCoreCode = qCellCoreCode;
    }

    public String getqCellCore() {
        return StringUtils.isBlank(qCellCore) ? Provices.getDescription(getqCellCoreCode()) : qCellCore;
    }

    public void setqCellCore(String qCellCore) {
        this.qCellCore = qCellCore;
    }

    public Integer getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(Integer operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getOperator() {
        return StringUtils.isBlank(operator) ? OperatorType.getDescription(getOperatorCode()) : operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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

    public Double getTransactionSum() {
        return transactionSum;
    }

    public void setTransactionSum(Double transactionSum) {
        this.transactionSum = transactionSum;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStateStr() {
        return StringUtils.isBlank(stateStr) ? OrderState.getDescription(getState()) : stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }

    public String getRechargeState() {
        return rechargeState;
    }

    public void setRechargeState(String rechargeState) {
        this.rechargeState = rechargeState;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public String getRechargeNumber() {
        return rechargeNumber;
    }

    public void setRechargeNumber(String rechargeNumber) {
        this.rechargeNumber = rechargeNumber;
    }
}
