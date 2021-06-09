package com.gavilan.searchems.posteo.services.impl;

import com.gavilan.searchems.posteo.infrastucture.ds.PosteoFinderDsGateway;
import com.gavilan.searchems.posteo.services.PosteoValidadorExistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PosteoValidadorExistencia implements PosteoValidadorExistenciaService {

    private final PosteoFinderDsGateway finderDsGateway;

    @Autowired
    public PosteoValidadorExistencia(PosteoFinderDsGateway finderDsGateway) {
        this.finderDsGateway = finderDsGateway;
    }

    @Override
    public boolean listaCargada() {
        return this.finderDsGateway.loaded();
    }
}
