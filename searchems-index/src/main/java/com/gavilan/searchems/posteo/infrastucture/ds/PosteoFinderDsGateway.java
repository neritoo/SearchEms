package com.gavilan.searchems.posteo.infrastucture.ds;

import com.gavilan.searchems.posteo.exceptions.PosteoNoEncontradoException;
import com.gavilan.searchems.posteo.infrastucture.entities.Posteo;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 1/6/2021
 */
public interface PosteoFinderDsGateway {
    Posteo findByTermino(String termino) throws PosteoNoEncontradoException;

    boolean existsByTermino(String termino);
}
