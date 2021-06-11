package com.gavilan.searchems.documentos.infrastructure.ds;

import com.gavilan.searchems.documentos.infrastructure.entities.Documento;

import java.util.List;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 1/6/2021
 */
public interface DocumentoCreationDsGateway {
    void saveAll(List<Documento> documentoEntities);

    Documento save(Documento documento);
}
