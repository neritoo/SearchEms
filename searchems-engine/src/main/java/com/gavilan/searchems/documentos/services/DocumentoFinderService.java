package com.gavilan.searchems.documentos.services;

import com.gavilan.searchems.documentos.exceptions.DocumentoNoEncontradoException;
import com.gavilan.searchems.documentos.infrastructure.entities.Documento;

/**
 * Servicio que se encarga de encontrar un documento a través de su título.
 */
public interface DocumentoFinderService {
    Documento find(String titulo) throws DocumentoNoEncontradoException;

    int totalDocumentos();
}
