package com.gavilan.searchems.documentos.infrastructure.repositories;

import com.gavilan.searchems.documentos.infrastructure.entities.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 28/5/2021
 */
@Repository
public interface DocumentoRepository extends JpaRepository<Documento, String> {
    long count();
}
