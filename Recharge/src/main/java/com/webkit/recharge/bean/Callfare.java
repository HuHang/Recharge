package com.webkit.recharge.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by HH on 2018/1/22.
 */
public class Callfare extends BusinessBaseBean{

    private Double standardPrice;//标价

    public Double getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(Double standardPrice) {
        this.standardPrice = standardPrice;
    }
}
