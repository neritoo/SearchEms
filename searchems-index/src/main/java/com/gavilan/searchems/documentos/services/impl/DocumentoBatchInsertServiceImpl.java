package com.gavilan.searchems.documentos.services.impl;

import com.gavilan.searchems.documentos.infrastructure.ds.DocumentoCreationDsGateway;
import com.gavilan.searchems.documentos.infrastructure.entities.Documento;
import com.gavilan.searchems.documentos.services.DocumentoBatchInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 1/6/2021
 */
@Service
public class DocumentoBatchInsertServiceImpl implements DocumentoBatchInsertService {

    private final DocumentoCreationDsGateway creationDsGateway;

    @Autowired
    public DocumentoBatchInsertServiceImpl(DocumentoCreationDsGateway creationDsGateway) {
        this.creationDsGateway = creationDsGateway;
    }

    @Override
    public void guardarDocumentos(List<Documento> documentos) {
        this.creationDsGateway.saveAll(documentos);
    }
}
