package com.gavilan.searchems.documentos.mapper;

import com.gavilan.searchems.documentos.dto.DocumentoDto;
import com.gavilan.searchems.documentos.infrastructure.entities.Documento;

public interface DocumentoMapper {
    DocumentoDto map(Documento documentoEntity);
}
