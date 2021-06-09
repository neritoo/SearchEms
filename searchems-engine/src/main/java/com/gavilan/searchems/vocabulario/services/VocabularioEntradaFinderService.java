package com.gavilan.searchems.vocabulario.services;

import com.gavilan.searchems.vocabulario.domain.EntradaVocabulario;

import java.util.Optional;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 27/5/2021
 *
 * Este servicio tiene la responsabilidad de encontrar entradas del vocabulario según los
 * clientes lo requieran.
 */
public interface VocabularioEntradaFinderService {
    Optional<EntradaVocabulario> find(String termino);
}
