package com.gavilan.searchems.documentos.services.impl;

import com.gavilan.searchems.documentos.infrastructure.entities.Documento;
import com.gavilan.searchems.documentos.services.DocumentoFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 28/5/2021
 */
@Service
@Slf4j
public class DocumentoEntityFactory implements DocumentoFactory {

    @Override
    public Documento create(File archivoDocumento) {
        String titulo = archivoDocumento.getName();
        String documentoUrl;
        return new Documento();
    }

}
