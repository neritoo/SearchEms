package com.gavilan.searchems.indexacion.services.impl;

import com.gavilan.searchems.documentos.util.DocumentoConstants;
import com.gavilan.searchems.indexacion.services.IndexingServiceOld;
import com.gavilan.searchems.posteo.services.ListaPosteoCreationService;
import com.gavilan.searchems.vocabulario.services.VocabularioLoaderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@AllArgsConstructor
@Slf4j
@Deprecated
public class IndexingServiceOldImpl implements IndexingServiceOld {

    private final ListaPosteoCreationService listaPosteoCreationService;
    private final VocabularioLoaderService vocabularioLoaderService;

    @Override
    public void indexar() {
        File documentosDir = obtenerDirectorioDocumentos();
        crearListaPosteo(documentosDir);
        cargarVocabulario();
    }

    @Override
    public void indexarDocumento(File file) {

    }

    private File obtenerDirectorioDocumentos() {
        return new File(DocumentoConstants.DIRECTORIO_DOCUMENTOS);
    }

    private void cargarVocabulario() {
        this.vocabularioLoaderService.cargarVocabulario();
    }

    private void crearListaPosteo(File directorio) {
        this.listaPosteoCreationService.crear(directorio);
    }
}
