package com.gavilan.searchems.indexacion.services.impl;

import com.gavilan.searchems.indexacion.services.IndexingService;
import com.gavilan.searchems.posteo.services.ListaPosteoCreationService;
import com.gavilan.searchems.vocabulario.services.VocabularioService;
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
    private final VocabularioService vocabularioService;

    @Autowired
    public IndexingServiceDirectorio(ListaPosteoCreationService posteoCreationService,
                                     VocabularioService vocabularioService) {
        this.posteoCreationService = posteoCreationService;
        this.vocabularioService = vocabularioService;
    }

    @Override
    public void cargarListaPosteo(File archivo) {
        this.posteoCreationService.crear(archivo);
    }

    @Override
    public void cargarVocabulario() {
        this.vocabularioService.cargarVocabulario();
    }
}
