package com.viettel.vtskit.logs.datasource;

import com.viettel.vtskit.logs.configuration.KpiDatasourceProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

public class MariaDbDatasource {
    private HikariConfig config = new HikariConfig();
    private HikariDataSource ds;

    private static AtomicReference<MariaDbDatasource> instance = new AtomicReference<>();

    public static MariaDbDatasource getInstance(KpiDatasourceProperties properties) {
        if (instance.get() == null) {
            synchronized (MariaDbDatasource.class) {
                if (instance.get() == null) {
                    instance.set(new MariaDbDatasource(properties));
                }
            }
        }
        return instance.get();
    }

    private MariaDbDatasource(KpiDatasourceProperties properties) {
        config.setJdbcUrl(properties.getUrl());
        config.setUsername(properties.getUsername());
        config.setPassword(properties.getPassword());
        config.setMaximumPoolSize(properties.getMaximumPoolSize());
        config.setPoolName("com.viettel.vtskit.logs");
        config.setDriverClassName(properties.getDriver_class_name());
        config.addDataSourceProperty( "cachePrepStmts" , properties.getCachePrepStmts() );
        config.addDataSourceProperty( "prepStmtCacheSize" , properties.getPrepStmtCacheSize() );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , properties.getPrepStmtCacheSqlLimit() );
        ds = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
