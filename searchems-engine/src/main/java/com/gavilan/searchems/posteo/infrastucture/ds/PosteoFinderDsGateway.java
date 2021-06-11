package com.gavilan.searchems.posteo.infrastucture.ds;

import com.gavilan.searchems.posteo.infrastucture.entities.Posteo;

import java.util.List;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 1/6/2021
 */
public interface PosteoFinderDsGateway {
    List<Posteo> findByTermino(String termino);

    List<Posteo> findByTermino(String termino, int r);

    boolean existsByTermino(String termino);

    boolean loaded();
}
