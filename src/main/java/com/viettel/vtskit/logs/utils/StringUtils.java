package com.viettel.vtskit.logs.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class StringUtils {
    private static final Gson GSON = new Gson();

    private StringUtils(){}

    public static boolean isNullOrEmpty(String input){
        return input == null || input.trim().length() == 0;
    }

    public static String cvtObjToJsonString(Object object){
        return GSON.toJson(object);
    }

    public static String cvtMapToJsonString(Map map){
        Type type = new TypeToken<HashMap>(){}.getType();
        return GSON.toJson(map, type);
    }

    public static String safeString(String input){
        if(isNullOrEmpty(input)){
            return "";
        }
        return input;
    }
}
