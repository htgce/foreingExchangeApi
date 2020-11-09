package com.openpayd.foreignexchange.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "rates-api")
public class RatesApiProperties {
    private String baseUrl;
}
