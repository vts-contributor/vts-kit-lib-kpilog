package com.viettel.vtskit.logs;

import com.viettel.vtskit.logs.configuration.KpiLogProperties;
import com.viettel.vtskit.logs.enums.TransactionStatus;
import com.viettel.vtskit.logs.utils.CommonUtils;
import com.viettel.vtskit.logs.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class AppLog {
    private static final String IP_PORT_SERVICE = CommonUtils.getIpAddressAndPort_q();
    private static final Marker ERROR_LOG_MARKER = MarkerFactory.getMarker("ERROR_LOG");
    private static Logger logger = LoggerFactory.getLogger(AppLog.class);

    private static String APP_CODE;
    private static String SERVICE_CODE;

    public static void setAppLogInfo(String appCode, String serviceCode) {
        APP_CODE = appCode;
        SERVICE_CODE = serviceCode;
    }

    public static void info(String message) {
        CommonUtils.addCodeLineNumber();
        logger.info(MarkerFactory.getMarker("INFO_LOG"), message);
    }

    public static void info(String message, Object... arguments) {
        CommonUtils.addCodeLineNumber();
        logger.info(message, arguments);
    }

    public static void error(String message, Object... arguments) {
        CommonUtils.addCodeLineNumber();
        logger.error(ERROR_LOG_MARKER, message, arguments);
    }

    public static void error(Throwable e) {
        CommonUtils.addCodeLineNumber();
        logException(e);
    }

    public static void error(Throwable e, HttpServletRequest request) {
        CommonUtils.addCodeLineNumber();
        logException(e, request);
    }

    public static void debug(String message, Object... arguments) {
        CommonUtils.addCodeLineNumber();
        logger.debug(message, arguments);
    }

    public static void trace(String message, Object... arguments) {
        CommonUtils.addCodeLineNumber();
        logger.trace(message, arguments);
    }

    public static void warn(String message, Object... arguments) {
        CommonUtils.addCodeLineNumber();
        logger.warn(message, arguments);
    }

    private static void logException(Throwable e) {
        StringBuilder sb = new StringBuilder();
        sb.append(APP_CODE).append("||");
        sb.append(SERVICE_CODE).append("||");
        sb.append("N/A").append("||");
        sb.append(IP_PORT_SERVICE).append("||");//IpPortParentNode
        sb.append("N/A").append("||");//ipPortCurrentNode
        sb.append("N/A").append("||");//RequestContent
        sb.append("N/A").append("||");//ResponseContent
        sb.append(DateUtils.formatDate(new Date())).append("||");//start time
        sb.append(DateUtils.formatDate(new Date())).append("||");//end time
        sb.append("N/A").append("||");//Duration
        sb.append("N/A").append("||");//Error Code
        sb.append(CommonUtils.getStackTrace(e)).append("||");//ErrorDescription
        sb.append(TransactionStatus.FAILED.getValue()).append("||");//TransactionStatus
        sb.append("N/A").append("||");//ActionName
        sb.append("N/A").append("||");//UserName
        sb.append("N/A");//Account
        logger.error(ERROR_LOG_MARKER, sb.toString());
    }

    private static void logException(Throwable e, HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append(APP_CODE).append("||");
        sb.append(SERVICE_CODE).append("||");
        sb.append("N/A").append("||");
        sb.append(IP_PORT_SERVICE).append("||");//IpPortParentNode
        sb.append(CommonUtils.fetchClientIpAddr(request)).append("||");//ipPortCurrentNode
        sb.append(getBodyContent(request)).append("||");//RequestContent
        sb.append("N/A").append("||");//ResponseContent
        sb.append(DateUtils.formatDate(new Date())).append("||");//start time
        sb.append(DateUtils.formatDate(new Date())).append("||");//end time
        sb.append("N/A").append("||");//Duration
        sb.append("N/A").append("||");//Error Code
        sb.append(CommonUtils.getStackTrace(e)).append("||");//ErrorDescription
        sb.append(TransactionStatus.FAILED.getValue()).append("||");//TransactionStatus
        sb.append("N/A").append("||");//ActionName
        sb.append("N/A").append("||");//UserName
        sb.append("N/A");//Account
        logger.error(ERROR_LOG_MARKER, sb.toString());
    }

    private static String getBodyContent(HttpServletRequest request) {
        try {
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                return request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            } else {
                StringBuilder requestUrl = new StringBuilder("[" + request.getMethod() + "]:" + request.getRequestURI());
                String queryString = request.getQueryString();
                if (queryString != null) {
                    requestUrl.append("?").append(queryString);
                }
                return requestUrl.toString();
            }
        } catch (Exception e) {
            logException(e);
        }
        return "";
    }

}
