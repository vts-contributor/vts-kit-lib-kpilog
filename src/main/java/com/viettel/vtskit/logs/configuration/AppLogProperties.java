package com.viettel.vtskit.logs.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "logs.app-logs")
public class AppLogProperties {

    @Value("${error-log-file-name:}")
    private String errorLogFileName;

    @Value("${console-log-file-name:}")
    private String consoleLogFileName;

    public String getErrorLogFileName() {
        return errorLogFileName;
    }

    public void setErrorLogFileName(String errorLogFileName) {
        this.errorLogFileName = errorLogFileName;
    }

    public String getConsoleLogFileName() {
        return consoleLogFileName;
    }

    public void setConsoleLogFileName(String consoleLogFileName) {
        this.consoleLogFileName = consoleLogFileName;
    }

}
