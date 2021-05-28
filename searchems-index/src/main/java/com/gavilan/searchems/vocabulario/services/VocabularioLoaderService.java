package com.gavilan.searchems.vocabulario.services;

import com.gavilan.searchems.vocabulario.domain.Vocabulario;
import com.gavilan.searchems.vocabulario.exceptions.VocabularioException;

import java.io.File;

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
     * @param directorio File archivo directorio donde se contienen los documentos necesarios para
     *                   cargar el vocabulario.
     * @return {@link Vocabulario} instancia del vocabulario cargado correctamente.
     * @throws VocabularioException si falla el proceso por algún motivo.
     */
    Vocabulario cargarVocabulario(File directorio) throws VocabularioException;
}
