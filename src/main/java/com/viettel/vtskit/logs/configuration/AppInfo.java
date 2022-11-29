package com.viettel.vtskit.logs.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
@Data
public class AppInfo {
    @Value("${app.application.code:N/A}")
    String code;
    @Value("${app.service.code:N/A}")
    String serviceCode;
}
