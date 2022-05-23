package com.viettel.vtskit.logs.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.viettel.vtskit.logs.AppLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class StringUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtils.class);
    private static final Gson GSON = new Gson();

    private StringUtils(){}

    public static boolean isNullOrEmpty(String input){
        return input == null || input.trim().length() == 0;
    }

    public static String cvtObjToJsonString(Object object){
        try {
            return GSON.toJson(object);
        }catch (Exception ex){
            AppLogService.error(LOGGER, ex);
            return "";
        }
    }

    public static String cvtMapToJsonString(Map map){
        try {
            Type type = new TypeToken<HashMap>(){}.getType();
            return GSON.toJson(map, type);
        }catch (Exception ex){
            AppLogService.error(LOGGER, ex);
            return "";
        }
    }

    public static String safeString(String input){
        if(isNullOrEmpty(input)){
            return "";
        }
        return input;
    }
}
