package com.viettel.vtskit.logs.utils;

import org.slf4j.MDC;

public class CommonUtils {

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
}
