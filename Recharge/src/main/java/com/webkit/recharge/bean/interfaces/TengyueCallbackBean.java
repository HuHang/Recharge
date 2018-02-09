package com.webkit.recharge.bean.interfaces;

/**
 * Created by HH on 2018/1/26.
 */
public class TengyueCallbackBean {
    private String userId;//合作方用户编号
    private String bizId;//业务编号
    private String ejId;//充值平台方订单号
    private String downstreamSerialno;//合作方商户系统的流水号
    private String status;//状态 2 是成功 3 是失败
    private String sign;//

    public TengyueCallbackBean() {
    }

    public TengyueCallbackBean(String userId, String bizId, String ejId, String downstreamSerialno, String status, String sign) {
        this.userId = userId;
        this.bizId = bizId;
        this.ejId = ejId;
        this.downstreamSerialno = downstreamSerialno;
        this.status = status;
        this.sign = sign;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getEjId() {
        return ejId;
    }

    public void setEjId(String ejId) {
        this.ejId = ejId;
    }

    public String getDownstreamSerialno() {
        return downstreamSerialno;
    }

    public void setDownstreamSerialno(String downstreamSerialno) {
        this.downstreamSerialno = downstreamSerialno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
