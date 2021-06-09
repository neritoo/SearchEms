package com.gavilan.searchems.posteo.services;

import com.gavilan.searchems.vocabulario.domain.EntradaVocabulario;

import java.util.List;

/**
 * Servicio utilizado por el loader del vocabulario para mappear los términos (EntradaVocabulario) con las entradas de
 * la lista de posteo. Se hace con el fin de que los servicios de vocabulario no conozcan el detalle de como se implementa
 * la búsqueda de cada PosteoEntrada, y simplemente lo vea como un mapeo.
 */
public interface PosteoEntradaMappingService {
    List<EntradaVocabulario> findEntradasVocabulario();
}
