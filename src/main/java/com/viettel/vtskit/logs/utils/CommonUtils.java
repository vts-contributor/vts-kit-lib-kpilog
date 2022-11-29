package com.viettel.vtskit.logs.utils;

import com.viettel.vtskit.logs.AppLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.MediaType;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class CommonUtils {

    private CommonUtils(){}

    /**
     * Lay thu tu dong code vi tri dang goi ghi log.
     * Xu ly truong hop log4j lay khong dung line number neu goi qua lop wrapper @{@link com.viettel.vtskit.logs.AppLog} va @{@link com.viettel.vtskit.logs.KpiLogService}
     * @return
     */
    public static void addCodeLineNumber(){
        String lineNumber;
        StackTraceElement[] stackTraces = Thread.currentThread().getStackTrace();
        if(stackTraces == null || stackTraces.length < 4){
            lineNumber = "";
        }else{
            lineNumber = String.valueOf(stackTraces[3].getLineNumber());
        }
        MDC.put("line", lineNumber);
    }

    public static String getServerIp(){
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            if(inetAddress != null){
                return String.valueOf(inetAddress);
            }
        } catch (UnknownHostException e) {
            AppLog.error( e);
        }
        return "";
    }

    public static boolean isJsonContentType(HttpServletRequest request){
        String contentType = request.getContentType();
        if(StringUtils.isNullOrEmpty(contentType)){
            return false;
        }
        return contentType.toLowerCase().contains(MediaType.APPLICATION_JSON_VALUE.toLowerCase());
    }

    public static boolean isTextType(MediaType mediaType){
        List<MediaType> listOfTextType = Arrays.asList(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN);
        return listOfTextType.contains(mediaType);
    }

    public static String getStringRequestAttr(HttpServletRequest request, String key){
        return (String) request.getAttribute(key);
    }

    public static Date getDateRequestAttr(HttpServletRequest request, String key){
        return (Date) request.getAttribute(key);
    }

    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

    /**
     * lay dia chi server
     *
     * @return
     */
    public static String getIpAddressAndPort_q() {
        String strIpServer = "";
        try {
            MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();

            Set<ObjectName> objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"),
                    Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));

            strIpServer = InetAddress.getLocalHost().getHostAddress();
            if (objectNames != null && objectNames.size() > 0) {
                String port = objectNames.iterator().next().getKeyProperty("port");
                strIpServer += ":" + port;
            }
        } catch (MalformedObjectNameException | UnknownHostException ex) {
            AppLog.error(ex);
        }
        return strIpServer;
    }

    /**
     * fetchClientIpAddr
     * @param request
     * @return
     */
    public static String fetchClientIpAddr(HttpServletRequest request) {
        String ip = Optional.ofNullable(request.getHeader("X-FORWARDED-FOR")).orElse(request.getRemoteAddr());
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }
        ip = ip.replace(",", "-");
        return ip;
    }

}
