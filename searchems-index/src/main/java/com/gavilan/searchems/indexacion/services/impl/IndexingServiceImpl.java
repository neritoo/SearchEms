package com.gavilan.searchems.indexacion.services.impl;

import com.gavilan.searchems.documentos.infrastructure.entities.Documento;
import com.gavilan.searchems.documentos.services.DocumentoFactory;
import com.gavilan.searchems.documentos.util.DocumentoConstants;
import com.gavilan.searchems.indexacion.exceptions.IndexingException;
import com.gavilan.searchems.indexacion.services.IndexingService;
import com.gavilan.searchems.posteo.exceptions.PosteoNoEncontradoException;
import com.gavilan.searchems.posteo.infrastucture.entities.Posteo;
import com.gavilan.searchems.posteo.services.PosteoFinderService;
import com.gavilan.searchems.util.files.DirectoryReaderService;
import com.gavilan.searchems.util.files.exceptions.FileException;
import com.gavilan.searchems.vocabulario.domain.Vocabulario;
import com.gavilan.searchems.vocabulario.exceptions.VocabularioException;
import com.gavilan.searchems.vocabulario.services.VocabularioLoaderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 28/5/2021
 */
@Service
@AllArgsConstructor
@Slf4j
public class IndexingServiceImpl implements IndexingService {
    private static final String DELIMITER = "[ .,\\n\\r\\[\\]'()\\-\":;0-9]";

    private final DocumentoFactory documentoFactory;
    private final VocabularioLoaderService vocabularioLoader;

    private final DirectoryReaderService directoryReaderService;
    private final PosteoFinderService posteoFinder;

    @Override
    public void indexar() {
        File documentosDir = obtenerDirectorioDocumentos();
        cargarVocabulario(documentosDir);
        crearListaPosteo(documentosDir);
    }


    @Override
    public void indexarDocumento() {

    }

    private File obtenerDirectorioDocumentos() {
        return new File(DocumentoConstants.DIRECTORIO_DOCUMENTOS);
    }

    private void crearListaPosteo(File directorio) {
        // Primer algoritmo indexación de prueba.
        // RECORDAR que el nr (cant doc del termino) se puede calcular accediendo vocabulario.termino.nr = posteo.termino? posteo.entradas.size

        // por cada documento en directorio:
        //     por cada palabra (termino) del documento actual:
        //         1. verificar si existe posteo con termino:
        //             si existe --> actualizar frecuencia termino al posteo item (tf++)
        //             no existe --> crear posteo con su posteo item y tf = 1
        //         2. agregar posteos nuevos a lista de posteos
        //     1. crear documento y agregarlo a lista de documentos
        // 1. insert batch documentos
        // 2. insert batch lista de posteos

        List<File> archivos;
        try {
            archivos = this.directoryReaderService.readDirectory(directorio, ".txt");
        } catch (FileException e) {
            e.printStackTrace();
            return;
        }

        List<Documento> documentos = new ArrayList<>();
        List<Posteo> listaPosteo = new ArrayList<>();

        for (File doc: archivos) {
            // cada documento
            String documentoActual = doc.getName();
            try (Scanner fileScanner = new Scanner(new BufferedReader(new FileReader(directorio.getPath()))) )  {
                fileScanner.useDelimiter(DELIMITER);
                String terminoActual;
                while (fileScanner.hasNext()) {
                    // cada palabra
                    terminoActual = fileScanner.next();

                    if (posteoFinder.existePosteo(terminoActual)) {
                        Posteo p;
                        try {
                            p = posteoFinder.getPosteo(terminoActual);
                        } catch (PosteoNoEncontradoException e) {
                            e.printStackTrace();
                            return;
                        }
                        
                    } else {

                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void cargarVocabulario(File directorio) {
        long start, end;
        float timeSec, timeMin;
        Vocabulario vocabulario;
        try {
            start = System.currentTimeMillis();
            System.out.println("cargando...");
            vocabulario = this.vocabularioLoader.cargarVocabulario(directorio);
            end = System.currentTimeMillis();
            timeSec = (end - start) / 1000f;
            timeMin = timeSec / 60;
        } catch (VocabularioException e) {
            throw new IndexingException(e.getMessage());
        }

        log.info("Time[s]: " + timeSec);
        log.info("Time[m]: " + timeMin);
        log.info("Vocabulario Size: " + vocabulario.getMap().size());
    }
}
