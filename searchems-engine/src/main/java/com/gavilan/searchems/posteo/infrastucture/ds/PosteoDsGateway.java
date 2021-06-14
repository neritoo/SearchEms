package com.gavilan.searchems.posteo.infrastucture.ds;

import com.gavilan.searchems.posteo.infrastucture.entities.Posteo;

import java.util.Collection;
import java.util.List;

/**
 * Clase que se encarga del manejo de posteos con la base de datos. Sirve como capa de abstracción de
 * acceso a datos.
 */
public interface PosteoDsGateway {

    void saveAll(Collection<Posteo> posteoList);

    List<Posteo> findByTermino(String termino);

    List<Posteo> findByTerminos(List<String> terminos);

    boolean loaded();

}
