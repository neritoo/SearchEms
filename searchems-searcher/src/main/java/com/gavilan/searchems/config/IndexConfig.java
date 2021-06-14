package com.gavilan.searchems.config;

import com.gavilan.searchems.documentos.util.DocumentoConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(
        prefix = "searchems.index"
)
@Configuration
@Data
public class IndexConfig {
    private String indexDirectory;

    @Bean(name = "INDEX_DIRECTORY")
    public String indexDirectory() {
        return (indexDirectory == null || indexDirectory.isEmpty() || indexDirectory.isBlank()) ?
                DocumentoConstants.DIRECTORIO_DOCUMENTOS : indexDirectory;
    }

}
