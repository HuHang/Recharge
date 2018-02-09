package com.webkit.recharge.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.webkit.recharge.bean.enums.BusinessType;
import com.webkit.recharge.bean.enums.PackageType;
import com.webkit.recharge.bean.enums.Provices;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created by HH on 2018/1/23.
 */
public class BusinessBaseBean {
    private long id;

    private String packageName;//包名
    private Integer packageType;//包类型
    private String packageTypeStr;//包类型名称

    private Integer qCellCoreCode;//归属地编码
    private String qCellCore;//归属地

    private Boolean isMobile;//移动
    private Boolean isTelecom;//电信
    private Boolean isUnicom;//联通

    private Integer businessType;//业务类型
    private String businessTypeStr;//业务名

    private String validTime;//有效期

    private Double discount;//

    private String itemId;//商品编号
    private String itemName;//商品名称

    private Date gmtCreate;//创建时间
    private Date gmtModified;//更新时间


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getPackageType() {
        return packageType;
    }

    public void setPackageType(Integer packageType) {
        this.packageType = packageType;
    }

    public String getPackageTypeStr() {
        return StringUtils.isBlank(packageTypeStr) ? PackageType.getDescription(getPackageType()) : packageTypeStr;
    }

    public void setPackageTypeStr(String packageTypeStr) {
        this.packageTypeStr = packageTypeStr;
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

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }


    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
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

    public Boolean getIsMobile() {
        return isMobile;
    }

    public void setIsMobile(Boolean mobile) {
        this.isMobile = mobile;
    }

    public Boolean getIsTelecom() {
        return isTelecom;
    }

    public void setIsTelecom(Boolean telecom) {
        this.isTelecom = telecom;
    }

    public Boolean getIsUnicom() {
        return isUnicom;
    }

    public void setIsUnicom(Boolean unicom) {
        this.isUnicom = unicom;
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
}
