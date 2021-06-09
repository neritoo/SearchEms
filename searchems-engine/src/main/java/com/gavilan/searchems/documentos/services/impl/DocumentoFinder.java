package com.gavilan.searchems.documentos.services.impl;

import com.gavilan.searchems.documentos.exceptions.DocumentoNoEncontradoException;
import com.gavilan.searchems.documentos.infrastructure.ds.DocumentoFinderDsGateway;
import com.gavilan.searchems.documentos.infrastructure.entities.Documento;
import com.gavilan.searchems.documentos.services.DocumentoFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 28/5/2021
 */
@Service
public class DocumentoFinder implements DocumentoFinderService {

    private final DocumentoFinderDsGateway documentoFinderDsGateway;

    @Autowired
    public DocumentoFinder(DocumentoFinderDsGateway documentoFinderDsGateway) {
        this.documentoFinderDsGateway = documentoFinderDsGateway;
    }

    @Override
    public Documento find(String titulo) throws DocumentoNoEncontradoException {
        return this.documentoFinderDsGateway.findByTitulo(titulo);
    }
}
