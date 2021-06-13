package com.gavilan.searchems.documentos.services.impl;

import com.gavilan.searchems.documentos.exceptions.DocumentoNoEncontradoException;
import com.gavilan.searchems.documentos.infrastructure.ds.DocumentoDsGateway;
import com.gavilan.searchems.documentos.infrastructure.entities.Documento;
import com.gavilan.searchems.documentos.services.DocumentoFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentoFinder implements DocumentoFinderService {

    private final DocumentoDsGateway documentoDsGateway;

    @Autowired
    public DocumentoFinder(DocumentoDsGateway documentoDsGateway) {
        this.documentoDsGateway = documentoDsGateway;
    }

    @Override
    public Documento find(String titulo) throws DocumentoNoEncontradoException {
        return this.documentoDsGateway.findByTitulo(titulo);
    }

    @Override
    public int totalDocumentos() {
        return (int) this.documentoDsGateway.count();
    }
}
