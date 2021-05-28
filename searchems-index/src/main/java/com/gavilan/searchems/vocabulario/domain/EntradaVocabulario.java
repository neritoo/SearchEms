package com.gavilan.searchems.vocabulario.domain;

import lombok.Data;
import lombok.ToString;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 27/5/2021
 */
@Data
@ToString
public class EntradaVocabulario {

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

    /**
     * Registra la cantidad de documentos (nr) en las que aparece el término de la entrada.
     * @param cantDocumentos int nr.
     */
    public void registrarCantidadDocumentos(int cantDocumentos) {
        this.setCantDocumentos(cantDocumentos);
    }

    /**
     * Registra la frecuencia máxima (maxTf) asociada al término de la entrada.
     * @param frecuenciaMax int maxTf.
     */
    public void registrarFrecuenciaMaxima(int frecuenciaMax) {
        this.setFrecuenciaMax(frecuenciaMax);
    }
}
