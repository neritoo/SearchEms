package com.gavilan.searchems.posteo.infrastucture.repositories;

import com.gavilan.searchems.posteo.infrastucture.entities.Posteo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosteoRepository extends JpaRepository<Posteo, String> {
    boolean existsByTermino(String termino);
}
