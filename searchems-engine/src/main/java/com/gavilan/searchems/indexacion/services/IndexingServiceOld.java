package com.gavilan.searchems.indexacion.services;

import java.io.File;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 28/5/2021
 *
 * Servicio que se encarga de realizar la indexación, cargando el vocabulario y, una vez cargado,
 * creando la lista de posteos asociada al vocabulario.
 */
@Deprecated
public interface IndexingServiceOld {
    void indexar();

    void indexarDocumento(File file);
}
