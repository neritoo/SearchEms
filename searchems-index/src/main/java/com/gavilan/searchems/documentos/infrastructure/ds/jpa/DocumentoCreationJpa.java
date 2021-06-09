package com.gavilan.searchems.documentos.infrastructure.ds.jpa;

import com.gavilan.searchems.documentos.infrastructure.ds.DocumentoCreationDsGateway;
import com.gavilan.searchems.documentos.infrastructure.entities.Documento;
import com.gavilan.searchems.documentos.infrastructure.repositories.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 1/6/2021
 */
@Service
public class DocumentoCreationJpa implements DocumentoCreationDsGateway {

    private final DocumentoRepository documentoRepository;

    @Autowired
    public DocumentoCreationJpa(DocumentoRepository documentoRepository) {
        this.documentoRepository = documentoRepository;
    }

    @Transactional
    @Override
    public void saveAll(List<Documento> documentos) {
        this.documentoRepository.saveAll(documentos);
    }

    @Transactional
    @Override
    public Documento save(Documento documento) {
        return this.documentoRepository.save(documento);
    }
}
