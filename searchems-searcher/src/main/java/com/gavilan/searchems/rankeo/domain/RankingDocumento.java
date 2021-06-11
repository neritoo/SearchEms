package com.gavilan.searchems.rankeo.domain;

import com.gavilan.searchems.documentos.dto.DocumentoDto;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Eze Gavilan
 * @project SearchEms
 * @date 10/6/2021
 */
@Getter
@ToString
public class RankingDocumento {

    private DocumentoDto documento;
    private float indiceRanking;

    public RankingDocumento() {

    }

    public RankingDocumento(DocumentoDto documento) {
        this.documento = documento;
        this.indiceRanking = 0f;
    }

    public void aumentarIr(float cantidad) {
        this.indiceRanking += cantidad;
    }
}
