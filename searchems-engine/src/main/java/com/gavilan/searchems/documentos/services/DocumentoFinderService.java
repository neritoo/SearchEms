package com.gavilan.searchems.documentos.services;

import com.gavilan.searchems.documentos.exceptions.DocumentoNoEncontradoException;
import com.gavilan.searchems.documentos.infrastructure.entities.Documento;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 28/5/2021
 *
 * Servicio que se encarga de encontrar un documento a través de su título.
 */
public interface DocumentoFinderService {
    Documento find(String titulo) throws DocumentoNoEncontradoException;
}
