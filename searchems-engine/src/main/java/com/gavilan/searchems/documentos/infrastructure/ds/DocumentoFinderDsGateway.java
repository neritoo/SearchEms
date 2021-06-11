package com.gavilan.searchems.documentos.infrastructure.ds;

import com.gavilan.searchems.documentos.exceptions.DocumentoNoEncontradoException;
import com.gavilan.searchems.documentos.infrastructure.entities.Documento;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 28/5/2021
 */
public interface DocumentoFinderDsGateway {
    /**
     * Encuentra un {@link Documento} a través de su título (que es su id).
     * @param id String título del documento a buscar.
     * @return {@link Documento} del título.
     * @throws DocumentoNoEncontradoException si no existe el documento para el título requerido.
     */
    Documento findByTitulo(String id) throws DocumentoNoEncontradoException;

    /**
     * Cuenta la cantidad de documentos actuales en la base de datos.
     * @return long con el número total de documentos.
     */
    long count();
}
