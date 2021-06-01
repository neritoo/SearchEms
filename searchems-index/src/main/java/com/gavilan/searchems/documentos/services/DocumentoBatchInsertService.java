package com.gavilan.searchems.documentos.services;

import com.gavilan.searchems.documentos.infrastructure.entities.Documento;

import java.util.List;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 1/6/2021
 */
public interface DocumentoBatchInsertService {
    void guardarDocumentos(List<Documento> documentos);
}
