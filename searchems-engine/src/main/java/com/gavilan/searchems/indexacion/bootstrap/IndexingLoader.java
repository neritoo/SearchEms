package com.gavilan.searchems.indexacion.bootstrap;

import com.gavilan.searchems.documentos.util.DocumentoConstants;
import com.gavilan.searchems.indexacion.services.Indexador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Clase que se encarga de iniciar el proceso de indexación, luego de cargar todos los componentes (beans) del sistema.
 */
@Component
public class IndexingLoader implements CommandLineRunner {

    private final Indexador indexador;

    @Autowired
    public IndexingLoader(@Qualifier("indexadorDirectorio") Indexador indexador) {
        this.indexador = indexador;
    }

    @Override
    public void run(String... args) throws Exception {
        File documentosDir = obtenerDirectorioDocumentos();
        this.indexador.indexar(documentosDir);
    }

    private File obtenerDirectorioDocumentos() {
        return new File(DocumentoConstants.DIRECTORIO_DOCUMENTOS);
    }
}
