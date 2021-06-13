package com.gavilan.searchems.vocabulario.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EntradaVocabulario implements Comparable<EntradaVocabulario> {

    private String termino;
    private int cantDocumentos;
    private int frecuenciaMax;

    public EntradaVocabulario() {

    }

    public EntradaVocabulario(String termino) {
        this(termino, 0, 0);
    }

    public EntradaVocabulario(String termino, int cantDocumentos, int frecuenciaMax) {
        this.termino = termino;
        this.cantDocumentos = cantDocumentos;
        this.frecuenciaMax = frecuenciaMax;
    }

    @Override
    public int compareTo(EntradaVocabulario o) {
        return (o.getCantDocumentos() - this.getCantDocumentos());
    }
}
