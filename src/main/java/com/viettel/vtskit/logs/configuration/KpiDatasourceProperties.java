package com.viettel.vtskit.logs.configuration;

import com.viettel.vtskit.logs.utils.StringUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "logs.kpi-logs.datasource")
public class KpiDatasourceProperties {
    @Value("${enabled:false}")
    private boolean enabled;

    @Value("${url:}")
    private String url;

    @Value("${username:}")
    private String username;

    @Value("${password:}")
    private String password;

    @Value("${driver-class-name}")
    private String driver_class_name;

    @Value("{table-name:kpi_log}")
    private String tableName;

    @Value("${cachePrepStmts:true}")
    private String cachePrepStmts;

    @Value("${prepStmtCacheSize:250}")
    private String prepStmtCacheSize;

    @Value("${prepStmtCacheSqlLimit:2048}")
    private String prepStmtCacheSqlLimit;

    @Value("${maximumPoolSize:100}")
    private Integer maximumPoolSize;

    @Value("${minimumPoolSize:10}")
    private Integer minimumPoolSize;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isUseDB() {
        return !StringUtils.isNullOrEmpty(url);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver_class_name() {
        return driver_class_name;
    }

    public void setDriver_class_name(String driver_class_name) {
        this.driver_class_name = driver_class_name;
    }

    public String getTableName() {
        return tableName == null ? "kpi_log" : tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCachePrepStmts() {
        return cachePrepStmts == null ? "true" : cachePrepStmts;
    }

    public void setCachePrepStmts(String cachePrepStmts) {
        this.cachePrepStmts = cachePrepStmts;
    }

    public String getPrepStmtCacheSize() {
        return prepStmtCacheSize == null ? "250" : prepStmtCacheSize;
    }

    public void setPrepStmtCacheSize(String prepStmtCacheSize) {
        this.prepStmtCacheSize = prepStmtCacheSize;
    }

    public String getPrepStmtCacheSqlLimit() {
        return prepStmtCacheSqlLimit == null ? "2048" : prepStmtCacheSqlLimit;
    }

    public void setPrepStmtCacheSqlLimit(String prepStmtCacheSqlLimit) {
        this.prepStmtCacheSqlLimit = prepStmtCacheSqlLimit;
    }

    public Integer getMaximumPoolSize() {
        return maximumPoolSize == null ? 100 : maximumPoolSize;
    }

    public void setMaximumPoolSize(Integer maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public Integer getMinimumPoolSize() {
        return minimumPoolSize == null ? 10 : minimumPoolSize;
    }

    public void setMinimumPoolSize(Integer minimumPoolSize) {
        this.minimumPoolSize = minimumPoolSize;
    }
}
