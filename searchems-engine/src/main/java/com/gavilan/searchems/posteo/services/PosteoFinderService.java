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

    /**
     * Encuentra la lista de posteo para un término y una cantidad entera de posteos a recuperar.
     * Encuentra los primeros R posteos del término, previamente ordenando por el terminoFrecuency.
     * @param termino String termino a buscar su lista de posteo.
     * @param r int número máximo de posteos a devolver.
     * @return lista de r posteos del término.
     */
    List<PosteoDto> findTopR(String termino, int r);
}
