package com.viettel.vtskit.logs.utils;

import com.viettel.vtskit.logs.AppLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CommonUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);

    private CommonUtils(){}

    /**
     * Lay thu tu dong code vi tri dang goi ghi log.
     * Xu ly truong hop log4j lay khong dung line number neu goi qua lop wrapper @{@link com.viettel.vtskit.logs.AppLogService} va @{@link com.viettel.vtskit.logs.KpiLogService}
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
            AppLogService.error(LOGGER, e);
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
}
