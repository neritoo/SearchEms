package com.gavilan.searchems.indexacion.bootstrap;

import com.gavilan.searchems.indexacion.services.IndexingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Clase que se encarga de iniciar el proceso de indexación, luego de cargar todos los componentes (beans) del sistema.
 */
@Component
public class IndexingLoader implements CommandLineRunner {

    private final IndexingService indexingService;

    @Autowired
    public IndexingLoader(IndexingService indexingService) {
        this.indexingService = indexingService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.indexingService.indexar();
    }
}
