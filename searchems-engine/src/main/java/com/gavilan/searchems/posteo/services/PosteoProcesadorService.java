package com.gavilan.searchems.posteo.services;

import com.gavilan.searchems.documentos.infrastructure.entities.Documento;

import java.io.File;

/**
 * Este servicio es el encargado de procesar un Documento, generando la lista de posteo para cada término
 * del documento.
 */
public interface PosteoProcesadorService {
    void procesarDocumentoPosteo(File documentoFile, Documento documento);
}
