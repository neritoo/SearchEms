package com.gavilan.searchems.indexacion.services.impl;

import com.gavilan.searchems.indexacion.services.Indexador;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Esta implementación de {@link Indexador} es responsable de indexar un documento individual, requerido por el cliente.
 * <br>
 * El bean de esta implementación es "indexadorDocumento".
 */
@Service(value = "indexadorDocumento")
public class IndexadorDocumento extends Indexador {

    @Override
    public void cargarListaPosteo(File archivo) {

    }

    @Override
    public void cargarVocabulario() {

    }
}
