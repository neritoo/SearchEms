package com.gavilan.searchems.indexacion.services.impl;

import com.gavilan.searchems.documentos.util.DocumentoConstants;
import com.gavilan.searchems.indexacion.services.IndexingService;
import com.gavilan.searchems.posteo.services.ListaPosteoCreationService;
import com.gavilan.searchems.vocabulario.domain.Vocabulario;
import com.gavilan.searchems.vocabulario.services.VocabularioLoaderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 28/5/2021
 */
@Service
@AllArgsConstructor
@Slf4j
public class IndexingServiceImpl implements IndexingService {

    private final ListaPosteoCreationService listaPosteoCreationService;
    private final VocabularioLoaderService vocabularioLoaderService;

    @Override
    public void indexar() {
        File documentosDir = obtenerDirectorioDocumentos();
        crearListaPosteo(documentosDir);
        cargarVocabulario();
        Vocabulario.getInstance().findVocabularioEntrada("many").ifPresent(x -> {
            System.out.println(x.toString());
        });
    }

    @Override
    public void indexarDocumento() {

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
