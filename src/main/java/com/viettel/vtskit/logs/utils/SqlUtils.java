package com.viettel.vtskit.logs.utils;

import com.viettel.vtskit.logs.AppLog;
import com.viettel.vtskit.logs.callback.QueryCallback;
import com.viettel.vtskit.logs.configuration.KpiDatasourceProperties;
import com.viettel.vtskit.logs.datasource.DbDatasource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlUtils {
    private SqlUtils() {
    }

    public static void closeQuiet(AutoCloseable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Exception exception) {
            AppLog.error(exception);
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
            AppLog.error(exception);
            return false;
        } finally {
            SqlUtils.closeQuiet(statement);
            SqlUtils.closeQuiet(connection);
        }
    }

}
