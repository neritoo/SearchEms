package com.gavilan.searchems.documentos.infrastructure.ds.jpa;

import com.gavilan.searchems.documentos.exceptions.DocumentoNoEncontradoException;
import com.gavilan.searchems.documentos.infrastructure.ds.DocumentoDsGateway;
import com.gavilan.searchems.documentos.infrastructure.entities.Documento;
import com.gavilan.searchems.documentos.infrastructure.repositories.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class DocumentoJpa implements DocumentoDsGateway {

    private final DocumentoRepository documentoRepository;

    @Autowired
    public DocumentoJpa(DocumentoRepository documentoRepository) {
        this.documentoRepository = documentoRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Documento findByTitulo(String id) throws DocumentoNoEncontradoException {
        return this.documentoRepository.findById(id)
                .orElseThrow(() -> new DocumentoNoEncontradoException("No eixste el documento " + id));
    }

    @Transactional(readOnly = true)
    @Override
    public long count() {
        return this.documentoRepository.count();
    }

    @Transactional
    @Override
    public Documento save(Documento documento) {
        return this.documentoRepository.save(documento);
    }

    @Transactional
    @Override
    public void saveAll(Collection<Documento> documentoEntities) {
        this.documentoRepository.saveAll(documentoEntities);
    }
}
