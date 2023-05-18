package com.viettel.vtskit.logs.schedule;

import com.viettel.vtskit.logs.callback.QueryCallback;
import com.viettel.vtskit.logs.configuration.KpiDatasourceProperties;
import com.viettel.vtskit.logs.configuration.KpiLogProperties;
import com.viettel.vtskit.logs.utils.SqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

@Configuration
@EnableScheduling
public class Schedule {

    @Autowired
    private KpiLogProperties kpiLogProperties;

    private KpiDatasourceProperties datasourceProperties;

    @PostConstruct
    private void init() {
        datasourceProperties = kpiLogProperties.getDatasource();
    }

    private boolean isUsingDB() {
        return datasourceProperties != null && datasourceProperties.isEnabled() && datasourceProperties.isUseDB();
    }


    @Scheduled(cron = "@daily")
    public void deleteKPILog() {
        final Integer timeSaveData = kpiLogProperties.getTimeSaveData();
        if (timeSaveData != null && timeSaveData > 0 && isUsingDB()) {
            String deleteKpiLogQuery = "DELETE FROM " + datasourceProperties.getTableName();
            deleteKpiLogQuery += " WHERE StartTime < ? ";

            SqlUtils.runQuery(datasourceProperties, deleteKpiLogQuery, new QueryCallback() {

                @Override
                public void bindParameters(PreparedStatement statement) throws SQLException {
                    statement.setTimestamp(1, new Timestamp(new Date().getTime() - (timeSaveData * 24 * 60 * 60 * 1000)));
                }
            });
        }
    }


}
