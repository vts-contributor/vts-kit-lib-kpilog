package com.viettel.vtskit.logs.configuration;

import com.viettel.vtskit.logs.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;

public class KpiDatasourceProperties {

    @Value("${url:}")
    private String url;

    @Value("${username:}")
    private String username;

    @Value("${password:}")
    private String password;

    @Value("${driver-class-name}")
    private  String driver_class_name;

    @Value("{table-name}")
    private  String table_name;
    @Value("${cachePrepStmts}")
    private String cachePrepStmts;

    @Value("${prepStmtCacheSize}")
    private String prepStmtCacheSize;

    @Value("${prepStmtCacheSqlLimit}")
    private String prepStmtCacheSqlLimit;

    @Value("${MaximumPoolSize}")
    private int MaximumPoolSize;

    @Value("${MinimumPoolSize}")
    private int MinimumPoolSize;

    public String getTable_name() {
        return table_name;
    }
    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public int getMaximumPoolSize() {
        return MaximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        MaximumPoolSize = maximumPoolSize;
    }

    public int getMinimumPoolSize() {
        return MinimumPoolSize;
    }

    public void setMinimumPoolSize(int minimumPoolSize) {
        MinimumPoolSize = minimumPoolSize;
    }

    public String getCachePrepStmts() {
        return cachePrepStmts;
    }

    public void setCachePrepStmts(String cachePrepStmts) {
        this.cachePrepStmts = cachePrepStmts;
    }

    public String getPrepStmtCacheSize() {
        return prepStmtCacheSize;
    }

    public void setPrepStmtCacheSize(String prepStmtCacheSize) {
        this.prepStmtCacheSize = prepStmtCacheSize;
    }

    public String getPrepStmtCacheSqlLimit() {
        return prepStmtCacheSqlLimit;
    }

    public void setPrepStmtCacheSqlLimit(String prepStmtCacheSqlLimit) {
        this.prepStmtCacheSqlLimit = prepStmtCacheSqlLimit;
    }
    public String getDriver_class_name() {
        return driver_class_name;
    }

    public void setDriver_class_name(String driver_class_name) {
        this.driver_class_name = driver_class_name;
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

    public boolean isUseMariaDB(){
        return !StringUtils.isNullOrEmpty(url);
    }
}
