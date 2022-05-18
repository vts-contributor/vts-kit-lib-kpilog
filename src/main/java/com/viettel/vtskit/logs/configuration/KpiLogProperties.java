package com.viettel.vtskit.logs.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties(prefix = "vtskit.logs.kpi-logs")
public class KpiLogProperties {

    @Value("${application-code:}")
    private String applicationCode;

    @Value("${service-code:}")
    private String serviceCode;

    @Value("${kpi-log-file-name:}")
    private String kpiLogFileName;

    @NestedConfigurationProperty
    private KpiDatasourceProperties datasource;

    public String getKpiLogFileName() {
        return kpiLogFileName;
    }

    public void setKpiLogFileName(String kpiLogFileName) {
        this.kpiLogFileName = kpiLogFileName;
    }

    public KpiDatasourceProperties getDatasource() {
        return datasource;
    }

    public void setDatasource(KpiDatasourceProperties datasource) {
        this.datasource = datasource;
    }

    public String getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
}
