package com.gavilan.searchems.documentos.services.impl;

import com.gavilan.searchems.documentos.infrastructure.ds.DocumentoFinderDsGateway;
import com.gavilan.searchems.documentos.services.DocumentoCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentoCountServiceImpl implements DocumentoCountService {

    private final DocumentoFinderDsGateway documentoFinderDsGateway;

    @Autowired
    public DocumentoCountServiceImpl(DocumentoFinderDsGateway documentoFinderDsGateway) {
        this.documentoFinderDsGateway = documentoFinderDsGateway;
    }

    @Override
    public int count() {
        return (int) this.documentoFinderDsGateway.count();
    }
}
