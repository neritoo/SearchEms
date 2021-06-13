package com.gavilan.searchems.documentos.services.impl;

import com.gavilan.searchems.documentos.infrastructure.ds.DocumentoDsGateway;
import com.gavilan.searchems.documentos.infrastructure.entities.Documento;
import com.gavilan.searchems.documentos.services.DocumentoFactory;
import com.gavilan.searchems.documentos.util.DocumentoConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DocumentoEntityFactory implements DocumentoFactory {

    private final String baseUrl;
    private final DocumentoDsGateway documentoDsGateway;

    @Autowired
    public DocumentoEntityFactory(@Qualifier("BASE_URL") String baseUrl,
                                  DocumentoDsGateway documentoDsGateway) {
        this.baseUrl = baseUrl;
        this.documentoDsGateway = documentoDsGateway;
    }

    @Override
    public Documento create(String titulo) {
        String apiUri = DocumentoConstants.API_DOCUMENTOS_URI;

        String documentoUrl = baseUrl.concat(apiUri + titulo);
        return this.documentoDsGateway.save(new Documento(titulo, documentoUrl));
    }
}
