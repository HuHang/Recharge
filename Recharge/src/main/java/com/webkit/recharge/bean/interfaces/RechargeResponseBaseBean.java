package com.webkit.recharge.bean.interfaces;

/**
 * Created by HH on 2018/1/22.
 */
public class RechargeResponseBaseBean {
    private Long id;
    private String name;
    private String standardPrice;
    private String price;
    private String validTime;
    private String discount;
    private String packageTypeStr;
    private String packageType;
    private String businessType;

    public RechargeResponseBaseBean() {
    }

    public RechargeResponseBaseBean(Long id, String name, String standardPrice, String price, String validTime, String discount, String packageTypeStr, String packageType, String businessType) {
        this.id = id;
        this.name = name;
        this.standardPrice = standardPrice;
        this.price = price;
        this.validTime = validTime;
        this.discount = discount;
        this.packageTypeStr = packageTypeStr;
        this.packageType = packageType;
        this.businessType = businessType;

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPackageTypeStr() {
        return packageTypeStr;
    }

    public void setPackageTypeStr(String packageTypeStr) {
        this.packageTypeStr = packageTypeStr;
    }

    public String getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(String standardPrice) {
        this.standardPrice = standardPrice;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
}
