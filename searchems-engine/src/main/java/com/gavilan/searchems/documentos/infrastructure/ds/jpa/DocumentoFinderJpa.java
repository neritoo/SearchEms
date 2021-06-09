package com.gavilan.searchems.documentos.infrastructure.ds.jpa;

import com.gavilan.searchems.documentos.exceptions.DocumentoNoEncontradoException;
import com.gavilan.searchems.documentos.infrastructure.ds.DocumentoFinderDsGateway;
import com.gavilan.searchems.documentos.infrastructure.entities.Documento;
import com.gavilan.searchems.documentos.infrastructure.repositories.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 28/5/2021
 */
@Service
public class DocumentoFinderJpa implements DocumentoFinderDsGateway {

    private final DocumentoRepository documentoRepository;

    @Autowired
    public DocumentoFinderJpa(DocumentoRepository documentoRepository) {
        this.documentoRepository = documentoRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Documento findByTitulo(String id) throws DocumentoNoEncontradoException {
        return this.documentoRepository.findById(id)
                .orElseThrow(() -> new DocumentoNoEncontradoException("No eixste el documento " + id));
    }
}
