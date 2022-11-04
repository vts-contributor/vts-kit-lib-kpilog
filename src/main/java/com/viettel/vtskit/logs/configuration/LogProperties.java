package com.viettel.vtskit.logs.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix ="logs")
public class LogProperties {
    @Value("${level:}")
    private String level;
    @Value("${log-folder:}")
    private String logFolder;
    @Value("${enable: true}")
    private boolean enable;
    public boolean getEnable() {
        return enable;
    }
    public void setEnable(boolean enable) {
        this.enable = enable;
    }
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLogFolder() {
        return logFolder;
    }

    public void setLogFolder(String logFolder) {
        this.logFolder = logFolder;
    }
}
