package com.gavilan.searchems.posteo.infrastucture.ds;

import com.gavilan.searchems.posteo.infrastucture.entities.Posteo;

import java.util.Collection;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 1/6/2021
 */
public interface PosteoCreationDsGateway {
    Posteo save(Posteo posteo);

    void saveAll(Collection<Posteo> posteoList);
}
