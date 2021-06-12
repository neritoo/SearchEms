package com.gavilan.searchems.posteo.infrastucture.repositories;

import com.gavilan.searchems.posteo.infrastucture.entities.Posteo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosteoRepository extends JpaRepository<Posteo, String> {

    List<Posteo> findAllByPosteoPKTermino(String termino);

    List<Posteo> findByPosteoPKTermino(String termino, Pageable pageable);

    @Query(value = "SELECT EXISTS (SELECT NULL FROM posteos)", nativeQuery = true)
    int estaListaCargada();

    @Query(value = "SELECT EXISTS (SELECT NULL FROM posteos)", nativeQuery = true)
    boolean listaCargada();
}
