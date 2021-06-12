package com.gavilan.searchems.indexacion.services;

import java.io.File;

/**
 * Servicio utilizado por clientes que requieran insertar un nuevo documento (archivo .txt) a la base de datos documental,
 * lo que requiere su indexación.
 */
public interface AgregarNuevoDocumentoService {
    void registrarNuevoDocumento(File file);
}
