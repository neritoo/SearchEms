package com.gavilan.searchems.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 28/5/2021
 *
 * Crea un BEAN para el endpoint de la baseUrl, definida en las propiedades de la aplicación.
 */
@ConfigurationProperties(prefix = "searchems.endpoint")
@Configuration
public class EndpointConfig {

    private String baseUrl;

    public String baseUrl() {
        return this.baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
