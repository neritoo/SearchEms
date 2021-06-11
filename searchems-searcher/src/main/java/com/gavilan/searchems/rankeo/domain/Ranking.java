package com.gavilan.searchems.rankeo.domain;

import com.gavilan.searchems.documentos.dto.DocumentoDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Eze Gavilan
 * @project SearchEms
 * @date 11/6/2021
 */
@Data
public class Ranking {

    private List<RankingDocumento> ld;

    public Ranking() {
        this.ld = new ArrayList<>();
    }

    public void agregarDocumento(RankingDocumento documento) {
        ld.add(documento);
    }

    public Optional<RankingDocumento> obtenerDocumento(DocumentoDto documento) {
        for (RankingDocumento rd: ld) {
            if (rd.getDocumento().equals(documento))
                return Optional.of(rd);
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "Ranking{" +
                "ld=" + ld +
                '}';
    }
}
