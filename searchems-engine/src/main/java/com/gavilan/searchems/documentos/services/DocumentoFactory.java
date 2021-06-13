package com.gavilan.searchems.documentos.services;

import com.gavilan.searchems.documentos.infrastructure.entities.Documento;

/**
 * Este servicio es responsable de la creación de Documentos, y de la inserción en base de datos. Toma
 * un título y crea un {@link Documento} de nuestro dominio.
 */
public interface DocumentoFactory {
    Documento create(String titulo);
}
