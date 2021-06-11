package com.gavilan.searchems.documentos.mapper;

import com.gavilan.searchems.documentos.dto.DocumentoDto;
import com.gavilan.searchems.documentos.infrastructure.entities.Documento;
import org.springframework.stereotype.Service;

/**
 * @author Eze Gavilan
 * @project SearchEms
 * @date 11/6/2021
 */
@Service
public class DocumentoMapperImpl implements DocumentoMapper {

    @Override
    public DocumentoDto map(Documento documentoEntity) {
        return DocumentoDto.builder()
                .titulo(documentoEntity.getTitulo())
                .url(documentoEntity.getUrl()).build();
    }
}
