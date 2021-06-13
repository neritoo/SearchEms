package com.gavilan.searchems.documentos.infrastructure.ds;

import com.gavilan.searchems.documentos.exceptions.DocumentoNoEncontradoException;
import com.gavilan.searchems.documentos.infrastructure.entities.Documento;

import java.util.Collection;

/**
 * Clase encargada de realizar el manejo de la entidad {@link Documento} con la base de datos. Las implementaciones
 * de esta interfaz deberán definir, de acuerdo a su detalle de implementación, el manejo de {@link Documento}s
 * con la bd.
 */
public interface DocumentoDsGateway {

    /**
     * Encuentra un {@link Documento} a través de su título (que es su id).
     * @param titulo String título del documento a buscar.
     * @return {@link Documento} del título.
     * @throws DocumentoNoEncontradoException si no existe el documento para el título requerido.
     */
    Documento findByTitulo(String titulo) throws DocumentoNoEncontradoException;

    /**
     * Cuenta la cantidad de documentos actuales en la base de datos.
     * @return long con el número total de documentos.
     */
    long count();

    /**
     * Guarda un documento en la base de datos. Lo devuelve ya guardado.
     * @param documento {@link Documento} a insertar.
     * @return {@link Documento} ya guardado en la bd.
     */
    Documento save(Documento documento);

    void saveAll(Collection<Documento> documentos);
}
