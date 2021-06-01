package com.gavilan.searchems.vocabulario.services.impl;

import com.gavilan.searchems.util.files.DirectoryReaderService;
import com.gavilan.searchems.util.files.exceptions.FileException;
import com.gavilan.searchems.vocabulario.domain.Vocabulario;
import com.gavilan.searchems.vocabulario.exceptions.VocabularioException;
import com.gavilan.searchems.vocabulario.services.VocabularioAgregadorService;
import com.gavilan.searchems.vocabulario.services.VocabularioLoaderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 28/5/2021
 */
@Service
@AllArgsConstructor
public class VocabularioLoader implements VocabularioLoaderService {
    private static final String DELIMITER = "[ .,\\n\\r\\[\\]'()\\-\":;0-9]";

    private final VocabularioAgregadorService vocabularioAgregadorService;
    private final DirectoryReaderService directoryReader;

    @Override
    public Vocabulario cargarVocabulario(File directorio) throws VocabularioException {
        Vocabulario vocabulario = Vocabulario.getInstance();

        List<File> archivosDir;
        try {
            archivosDir = this.directoryReader.readDirectory(directorio, "txt");
        } catch (FileException e) {
            throw new VocabularioException(e.getMessage());
        }

        for (File documento: archivosDir) {
            llenarVocabulario(documento);
        }

        return vocabulario;
    }

    private void llenarVocabulario(File file) throws VocabularioException {
        String termino;
        try (Scanner fileScanner = new Scanner(new BufferedReader(new FileReader(file.getPath())))) {
            fileScanner.useDelimiter(DELIMITER);

            while (fileScanner.hasNext()) {
                termino = fileScanner.next();
                if (! termino.isBlank()) {
                    agregarEntradaAlVocabulario(termino);
                }
            }
        } catch (FileNotFoundException e) {
            throw new VocabularioException(e.getMessage());
        }
    }

    private void agregarEntradaAlVocabulario(String termino) throws VocabularioException {
        this.vocabularioAgregadorService.agregarNuevaEntrada(termino);
    }
}
