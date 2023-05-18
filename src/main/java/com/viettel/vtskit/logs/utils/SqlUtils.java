package com.viettel.vtskit.logs.utils;

import com.viettel.vtskit.logs.callback.QueryCallback;
import com.viettel.vtskit.logs.configuration.KpiDatasourceProperties;
import com.viettel.vtskit.logs.datasource.DbDatasource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SqlUtils.class);

    private SqlUtils() {
    }

    public static void closeQuiet(AutoCloseable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Exception exception) {
            LOGGER.error("closeQuiet", exception);
        }
    }

    public static boolean runQuery(KpiDatasourceProperties properties, String query, QueryCallback callback) {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = DbDatasource.getInstance(properties).getConnection();
            statement = connection.prepareStatement(query);
            if (callback != null) {
                callback.bindParameters(statement);
            }
            return statement.execute();
        } catch (SQLException exception) {
            LOGGER.error("runQuery", exception);
            return false;
        } catch (Exception exception) {
            LOGGER.error("runQuery", exception);
            return false;
        } finally {
            SqlUtils.closeQuiet(statement);
            SqlUtils.closeQuiet(connection);
        }
    }

}
