package com.gavilan.searchems.indexacion.services.impl;

import com.gavilan.searchems.indexacion.services.Indexador;
import com.gavilan.searchems.posteo.services.ListaPosteoCreationService;
import com.gavilan.searchems.vocabulario.services.VocabularioLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Esta implementación de {@link Indexador} se encarga de la carga incial, recorriendo el directorio con todos los
 * documentos iniciales de la base de datos documental.
 * <br>
 * El bean para esta implementación recibe el nombre "indexadorDirectorio".
 */
@Service(value = "indexadorDirectorio")
public class IndexadorDirectorio extends Indexador {


    private final ListaPosteoCreationService posteoCreationService;
    private final VocabularioLoaderService vocabularioLoaderService;

    @Autowired
    public IndexadorDirectorio(ListaPosteoCreationService posteoCreationService,
                               VocabularioLoaderService vocabularioLoaderService) {
        this.posteoCreationService = posteoCreationService;
        this.vocabularioLoaderService = vocabularioLoaderService;
    }

    @Override
    public void cargarListaPosteo(File archivo) {
        this.posteoCreationService.crear(archivo);
    }

    @Override
    public void cargarVocabulario() {
        this.vocabularioLoaderService.cargarVocabulario();
    }
}
