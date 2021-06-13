package com.gavilan.searchems.vocabulario.services;

import com.gavilan.searchems.vocabulario.domain.EntradaVocabulario;
import com.gavilan.searchems.vocabulario.domain.Vocabulario;
import com.gavilan.searchems.vocabulario.exceptions.VocabularioException;

import java.util.Optional;

public interface VocabularioService {

    /**
     * Este método realiza la inserción de una nueva {@link EntradaVocabulario} al vocabulario.
     */
    void agregarNuevaEntrada(String termino, int nr, int mf) throws VocabularioException;

    /**
     * Este método encuentra entradas del vocabulario según un término requerido.
     */
    Optional<EntradaVocabulario> find(String termino);

    /**
     * Carga el {@link Vocabulario} cargado en memoria. Util para la carga incial, al momento
     * de indexar los documentos.
     */
    void cargarVocabulario();
}
