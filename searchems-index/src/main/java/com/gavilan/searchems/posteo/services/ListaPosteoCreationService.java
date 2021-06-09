package com.gavilan.searchems.posteo.services;

import java.io.File;

/**
 * Este servicio se encarga de crear la lista de posteos, es decir, cargar en la base de datos la lista de posteo generada
 * en la indexación.
 */
public interface ListaPosteoCreationService {

    /**
     * Crea la lista de posteos, guardando en la bd cada posteo.
     * @param directorio {@link File} archivo directorio que contiene todos los documentos indexados.
     */
    void crear(File directorio);
}
