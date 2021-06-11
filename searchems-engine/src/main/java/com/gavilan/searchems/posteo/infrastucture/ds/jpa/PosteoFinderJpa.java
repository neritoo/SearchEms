package com.gavilan.searchems.posteo.infrastucture.ds.jpa;

import com.gavilan.searchems.posteo.infrastucture.ds.PosteoFinderDsGateway;
import com.gavilan.searchems.posteo.infrastucture.entities.Posteo;
import com.gavilan.searchems.posteo.infrastucture.repositories.PosteoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Posteo> findByTermino(String termino) {
        return this.posteoRepository.findAllByPosteoPKTermino(termino);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Posteo> findByTermino(String termino, int r) {
        return this.posteoRepository.findByPosteoPKTermino(termino, PageRequest.of(0, r, Sort.by(Sort.Direction.DESC, "terminoFrecuency")));
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
