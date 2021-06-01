package com.gavilan.searchems.posteo.services;

import com.gavilan.searchems.posteo.exceptions.PosteoNoEncontradoException;
import com.gavilan.searchems.posteo.infrastucture.entities.Posteo;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 1/6/2021
 */
public interface PosteoFinderService {
    Posteo getPosteo(String termino) throws PosteoNoEncontradoException;

    boolean existePosteo(String termino);
}
