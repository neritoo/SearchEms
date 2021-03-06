package com.gavilan.searchems.posteo.infrastucture.ds.jpa;

import com.gavilan.searchems.posteo.infrastucture.ds.PosteoDsGateway;
import com.gavilan.searchems.posteo.infrastucture.entities.Posteo;
import com.gavilan.searchems.posteo.infrastucture.repositories.PosteoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Profile({"mysql", "mysql-qa"})
public class PosteoJpa implements PosteoDsGateway {

    protected final PosteoRepository posteoRepository;

    @Autowired
    public PosteoJpa(PosteoRepository posteoRepository) {
        this.posteoRepository = posteoRepository;
    }

    @Override
    public void saveAll(Collection<Posteo> posteoList) {
        this.posteoRepository.saveAll(posteoList);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Posteo> findByTermino(String termino) {
        return this.posteoRepository.findAllByPosteoPKTermino(termino);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Posteo> findByTerminos(List<String> terminos) {
        return this.posteoRepository.findByListTermino(terminos);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean loaded() {
        return this.posteoRepository.estaListaCargada() == 1;
    }
}
