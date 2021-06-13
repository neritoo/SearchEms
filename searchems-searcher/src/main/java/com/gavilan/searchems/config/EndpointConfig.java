package com.gavilan.searchems.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(
        prefix = "searchems.endpoint"
)
@Configuration
@Data
public class EndpointConfig {
    private String baseUrl;

    @Bean(name = "BASE_URL")
    public String baseUrl() {
        return this.baseUrl;
    }
}
