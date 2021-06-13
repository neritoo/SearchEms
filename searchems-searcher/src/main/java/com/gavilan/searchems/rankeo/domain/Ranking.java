package com.gavilan.searchems.rankeo.domain;

import com.gavilan.searchems.documentos.dto.DocumentoDto;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Esta clase representa una abstracción utilizada para generar el ranking de documentos más relevantes dada
 * una consulta. Esta clase de soporte encapsula la lógica para el manejo de la lista de documentos con sus
 * índices de ranking.
 */
@Getter
public class Ranking implements Serializable {

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

    public void ordenarRanking() {
        this.ld.sort(Comparator.naturalOrder());
    }

    public void comprimirListaRanking(int R) {
        if (size() > R) this.ld = this.ld.subList(0, R);
    }

    public int size() {
        return this.ld.size();
    }

    @Override
    public String toString() {
        return "Ranking{" +
                "ld=" + ld +
                '}';
    }
}
