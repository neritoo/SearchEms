package com.gavilan.searchems.indexacion.services.impl;

import com.gavilan.searchems.documentos.infrastructure.entities.Documento;
import com.gavilan.searchems.documentos.services.DocumentoFactory;
import com.gavilan.searchems.indexacion.services.IndexingService;
import com.gavilan.searchems.posteo.services.PosteoProcesadorService;
import com.gavilan.searchems.vocabulario.services.VocabularioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Esta implementación de {@link IndexingService} es responsable de indexar un documento individual, requerido por el cliente.
 * <br>
 * El bean de esta implementación es "indexadorDocumento".
 */
@Service(value = "indexadorDocumento")
@AllArgsConstructor
public class IndexingServiceDocumento extends IndexingService {

    private final PosteoProcesadorService posteoProcesadorService;
    private final VocabularioService vocabularioService;
    private final DocumentoFactory documentoFactory;

    @Override
    public void cargarListaPosteo(File archivo) {
        this.posteoProcesadorService.procesarDocumentoPosteo(archivo, crearDocumento(archivo.getName()));
    }

    @Override
    public void cargarVocabulario() {
        this.vocabularioService.cargarVocabulario();
    }

    private Documento crearDocumento(String titulo) {
        return this.documentoFactory.create(titulo);
    }
}
