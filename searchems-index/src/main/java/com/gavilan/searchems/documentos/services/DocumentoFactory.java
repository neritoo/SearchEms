package com.gavilan.searchems.documentos.services;

import com.gavilan.searchems.documentos.infrastructure.entities.Documento;

import java.io.File;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 28/5/2021
 *
 * Este servicio es responsable de la creación de Documentos, pero no de la inserción en base de datos. Toma
 * un {@link File} y crea un {@link Documento} de nuestro dominio.
 * <br>
 * ACLARACIÓN: Como la inserción de los documentos se busca hacerla en lotes, al momento de indexar, y más
 * precisamente, de crear la lista de posteo, sería ineficiente insertar cada documento en la base de datos
 * de manera individual, por esto, no se provee un servicio para hacerlo.
 */
public interface DocumentoFactory {
    Documento create(String titulo);
}
