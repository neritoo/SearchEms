package com.gavilan.searchems.posteo.services.impl;

import com.gavilan.searchems.posteo.infrastucture.ds.PosteoDsGateway;
import com.gavilan.searchems.posteo.services.PosteoValidadorExistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PosteoValidadorExistencia implements PosteoValidadorExistenciaService {

    private final PosteoDsGateway posteoDsGateway;

    @Autowired
    public PosteoValidadorExistencia(PosteoDsGateway posteoDsGateway) {
        this.posteoDsGateway = posteoDsGateway;
    }

    @Override
    public boolean listaCargada() {
        return this.posteoDsGateway.loaded();
    }
}
