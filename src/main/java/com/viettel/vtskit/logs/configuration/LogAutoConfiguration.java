package com.viettel.vtskit.logs.configuration;

import com.viettel.vtskit.logs.KpiLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableConfigurationProperties({ KpiLogProperties.class, AppInfo.class})
public class LogAutoConfiguration implements WebMvcConfigurer {

    private KpiLogProperties kpiLogProperties;

    private AppInfo appInfo;

    @Bean
    public KpiLogService kpiLogService(){
        return new KpiLogService();
    }

    @Bean
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(500);
        executor.setMaxPoolSize(500);
        executor.setThreadNamePrefix("vts-kit-lib-kpilog-");
        executor.initialize();
        return executor;
    }

    @Autowired
    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    @Autowired
    public void setKpiLogProperties(KpiLogProperties kpiLogProperties) {
        this.kpiLogProperties = kpiLogProperties;
    }
}
