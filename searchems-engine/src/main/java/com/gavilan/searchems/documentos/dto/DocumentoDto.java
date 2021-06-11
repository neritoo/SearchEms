package com.gavilan.searchems.documentos.dto;

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
public class DocumentoDto implements Serializable {

    private String titulo;
    private String url;

    public DocumentoDto(String titulo, String url) {
        this.titulo = titulo;
        this.url = url;
    }
}
