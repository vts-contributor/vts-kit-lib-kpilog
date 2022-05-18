package com.viettel.vtskit.logs.configuration;

import com.viettel.vtskit.logs.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;

public class KpiDatasourceProperties {

    @Value("${url:}")
    private String url;

    @Value("${username:}")
    private String username;

    @Value("${password:}")
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isUseMariaDB(){
        return !StringUtils.isNullOrEmpty(url);
    }
}
