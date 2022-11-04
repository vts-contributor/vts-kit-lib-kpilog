package com.viettel.vtskit.logs.configuration;

import com.viettel.vtskit.logs.AppLogService;
import com.viettel.vtskit.logs.KpiLogService;
import com.viettel.vtskit.logs.handler.KpiLogInterceptor;
import com.viettel.vtskit.logs.handler.RequestBodyCapture;
import com.viettel.vtskit.logs.handler.ResponseBodyCapture;
import com.viettel.vtskit.logs.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableConfigurationProperties({AppLogProperties.class, KpiLogProperties.class, LogProperties.class})
@Import({RequestBodyCapture.class, ResponseBodyCapture.class})
public class LogAutoConfiguration implements WebMvcConfigurer {

    private KpiLogProperties kpiLogProperties;

    @Bean
    public KpiLogService kpiLogService(){
        return new KpiLogService();
    }

    @Bean
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(5);
        executor.setThreadNamePrefix("vts-kit-logs-handler-");
        executor.initialize();
        return executor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // KpiLogInterceptor
        String allowUrlPatterns = kpiLogProperties.getAllowUrlPatterns();
        if(StringUtils.isNullOrEmpty(allowUrlPatterns) || "ignore".equals(allowUrlPatterns)){
            return;
        }
        String[] patterns = kpiLogProperties.getAllowUrlPatterns().split(",");
        InterceptorRegistration registration = registry.addInterceptor(new KpiLogInterceptor(kpiLogService()));
        for(String pattern: patterns){
            registration.addPathPatterns(pattern);
        }
    }

    @Autowired
    public void setKpiLogProperties(KpiLogProperties kpiLogProperties) {
        this.kpiLogProperties = kpiLogProperties;
    }
}
