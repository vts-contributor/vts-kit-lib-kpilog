package com.viettel.vtskit.logs;

import com.viettel.vtskit.logs.callback.QueryCallback;
import com.viettel.vtskit.logs.configuration.AppInfo;
import com.viettel.vtskit.logs.configuration.KpiDatasourceProperties;
import com.viettel.vtskit.logs.configuration.KpiLogProperties;
import com.viettel.vtskit.logs.domain.KpiLog;
import com.viettel.vtskit.logs.utils.CommonUtils;
import com.viettel.vtskit.logs.utils.SqlUtils;
import com.viettel.vtskit.logs.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.lang.NonNull;

import javax.annotation.PostConstruct;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class KpiLogService {
    private KpiLogProperties kpiLogProperties;
    private KpiDatasourceProperties datasourceProperties;
    private AppInfo appInfo;
    private TaskExecutor taskExecutor;
    private static final Marker KPI_LOG_MARKER = MarkerFactory.getMarker("KPI_LOG");
    private String INSERT_KPI_LOG_QUERY;
    private String CREATE_KPI_LOG_TABLE_QUERY;

    @PostConstruct
    private void init() {
        datasourceProperties = kpiLogProperties.getDatasource();
        createKpiLogTable();
    }

    private boolean isUsingDB() {
        return datasourceProperties != null && datasourceProperties.isEnabled()  && datasourceProperties.isUseDB();
    }

    private void createKpiLogTable() {
        if (!isUsingDB()) {
            return;
        }

        CREATE_KPI_LOG_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + datasourceProperties.getTable_name() + "\n" +
                "(" +
                "  ApplicationCode VARCHAR(200) DEFAULT NULL, \n" +
                "  ServiceCode VARCHAR(200) DEFAULT NULL, \n" +
                "  SessionID text DEFAULT NULL, \n" +
                "  IP_Port_ParentNode VARCHAR(200) DEFAULT NULL, \n" +
                "  IP_Port_CurrentNode VARCHAR(200) DEFAULT NULL, \n" +
                "  RequestContent text DEFAULT NULL, \n" +
                "  ResponseContent text DEFAULT NULL, \n" +
                "  StartTime timestamp  DEFAULT NULL, \n" +
                "  EndTime timestamp  DEFAULT NULL, \n" +
                "  Duration bigint DEFAULT NULL, \n" +
                "  ErrorCode VARCHAR(200) DEFAULT NULL, \n" +
                "  ErrorDescription VARCHAR(1000) DEFAULT NULL, \n" +
                "  TransactionStatus int DEFAULT NULL, \n" +
                "  ActionName VARCHAR(200) DEFAULT NULL, \n" +
                "  UserName VARCHAR(200) DEFAULT NULL, \n" +
                "  Account VARCHAR(200) DEFAULT NULL\n" +
                ");";
        SqlUtils.runQuery(datasourceProperties, CREATE_KPI_LOG_TABLE_QUERY, null);
    }

    private void insertKpiLogToDb(final KpiLog kpiLog) {
        INSERT_KPI_LOG_QUERY = "INSERT INTO " + datasourceProperties.getTable_name() + "(\n" +
                "  ApplicationCode, \n" +
                "  ServiceCode, \n" +
                "  SessionID, \n" +
                "  IP_Port_ParentNode, \n" +
                "  IP_Port_CurrentNode, \n" +
                "  RequestContent, \n" +
                "  ResponseContent, \n" +
                "  StartTime, \n" +
                "  EndTime, \n" +
                "  Duration, \n" +
                "  ErrorCode, \n" +
                "  ErrorDescription, \n" +
                "  TransactionStatus, \n" +
                "  ActionName, \n" +
                "  UserName, \n" +
                "  Account\n" +
                ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        SqlUtils.runQuery(datasourceProperties, INSERT_KPI_LOG_QUERY, new QueryCallback() {
            @Override
            public void bindParameters(PreparedStatement statement) throws SQLException {
                statement.setString(1, kpiLog.getApplicationCode());
                statement.setString(2, kpiLog.getServiceCode());
                statement.setString(3, kpiLog.getSessionId());
                statement.setString(4, kpiLog.getIpPortParentNode());
                statement.setString(5, kpiLog.getIpPortCurrentNode());
                statement.setString(6, kpiLog.getRequestContent());
                statement.setString(7, kpiLog.getResponseContent());
                if (kpiLog.getStartTime() == null) {
                    statement.setNull(8, java.sql.Types.NULL);
                } else {
                    statement.setTimestamp(8, new Timestamp(kpiLog.getStartTime().getTime()));
                }
                if (kpiLog.getEndTime() == null) {
                    statement.setNull(9, java.sql.Types.NULL);
                } else {
                    statement.setTimestamp(9, new Timestamp(kpiLog.getEndTime().getTime()));
                }
                if (kpiLog.getDuration() == null) {
                    statement.setNull(10, java.sql.Types.NULL);
                } else {
                    statement.setLong(10, kpiLog.getDuration());
                }
                statement.setString(11, kpiLog.getErrorCode());

                if (kpiLog.getErrorDescription() != null && kpiLog.getErrorDescription().length() > 1000) {
                    statement.setString(12, kpiLog.getErrorDescription().substring(0, 1000));
                } else {
                    statement.setString(12, kpiLog.getErrorDescription());
                }

                if (kpiLog.getTransactionStatus() == null) {
                    statement.setNull(13, java.sql.Types.NULL);
                } else {
                    statement.setInt(13, kpiLog.getTransactionStatus());
                }
                statement.setString(14, kpiLog.getActionName());
                statement.setString(15, kpiLog.getUsername());
                statement.setString(16, kpiLog.getAccount());
            }
        });
    }

    public void writeLog(KpiLog kpiLog) {
        if (isUsingDB()){
            if (StringUtils.isNullOrEmpty(kpiLog.getApplicationCode())) {
                kpiLog.setApplicationCode(appInfo.getCode());
            }
            if (StringUtils.isNullOrEmpty(kpiLog.getServiceCode())) {
                kpiLog.setServiceCode(appInfo.getServiceCode());
            }

            taskExecutor.execute(() -> insertKpiLogToDb(kpiLog));
        }
    }

    @Autowired
    public void setKpiLogProperties(KpiLogProperties kpiLogProperties) {
        this.kpiLogProperties = kpiLogProperties;
    }

    @Autowired
    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    @Autowired
    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }
}
