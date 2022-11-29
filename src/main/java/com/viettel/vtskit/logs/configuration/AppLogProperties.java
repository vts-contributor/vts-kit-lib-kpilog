package com.viettel.vtskit.logs.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "logs.app-logs")
@Data
public class AppLogProperties {

    @Value("${error-log-file-name:error_log.log}")
    private String errorLogFileName;

    @Value("${console-log-file-name:}")
    private String consoleLogFileName;

}
