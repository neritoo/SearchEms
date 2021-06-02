package com.gavilan.searchems.documentos.services.impl;

import com.gavilan.searchems.documentos.infrastructure.entities.Documento;
import com.gavilan.searchems.documentos.services.DocumentoFactory;
import com.gavilan.searchems.documentos.util.DocumentoConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 28/5/2021
 */
@Service
@Slf4j
public class DocumentoEntityFactory implements DocumentoFactory {

    private final String baseUrl;

    @Autowired
    public DocumentoEntityFactory(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public Documento create(String titulo) {
        String apiUri = DocumentoConstants.API_DOCUMENTOS_URI;

        String documentoUrl = baseUrl.concat(apiUri + titulo);
        return new Documento(titulo, documentoUrl);
    }
}
