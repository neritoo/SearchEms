package com.gavilan.searchems.indexacion.services.impl;

import com.gavilan.searchems.indexacion.services.IndexingService;
import com.gavilan.searchems.posteo.services.ListaPosteoCreationService;
import com.gavilan.searchems.vocabulario.services.VocabularioLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Esta implementación de {@link IndexingService} se encarga de la carga incial, recorriendo el directorio con todos los
 * documentos iniciales de la base de datos documental.
 * <br>
 * El bean para esta implementación recibe el nombre "indexadorDirectorio".
 */
@Service(value = "indexadorDirectorio")
public class IndexingServiceDirectorio extends IndexingService {


    private final ListaPosteoCreationService posteoCreationService;
    private final VocabularioLoaderService vocabularioLoaderService;

    @Autowired
    public IndexingServiceDirectorio(ListaPosteoCreationService posteoCreationService,
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
