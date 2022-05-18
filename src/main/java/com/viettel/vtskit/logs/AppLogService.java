package com.viettel.vtskit.logs;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class AppLogService {
    private static final Marker ERROR_LOG_MARKER = MarkerFactory.getMarker("ERROR_LOG");

    private AppLogService(){
        // Disable New Instance
    }

    public static void info(Logger logger, String message){
        if(logger == null){
            return;
        }
        logger.info(message);
    }

    public static void info(Logger logger, String message, Object... arguments){
        if(logger == null){
            return;
        }
        logger.info(message, arguments);
    }

    public static void error(Logger logger, String message, Object... arguments){
        if(logger == null){
            return;
        }
        logger.error(ERROR_LOG_MARKER, message, arguments);
    }

    public static void error(Logger logger, Throwable throwable){
        if(logger == null){
            return;
        }
        logger.error(ERROR_LOG_MARKER, throwable.getMessage(), throwable);
    }

    public static void debug(Logger logger, String message, Object... arguments){
        if(logger == null){
            return;
        }
        logger.debug(message, arguments);
    }

    public static void trace(Logger logger, String message, Object... arguments){
        if(logger == null){
            return;
        }
        logger.trace(message, arguments);
    }

    public static void warn(Logger logger, String message, Object... arguments){
        if(logger == null){
            return;
        }
        logger.warn(message, arguments);
    }
}
