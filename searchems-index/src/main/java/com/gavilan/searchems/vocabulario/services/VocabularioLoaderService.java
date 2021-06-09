package com.gavilan.searchems.vocabulario.services;

import com.gavilan.searchems.vocabulario.domain.Vocabulario;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 27/5/2021
 *
 * Servicio que se encarga de la carga inicial del vocabulario, leyendo archivos de un directorio.
 */
public interface VocabularioLoaderService {

    /**
     * Carga el {@link Vocabulario} cargado en memoria. Util para la carga incial, al momento
     * de indexar los documentos.
     * @return {@link Vocabulario} instancia del vocabulario cargado correctamente.
     */
    Vocabulario cargarVocabulario();
}
