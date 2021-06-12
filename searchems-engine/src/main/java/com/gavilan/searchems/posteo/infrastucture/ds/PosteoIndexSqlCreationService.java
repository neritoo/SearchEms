package com.gavilan.searchems.posteo.infrastucture.ds;

/**
 * Este servicio solamente se encarga de crear el índice que vincula (termino, termino_frecuency) de la entidad Posteo,
 * para agilizar las búsquedas.
 * <br>
 * Esto se hace luego de la inserción inicial, ya que está demostrado que cuantos más índices (incluyendo PK) tenga una
 * tabla o entidad de SQL, mas lenta serán las inserciones (debe realizar muchas tareas antes de insertar, como la
 * administración del heap, bloques de disco, etc).
 * Entonces, es lo ideal primero realizar la inserción global de las listas de posteos, que serán masivas solo en la carga
 * inicial, y luego recién crear el indice.
 */
public interface PosteoIndexSqlCreationService {
    void createIndex();
}
