package com.webkit.recharge.bean.interfaces;

/**
 * Created by HH on 2018/1/24.
 */
public class TengyueRequestBaseBean {

    private String userId;
    private String itemId;
    private String uid;
    private String serialno;
    private String dtCreate;
    private String sign;

    public TengyueRequestBaseBean() {
    }

    public TengyueRequestBaseBean(String userId, String serialno, String sign) {
        this.userId = userId;
        this.serialno = serialno;
        this.sign = sign;
    }

    public TengyueRequestBaseBean(String userId, String itemId, String uid, String serialno, String dtCreate, String sign) {
        this.userId = userId;
        this.itemId = itemId;
        this.uid = uid;
        this.serialno = serialno;
        this.dtCreate = dtCreate;
        this.sign = sign;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(String dtCreate) {
        this.dtCreate = dtCreate;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
