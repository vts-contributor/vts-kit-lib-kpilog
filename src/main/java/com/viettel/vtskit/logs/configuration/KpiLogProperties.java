package com.viettel.vtskit.logs.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties(prefix = "logs.kpi-logs")
public class KpiLogProperties {

    @Value("${application-code:}")
    private String applicationCode;

    @Value("${service-code:}")
    private String serviceCode;

    @Value("${allow-url-patterns:/**}")
    private String allowUrlPatterns;

    @Value("${kpi-log-file-name:}")
    private String kpiLogFileName;

    @NestedConfigurationProperty
    private KpiDatasourceProperties datasource;

    public KpiLogProperties(){
        this.datasource = new KpiDatasourceProperties();
    }

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
        String CachePrepStmts = datasource.getCachePrepStmts() != null ? datasource.getCachePrepStmts() : "true";
        this.datasource.setCachePrepStmts(CachePrepStmts);
        String PreStmtCacheSize = datasource.getPrepStmtCacheSize() != null ? datasource.getCachePrepStmts() : "250";
        this.datasource.setPrepStmtCacheSize(PreStmtCacheSize);
        String PrepStmtCacheSqlLimit = datasource.getPrepStmtCacheSqlLimit() != null ? datasource.getCachePrepStmts() : "2048";
        this.datasource.setPrepStmtCacheSqlLimit(PrepStmtCacheSqlLimit);
        int MaximumPoolSize = datasource.getMaximumPoolSize() != 0 ? datasource.getMaximumPoolSize() :100;
        this.datasource.setMaximumPoolSize(MaximumPoolSize);
        int MinimumPoolSize = datasource.getMinimumPoolSize() != 0 ? datasource.getMinimumPoolSize() : 10;
        this.datasource.setMinimumPoolSize(MinimumPoolSize);
        String table_name = datasource.getTable_name() != null ? datasource.getTable_name() : "KPI_LOG";
        this.datasource.setTable_name(table_name);
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

    public String getAllowUrlPatterns() {
        return allowUrlPatterns;
    }

    public void setAllowUrlPatterns(String allowUrlPatterns) {
        this.allowUrlPatterns = allowUrlPatterns;
    }
}
