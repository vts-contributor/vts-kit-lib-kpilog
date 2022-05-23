package com.viettel.vtskit.logs.handler;

import com.viettel.vtskit.logs.enums.KpiLogAttrKeys;
import com.viettel.vtskit.logs.utils.CommonUtils;
import com.viettel.vtskit.logs.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;

@ControllerAdvice
public class RequestBodyCapture extends RequestBodyAdviceAdapter {

    private HttpServletRequest httpServletRequest;

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
                                Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        httpServletRequest.setAttribute(KpiLogAttrKeys.REQUEST_CONTENT, StringUtils.cvtObjToJsonString(body));
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }

    @Autowired
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
}
