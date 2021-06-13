package com.gavilan.searchems.documentos.infrastructure.repositories;

import com.gavilan.searchems.documentos.infrastructure.entities.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, String> {
    long count();
}
