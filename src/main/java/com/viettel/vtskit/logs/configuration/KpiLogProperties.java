package com.viettel.vtskit.logs.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "logs.kpi-logs")
@Data
public class KpiLogProperties {

    @Value("${kpi-log-file-name:}")
    private String kpiLogFileName;

    @NestedConfigurationProperty
    private KpiDatasourceProperties datasource;

}
