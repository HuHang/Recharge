package com.webkit.recharge.bean;

/**
 * Created by HH on 2018/1/22.
 */
public class Dataflow extends BusinessBaseBean{
    private Double mobilePrice;//移动价格
    private Double telecomPrice;//电信价格
    private Double unicomPrice;//联通价格

    private Integer packageSize;//包大小

    public Double getMobilePrice() {
        return mobilePrice;
    }

    public void setMobilePrice(Double mobilePrice) {
        this.mobilePrice = mobilePrice;
    }

    public Double getTelecomPrice() {
        return telecomPrice;
    }

    public void setTelecomPrice(Double telecomPrice) {
        this.telecomPrice = telecomPrice;
    }

    public Double getUnicomPrice() {
        return unicomPrice;
    }

    public void setUnicomPrice(Double unicomPrice) {
        this.unicomPrice = unicomPrice;
    }

    public Integer getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(Integer packageSize) {
        this.packageSize = packageSize;
    }
}
