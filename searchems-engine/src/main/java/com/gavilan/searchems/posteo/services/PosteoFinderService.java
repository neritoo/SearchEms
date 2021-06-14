package com.gavilan.searchems.posteo.services;

import com.gavilan.searchems.posteo.dto.PosteoDto;

import java.util.List;

/**
 * Servicio responsable de encontrar listas de posteo.
 */
public interface PosteoFinderService {

    /**
     * Encuentra la lista de posteo dado un término.
     * @param termino String termino a buscar su lista de posteo.
     * @return lista de posteo del término.
     */
    List<PosteoDto> find(String termino);

    List<PosteoDto> findByTerminos(List<String> terminos);
}
