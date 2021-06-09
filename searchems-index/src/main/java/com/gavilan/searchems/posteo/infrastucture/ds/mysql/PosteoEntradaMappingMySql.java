package com.gavilan.searchems.posteo.infrastucture.ds.mysql;

import com.gavilan.searchems.posteo.infrastucture.ds.PosteoEntradaMappingDsGateway;
import com.gavilan.searchems.posteo.infrastucture.entities.PosteoEntrada;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class PosteoEntradaMappingMySql implements PosteoEntradaMappingDsGateway {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public List<PosteoEntrada> findPosteoEntradas() {
        List<PosteoEntrada> e = new ArrayList<>();

        entityManager
                .createNativeQuery
                        ("SELECT p.termino as 'termino', COUNT(p.termino) as 'cant_documentos', MAX(p.termino_frecuency) as 'frecuencia_max' FROM posteos p GROUP BY p.termino", PosteoEntrada.class)
        .getResultStream().forEach(entrada -> e.add((PosteoEntrada) entrada));

        return e;
    }
}
