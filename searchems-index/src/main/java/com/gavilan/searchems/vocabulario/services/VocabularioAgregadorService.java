package com.gavilan.searchems.vocabulario.services;

import com.gavilan.searchems.vocabulario.domain.EntradaVocabulario;
import com.gavilan.searchems.vocabulario.exceptions.VocabularioException;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 27/5/2021
 *
 * Este servicio provee una interface para la inserción de una nueva
 * {@link EntradaVocabulario} al vocabulario.
 */
public interface VocabularioAgregadorService {
    void agregarNuevaEntrada(String termino, int nr, int mf) throws VocabularioException;
}
