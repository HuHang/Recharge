package com.webkit.recharge.bean.interfaces;

/**
 * Created by HH on 2018/1/24.
 */
public class TaobaoMobileSegmentResponseBean {

    private String province;
    private String catName;
    private String telString;
    private String carrier;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getTelString() {
        return telString;
    }

    public void setTelString(String telString) {
        this.telString = telString;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

}
