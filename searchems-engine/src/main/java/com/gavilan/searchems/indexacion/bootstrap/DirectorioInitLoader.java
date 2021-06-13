package com.gavilan.searchems.indexacion.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Clase sencilla de crear el directorio que contiene los documentos, en caso de que no se carguen inicialmente.
 */
@Component
public class DirectorioInitLoader {

    private final String indexDirectory;

    @Autowired
    public DirectorioInitLoader(@Qualifier("INDEX_DIRECTORY") String indexDirectory) {
        this.indexDirectory = indexDirectory;
    }

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(Paths.get(indexDirectory));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
