package com.gavilan.searchems.documentos.infrastructure.ds;

import com.gavilan.searchems.documentos.exceptions.DocumentoNoEncontradoException;
import com.gavilan.searchems.documentos.infrastructure.entities.Documento;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 28/5/2021
 */
public interface DocumentoFinderDsGateway {
    Documento findByTitulo(String id) throws DocumentoNoEncontradoException;
}
