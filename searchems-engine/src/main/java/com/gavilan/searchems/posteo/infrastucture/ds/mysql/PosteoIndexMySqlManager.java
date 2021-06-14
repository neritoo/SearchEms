package com.gavilan.searchems.posteo.infrastucture.ds.mysql;

import com.gavilan.searchems.posteo.infrastucture.ds.PosteoIndexSqlService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class PosteoIndexMySqlManager implements PosteoIndexSqlService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void createIndex() {
        entityManager.createNativeQuery("CREATE INDEX si ON posteos(termino, termino_frecuency)")
                .executeUpdate();
    }

    @Override
    public void deleteIndex() {
        entityManager.createNativeQuery("ALTER TABLE posteos DROP INDEX si")
                .getFirstResult();
    }
}
