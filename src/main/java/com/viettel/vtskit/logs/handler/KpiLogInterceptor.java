package com.viettel.vtskit.logs.handler;

import com.viettel.vtskit.logs.KpiLogService;
import com.viettel.vtskit.logs.domain.KpiLog;
import com.viettel.vtskit.logs.enums.KpiLogAttrKeys;
import com.viettel.vtskit.logs.enums.TransactionStatus;
import com.viettel.vtskit.logs.utils.CommonUtils;
import com.viettel.vtskit.logs.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

public class KpiLogInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(KpiLogInterceptor.class);

    private KpiLogService kpiLogService;

    public KpiLogInterceptor(KpiLogService kpiLogService){
        this.kpiLogService = kpiLogService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(KpiLogAttrKeys.STAR_TIME, new Date());
        Map<String, String[]> paramsReq = request.getParameterMap();
        if(HttpMethod.GET.name().equals(request.getMethod()) && !paramsReq.isEmpty()){
            request.setAttribute(KpiLogAttrKeys.REQUEST_CONTENT, StringUtils.cvtMapToJsonString(paramsReq));
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String actionName = String.format("%s(%s)", request.getRequestURI(), request.getMethod());
        TransactionStatus transactionStatus = TransactionStatus.resolve(CommonUtils.getStringRequestAttr(request, KpiLogAttrKeys.TRANSACTION_STATUS));
        KpiLog.Builder builder = new KpiLog.Builder();
        builder.sessionId(CommonUtils.getStringRequestAttr(request, KpiLogAttrKeys.SESSION_ID));
        builder.ipPortParentNode(CommonUtils.getStringRequestAttr(request, KpiLogAttrKeys.PARENT_NODE));
        builder.ipPortCurrentNode(CommonUtils.getServerIp());
        builder.requestContent(CommonUtils.getStringRequestAttr(request, KpiLogAttrKeys.REQUEST_CONTENT));
        builder.responseContent(CommonUtils.getStringRequestAttr(request, KpiLogAttrKeys.RESPONSE_CONTENT));
        builder.startTime(CommonUtils.getDateRequestAttr(request, KpiLogAttrKeys.STAR_TIME));
        builder.errorCode(CommonUtils.getStringRequestAttr(request, KpiLogAttrKeys.ERROR_CODE));
        builder.errorDescription(CommonUtils.getStringRequestAttr(request, KpiLogAttrKeys.ERROR_DESCRIPTION));
        builder.transactionStatus(transactionStatus);
        builder.username(CommonUtils.getStringRequestAttr(request, KpiLogAttrKeys.USERNAME));
        builder.account(CommonUtils.getStringRequestAttr(request, KpiLogAttrKeys.ACCOUNT));
        builder.actionName(actionName);
        builder.endTime(new Date());
        kpiLogService.writeLog(LOGGER, builder.build());
    }
}
