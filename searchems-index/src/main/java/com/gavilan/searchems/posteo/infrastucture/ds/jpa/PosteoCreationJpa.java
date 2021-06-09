package com.gavilan.searchems.posteo.infrastucture.ds.jpa;

import com.gavilan.searchems.posteo.infrastucture.ds.PosteoCreationDsGateway;
import com.gavilan.searchems.posteo.infrastucture.entities.Posteo;
import com.gavilan.searchems.posteo.infrastucture.repositories.PosteoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 1/6/2021
 */
@Service
public class PosteoCreationJpa implements PosteoCreationDsGateway {

    private final PosteoRepository posteoRepository;

    @Autowired
    public PosteoCreationJpa(PosteoRepository posteoRepository) {
        this.posteoRepository = posteoRepository;
    }

    @Override
    public Posteo save(Posteo posteo) {
        return this.posteoRepository.save(posteo);
    }

    @Override
    public void saveAll(Collection<Posteo> posteoList) {
        this.posteoRepository.saveAll(posteoList);
    }
}
