package com.gavilan.searchems.indexacion.services;

import java.io.File;

/**
 * Implementación del patrón "Template Method", donde tenemos dos métodos primitivos: cargarListaPosteo() y cargarVocbulario(),
 * los cuales dependen de quien implemente la clase, que determina si la indexación es a nivel de directorio o de documento
 * individual.
 * El método plantilla en este caso sería indexar(), quien determina el orden de ejecución de los algoritmos para indexar.
 */
public abstract class Indexador {

    public void indexar(File archivo) {
        cargarListaPosteo(archivo);
        cargarVocabulario();
    }

    public abstract void cargarListaPosteo(File archivo);

    public abstract void cargarVocabulario();
}
