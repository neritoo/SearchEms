package com.gavilan.searchems.posteo.infrastucture.ds.jpa;

import com.gavilan.searchems.posteo.infrastucture.repositories.PosteoRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("h2")
public class PosteoH2Jpa extends PosteoJpa {

    public PosteoH2Jpa(PosteoRepository posteoRepository) {
        super(posteoRepository);
    }

    @Override
    public boolean loaded() {
        return this.posteoRepository.listaCargada();
    }
}
