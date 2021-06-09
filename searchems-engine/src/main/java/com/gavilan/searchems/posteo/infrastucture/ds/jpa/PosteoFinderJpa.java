package com.gavilan.searchems.posteo.infrastucture.ds.jpa;

import com.gavilan.searchems.posteo.exceptions.PosteoNoEncontradoException;
import com.gavilan.searchems.posteo.infrastucture.ds.PosteoFinderDsGateway;
import com.gavilan.searchems.posteo.infrastucture.entities.Posteo;
import com.gavilan.searchems.posteo.infrastucture.repositories.PosteoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 1/6/2021
 */
@Service
public class PosteoFinderJpa implements PosteoFinderDsGateway {

    private final PosteoRepository posteoRepository;

    @Autowired
    public PosteoFinderJpa(PosteoRepository posteoRepository) {
        this.posteoRepository = posteoRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Posteo findByTermino(String termino) throws PosteoNoEncontradoException {
        return this.posteoRepository.findById(termino)
                .orElseThrow(() -> new PosteoNoEncontradoException("No existe el posteo con término: " + termino));
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByTermino(String termino) {
        return false;
        //return this.posteoRepository.existsByTermino(termino);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean loaded() {
        return this.posteoRepository.estaListaCargada() == 1;
    }
}
