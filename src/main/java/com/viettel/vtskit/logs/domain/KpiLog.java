package com.viettel.vtskit.logs.domain;

import com.viettel.vtskit.logs.enums.TransactionStatus;
import com.viettel.vtskit.logs.utils.DateUtils;
import com.viettel.vtskit.logs.utils.StringUtils;

import java.sql.Date;

public class KpiLog {
    private String applicationCode;
    private String serviceCode;
    private String sessionId;
    private String ipPortParentNode;
    private String ipPortCurrentNode;
    private String requestContent;
    private String responseContent;
    private Date startTime;
    private Date endTime;
    private Long duration;
    private String errorCode;
    private String errorDescription;
    private Integer transactionStatus;
    private String actionName;
    private String username;
    private String account;

    public KpiLog(String applicationCode, String serviceCode, String sessionId, String ipPortParentNode, String ipPortCurrentNode, String requestContent, String responseContent, Date startTime, Date endTime, String errorCode, String errorDescription, Integer transactionStatus, String actionName, String username, String account) {
        this.applicationCode = applicationCode;
        this.serviceCode = serviceCode;
        this.sessionId = sessionId;
        this.ipPortParentNode = ipPortParentNode;
        this.ipPortCurrentNode = ipPortCurrentNode;
        this.requestContent = requestContent;
        this.responseContent = responseContent;
        this.startTime = startTime;
        this.endTime = endTime;
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.transactionStatus = transactionStatus;
        this.actionName = actionName;
        this.username = username;
        this.account = account;
        if (this.startTime != null && this.endTime != null) {
            duration = endTime.getTime() - startTime.getTime();
        }
    }


    public String getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getIpPortParentNode() {
        return ipPortParentNode;
    }

    public void setIpPortParentNode(String ipPortParentNode) {
        this.ipPortParentNode = ipPortParentNode;
    }

    public String getIpPortCurrentNode() {
        return ipPortCurrentNode;
    }

    public void setIpPortCurrentNode(String ipPortCurrentNode) {
        this.ipPortCurrentNode = ipPortCurrentNode;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public Integer getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(Integer transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.safeString(applicationCode)).append("|");
        sb.append(StringUtils.safeString(serviceCode)).append("|");
        sb.append(StringUtils.safeString(sessionId)).append("|");
        sb.append(StringUtils.safeString(ipPortParentNode)).append("|");
        sb.append(StringUtils.safeString(ipPortCurrentNode)).append("|");
        sb.append(StringUtils.safeString(requestContent)).append("|");
        sb.append(StringUtils.safeString(responseContent)).append("|");
        sb.append(DateUtils.formatDate(startTime)).append("|");
        sb.append(DateUtils.formatDate(endTime)).append("|");
        sb.append(duration == null ? "" : duration).append("|");
        sb.append(StringUtils.safeString(errorCode)).append("|");
        sb.append(StringUtils.safeString(errorDescription)).append("|");
        sb.append(transactionStatus == null ? "" : transactionStatus).append("|");
        sb.append(StringUtils.safeString(actionName)).append("|");
        sb.append(StringUtils.safeString(username)).append("|");
        sb.append(StringUtils.safeString(account)).append("|");
        return sb.toString();
    }

    public static class Builder {
        private String applicationCode;
        private String serviceCode;
        private String sessionId;
        private String ipPortParentNode;
        private String ipPortCurrentNode;
        private String requestContent;
        private String responseContent;
        private java.util.Date startTime;
        private java.util.Date endTime;
        private String errorCode;
        private String errorDescription;
        private TransactionStatus transactionStatus;
        private String actionName;
        private String username;
        private String account;

        public KpiLog.Builder applicationCode(String applicationCode) {
            this.applicationCode = applicationCode;
            return this;
        }

        public KpiLog.Builder serviceCode(String serviceCode) {
            this.serviceCode = serviceCode;
            return this;
        }

        public KpiLog.Builder sessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public KpiLog.Builder ipPortParentNode(String ipPortParentNode) {
            this.ipPortParentNode = ipPortParentNode;
            return this;
        }

        public KpiLog.Builder ipPortCurrentNode(String ipPortCurrentNode) {
            this.ipPortCurrentNode = ipPortCurrentNode;
            return this;
        }

        public KpiLog.Builder requestContent(String requestContent) {
            this.requestContent = requestContent;
            return this;
        }

        public KpiLog.Builder responseContent(String responseContent) {
            this.responseContent = responseContent;
            return this;
        }

        public KpiLog.Builder startTime(java.util.Date startTime) {
            this.startTime = startTime;
            return this;
        }

        public KpiLog.Builder endTime(java.util.Date endTime) {
            this.endTime = endTime;
            return this;
        }

        public KpiLog.Builder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public KpiLog.Builder errorDescription(String errorDescription) {
            this.errorDescription = errorDescription;
            return this;
        }

        public KpiLog.Builder transactionStatus(TransactionStatus transactionStatus) {
            this.transactionStatus = transactionStatus;
            return this;
        }

        public KpiLog.Builder actionName(String actionName) {
            this.actionName = actionName;
            return this;
        }

        public KpiLog.Builder username(String username) {
            this.username = username;
            return this;
        }

        public KpiLog.Builder account(String account) {
            this.account = account;
            return this;
        }

        public KpiLog build() {
            Date startTimeVal = null;
            Date endTimeVal = null;
            Integer transactionStatusVal = null;
            if (transactionStatus != null) {
                transactionStatusVal = this.transactionStatus.getValue();
            }
            if(startTime != null){
                startTimeVal = new Date(startTime.getTime());
            }
            if(endTime != null){
                endTimeVal = new Date(endTime.getTime());
            }
            return new KpiLog(applicationCode, serviceCode, sessionId, ipPortParentNode,
                    ipPortCurrentNode, requestContent, responseContent,
                    startTimeVal, endTimeVal, errorCode, errorDescription, transactionStatusVal,
                    actionName, username, account);
        }
    }
}
