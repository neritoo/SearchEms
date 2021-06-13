package com.gavilan.searchems.documentos.services;

import com.gavilan.searchems.documentos.exceptions.DocumentoNoExisteException;
import com.gavilan.searchems.documentos.infrastructure.entities.Documento;

/**
 * Servicio para descargar un documento requerido.
 */
public interface DocumentoDownloadService {
    byte[] descargarDocumento(Documento documento) throws DocumentoNoExisteException;
}
