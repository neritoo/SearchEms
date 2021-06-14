package com.gavilan.searchems.rankeo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gavilan.searchems.documentos.dto.DocumentoDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Clase que representa un Documento calificado/por calificar con índice de relevancia, es decir, un
 * Documento que pertenece a un ranking (ld).
 */
@Getter
@Setter
@ToString
public class RankingDocumento implements Serializable, Comparable<RankingDocumento> {

    private DocumentoDto documento;
    private float indiceRanking;
    @JsonIgnore
    private int factor;

    public RankingDocumento() {

    }

    public RankingDocumento(DocumentoDto documento) {
        this.documento = documento;
        this.indiceRanking = 0f;
        this.factor = 0;
    }

    @Override
    public int compareTo(RankingDocumento o) {
        return (int) (o.indiceRanking - this.indiceRanking);
    }

    public void aumentarIr(float cantidad) {
        this.indiceRanking += cantidad;
    }
}
