package com.gavilan.searchems.indexacion.bootstrap;

import com.gavilan.searchems.indexacion.services.IndexingService;
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

    private final String indexDirectory;
    private final IndexingService indexingService;

    @Autowired
    public IndexingLoader(@Qualifier("INDEX_DIRECTORY") String indexDirectory,
                          @Qualifier("indexadorDirectorio") IndexingService indexingService) {
        this.indexDirectory = indexDirectory;
        this.indexingService = indexingService;
    }

    @Override
    public void run(String... args) throws Exception {
        File documentosDir = obtenerDirectorioDocumentos();
        this.indexingService.indexar(documentosDir);
    }

    private File obtenerDirectorioDocumentos() {
        return new File(indexDirectory);
    }
}
