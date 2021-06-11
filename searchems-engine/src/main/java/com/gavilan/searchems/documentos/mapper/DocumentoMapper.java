package com.gavilan.searchems.documentos.mapper;

import com.gavilan.searchems.documentos.dto.DocumentoDto;
import com.gavilan.searchems.documentos.infrastructure.entities.Documento;

/**
 * @author Eze Gavilan
 * @project SearchEms
 * @date 11/6/2021
 */
public interface DocumentoMapper {
    DocumentoDto map(Documento documentoEntity);
}
