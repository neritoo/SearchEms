package com.gavilan.searchems.posteo.infrastucture.ds.jpa;

import com.gavilan.searchems.posteo.infrastucture.ds.PosteoCreationDsGateway;
import com.gavilan.searchems.posteo.infrastucture.entities.Posteo;
import com.gavilan.searchems.posteo.infrastucture.repositories.PosteoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    @Override
    public Posteo save(Posteo posteo) {
        return this.posteoRepository.save(posteo);
    }

    @Transactional
    @Override
    public void saveAll(List<Posteo> posteoList) {
        this.posteoRepository.saveAll(posteoList);
    }
}
