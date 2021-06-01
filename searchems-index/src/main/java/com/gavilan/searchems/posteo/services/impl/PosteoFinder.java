package com.gavilan.searchems.posteo.services.impl;

import com.gavilan.searchems.posteo.exceptions.PosteoNoEncontradoException;
import com.gavilan.searchems.posteo.infrastucture.ds.PosteoFinderDsGateway;
import com.gavilan.searchems.posteo.infrastucture.entities.Posteo;
import com.gavilan.searchems.posteo.services.PosteoFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 1/6/2021
 */
@Service
public class PosteoFinder implements PosteoFinderService {

    private final PosteoFinderDsGateway posteoFinderDsGateway;

    @Autowired
    public PosteoFinder(PosteoFinderDsGateway posteoFinderDsGateway) {
        this.posteoFinderDsGateway = posteoFinderDsGateway;
    }

    @Override
    public Posteo getPosteo(String termino) throws PosteoNoEncontradoException {
        return this.posteoFinderDsGateway.findByTermino(termino);
    }

    @Override
    public boolean existePosteo(String termino) {
        return this.posteoFinderDsGateway.existsByTermino(termino);
    }
}
