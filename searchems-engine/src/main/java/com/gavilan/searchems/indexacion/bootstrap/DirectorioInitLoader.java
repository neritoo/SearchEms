package com.gavilan.searchems.indexacion.bootstrap;

import com.gavilan.searchems.documentos.util.DocumentoConstants;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Clase sencilla de crear el directorio que contiene los documentos, en caso de que no se carguen inicialmente.
 */
public class DirectorioInitLoader {

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(Paths.get(DocumentoConstants.DIRECTORIO_DOCUMENTOS));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
