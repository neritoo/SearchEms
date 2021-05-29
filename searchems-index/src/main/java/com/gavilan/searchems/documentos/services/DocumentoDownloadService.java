package com.gavilan.searchems.documentos.services;

import com.gavilan.searchems.documentos.exceptions.DocumentoNoExisteException;
import com.gavilan.searchems.documentos.infrastructure.entities.Documento;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 28/5/2021
 *
 * Servicio para descargar un documento requerido.
 */
public interface DocumentoDownloadService {
    byte[] descargarDocumento(Documento documento) throws DocumentoNoExisteException;
}
