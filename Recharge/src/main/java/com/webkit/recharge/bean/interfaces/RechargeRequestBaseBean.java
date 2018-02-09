package com.webkit.recharge.bean.interfaces;

/**
 * Created by HH on 2018/1/24.
 */
public class RechargeRequestBaseBean {
    private Long packageId;
    private Integer businessType;
    private Integer operatorId;
    private Integer qCellCoreCode;
    private String mobileTel;

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

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getqCellCoreCode() {
        return qCellCoreCode;
    }

    public void setqCellCoreCode(Integer qCellCoreCode) {
        this.qCellCoreCode = qCellCoreCode;
    }
}
