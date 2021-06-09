package com.gavilan.searchems.posteo.services;

/**
 * Servicio sencillo que valida si ya está cargada o no la lista de posteos en la base de datos.
 */
public interface PosteoValidadorExistenciaService {
    boolean listaCargada();
}
