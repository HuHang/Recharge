package com.webkit.recharge.bean.interfaces;

/**
 * Created by HH on 2018/1/30.
 */
public class ICBCCallBackBean {
    private String merVAR;
    private NotifyData notifyData = new NotifyData();
    private String signMsg;

    public String getMerVAR() {
        return merVAR;
    }

    public void setMerVAR(String merVAR) {
        this.merVAR = merVAR;
    }

    public NotifyData getNotifyData() {
        return notifyData;
    }

    public void setNotifyData(NotifyData notifyData) {
        this.notifyData = notifyData;
    }

    public String getSignMsg() {
        return signMsg;
    }

    public void setSignMsg(String signMsg) {
        this.signMsg = signMsg;
    }

    public class NotifyData{
        private String interfaceName;
        private String interfaceVersion;
        private String orderDate;
        private String orderid;
        private String amount;
        private String merID;
        private String merAcct;
        private String curType;
        private String JoinFlag;
        private String UserNum;
        private String TranSerialNo;
        private String notifyDate;
        private String tranStat;//1-“交易成功，已清算”；2-“交易失败”；3-“交易可疑”
        private String comment;//错误描述

        public String getInterfaceName() {
            return interfaceName;
        }

        public void setInterfaceName(String interfaceName) {
            this.interfaceName = interfaceName;
        }

        public String getInterfaceVersion() {
            return interfaceVersion;
        }

        public void setInterfaceVersion(String interfaceVersion) {
            this.interfaceVersion = interfaceVersion;
        }

        public String getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(String orderDate) {
            this.orderDate = orderDate;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getMerID() {
            return merID;
        }

        public void setMerID(String merID) {
            this.merID = merID;
        }

        public String getMerAcct() {
            return merAcct;
        }

        public void setMerAcct(String merAcct) {
            this.merAcct = merAcct;
        }

        public String getCurType() {
            return curType;
        }

        public void setCurType(String curType) {
            this.curType = curType;
        }

        public String getJoinFlag() {
            return JoinFlag;
        }

        public void setJoinFlag(String joinFlag) {
            JoinFlag = joinFlag;
        }

        public String getUserNum() {
            return UserNum;
        }

        public void setUserNum(String userNum) {
            UserNum = userNum;
        }

        public String getTranSerialNo() {
            return TranSerialNo;
        }

        public void setTranSerialNo(String tranSerialNo) {
            TranSerialNo = tranSerialNo;
        }

        public String getNotifyDate() {
            return notifyDate;
        }

        public void setNotifyDate(String notifyDate) {
            this.notifyDate = notifyDate;
        }

        public String getTranStat() {
            return tranStat;
        }

        public void setTranStat(String tranStat) {
            this.tranStat = tranStat;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }
}
