package com.viettel.vtskit.logs;

import com.viettel.vtskit.logs.configuration.LogProperties;
import com.viettel.vtskit.logs.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

public class AppLogService {
    private static final Marker ERROR_LOG_MARKER = MarkerFactory.getMarker("ERROR_LOG");

    private AppLogService(){
        // Disable New Instance
    }
    public static void info(@NonNull Logger logger, String message) {
            CommonUtils.addCodeLineNumber();
            logger.info(message);
    }

    public static void info(@NonNull Logger logger, String message, Object... arguments){
            CommonUtils.addCodeLineNumber();
            logger.info(message, arguments);
    }

    public static void error(@NonNull Logger logger, String message, Object... arguments){
        CommonUtils.addCodeLineNumber();
        logger.error(ERROR_LOG_MARKER, message, arguments);
    }

    public static void error(@NonNull Logger logger, Throwable throwable){
        CommonUtils.addCodeLineNumber();
        logger.error(ERROR_LOG_MARKER, throwable.getMessage(), throwable);
    }

    public static void debug(@NonNull Logger logger, String message, Object... arguments){
        CommonUtils.addCodeLineNumber();
        logger.debug(message, arguments);
    }

    public static void trace(@NonNull Logger logger, String message, Object... arguments){
        CommonUtils.addCodeLineNumber();
        logger.trace(message, arguments);
    }

    public static void warn(@NonNull Logger logger, String message, Object... arguments){
        CommonUtils.addCodeLineNumber();
        logger.warn(message, arguments);
    }
}
