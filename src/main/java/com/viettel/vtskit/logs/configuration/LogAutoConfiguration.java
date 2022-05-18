package com.viettel.vtskit.logs.configuration;

import com.viettel.vtskit.logs.KpiLogService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({AppLogProperties.class, KpiLogProperties.class, LogProperties.class})
public class LogAutoConfiguration {
    @Bean
    public KpiLogService kpiLogService(){
        return new KpiLogService();
    }
}
