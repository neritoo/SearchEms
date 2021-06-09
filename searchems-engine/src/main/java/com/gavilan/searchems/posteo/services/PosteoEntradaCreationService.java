package com.gavilan.searchems.posteo.services;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 1/6/2021
 *
 * Este servicio es el encargado de crear una entrada a la lista de posteo.
 * <br>
 * ACLARACIÓN: No se encarga de la insersión en base de datos, que se hace al momento de indexación.
 */
public interface PosteoEntradaCreationService {
    void crearListaPosteo();
}
