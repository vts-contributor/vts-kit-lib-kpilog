package com.viettel.vtskit.logs.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix ="logs")
@Data
public class LogProperties {
    @Value("${level:INFO}")
    private String level;
    @Value("${log-folder:}")
    private String logFolder;

}
