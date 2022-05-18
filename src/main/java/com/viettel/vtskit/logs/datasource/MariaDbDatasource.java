package com.viettel.vtskit.logs.datasource;

import com.viettel.vtskit.logs.configuration.KpiDatasourceProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MariaDbDatasource {
    private HikariConfig config = new HikariConfig();
    private HikariDataSource ds;

    private static MariaDbDatasource instance;

    public static MariaDbDatasource getInstance(KpiDatasourceProperties properties) {
        if (instance == null) {
            synchronized (MariaDbDatasource.class) {
                if (instance == null) {
                    instance = new MariaDbDatasource(properties);
                }
            }
        }
        return instance;
    }

    private MariaDbDatasource(KpiDatasourceProperties properties) {
        config.setJdbcUrl(properties.getUrl());
        config.setUsername(properties.getUsername());
        config.setPassword(properties.getPassword());
        config.setMaximumPoolSize(5);
        config.setPoolName("com.viettel.vtskit.logs");
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
