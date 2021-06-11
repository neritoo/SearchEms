package com.gavilan.searchems.rankeo.domain;

import com.gavilan.searchems.documentos.dto.DocumentoDto;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Eze Gavilan
 * @project SearchEms
 * @date 10/6/2021
 */
@Getter
@ToString
public class RankingDocumento implements Serializable, Comparable<RankingDocumento> {

    private DocumentoDto documento;
    private float indiceRanking;

    public RankingDocumento() {

    }

    public RankingDocumento(DocumentoDto documento) {
        this.documento = documento;
        this.indiceRanking = 0f;
    }

    @Override
    public int compareTo(RankingDocumento o) {
        return (int) (o.indiceRanking - this.indiceRanking);
    }

    public void aumentarIr(float cantidad) {
        this.indiceRanking += cantidad;
    }
}
