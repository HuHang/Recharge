package com.webkit.recharge.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.webkit.recharge.bean.enums.BusinessType;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created by HH on 2018/1/22.
 */
public class Discount {
    private Long id;
    private String businessTypeStr;
    private Double standardDiscount;
    private Integer businessType;
    private Date gmtCreate;
    private Date gmtModified;

    @Override
    public String toString(){
        return "Discount{" +
                "id=" + id +
                ", businessTypeStr='" + businessTypeStr + '\'' +
                ", standardDiscount='" + standardDiscount + '\'' +
                ", businessType='" + businessType + '\'' +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", gmtModified='" + gmtModified + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessTypeStr() {
        return StringUtils.equals(businessTypeStr, null) ? BusinessType.getDescription(getBusinessType()) : businessTypeStr;
    }

    public void setBusinessTypeStr(String businessTypeStr) {
        this.businessTypeStr = businessTypeStr;
    }

    public Double getStandardDiscount() {
        return standardDiscount;
    }

    public void setStandardDiscount(Double standardDiscount) {
        this.standardDiscount = standardDiscount;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
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
}
