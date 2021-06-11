package com.gavilan.searchems.posteo.dto;

import com.gavilan.searchems.documentos.dto.DocumentoDto;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Eze Gavilan
 * @project SearchEms
 * @date 11/6/2021
 */
@Data
@Builder
public class PosteoDto implements Serializable {

    private String termino;
    private DocumentoDto documento;
    private int tf;

    public PosteoDto() {

    }

    public PosteoDto(String termino, DocumentoDto documento, int tf) {
        this.termino = termino;
        this.documento = documento;
        this.tf = tf;
    }
}
