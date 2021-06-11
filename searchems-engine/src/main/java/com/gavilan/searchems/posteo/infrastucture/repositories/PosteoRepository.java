package com.gavilan.searchems.posteo.infrastucture.repositories;

import com.gavilan.searchems.posteo.infrastucture.entities.Posteo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosteoRepository extends JpaRepository<Posteo, String> {

    @Query(value = "SELECT MAX(p.termino_frecuency) FROM posteos p WHERE p.termino = ?1", nativeQuery = true)
    int calcularFrecuenciaMaximaByTermino(String termino);

    int countByPosteoPKTermino(String termino);

    List<Posteo> findAllByPosteoPKTermino(String termino);

    List<Posteo> findTop10ByPosteoPKTerminoOrderByTerminoFrecuencyDesc(String termino);

    @Query(value = "SELECT EXISTS (SELECT NULL FROM posteos)", nativeQuery = true)
    int estaListaCargada();
}
