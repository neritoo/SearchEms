package com.gavilan.searchems.posteo.services.impl;

import com.gavilan.searchems.documentos.infrastructure.entities.Documento;
import com.gavilan.searchems.documentos.services.DocumentoFactory;
import com.gavilan.searchems.indexacion.exceptions.IndexingException;
import com.gavilan.searchems.posteo.infrastucture.ds.PosteoCreationDsGateway;
import com.gavilan.searchems.posteo.infrastucture.entities.Posteo;
import com.gavilan.searchems.posteo.infrastucture.entities.PosteoPK;
import com.gavilan.searchems.posteo.services.ListaPosteoCreationService;
import com.gavilan.searchems.posteo.services.PosteoValidadorExistenciaService;
import com.gavilan.searchems.util.delimiter.Delimiter;
import com.gavilan.searchems.util.files.DirectoryReaderService;
import com.gavilan.searchems.util.files.exceptions.FileException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Service
@AllArgsConstructor
@Slf4j
public class ListaPosteoCreator implements ListaPosteoCreationService {
    private static final String DELIMITER = Delimiter.DELIMITER;

    private final PosteoCreationDsGateway creationDsGateway;
    private final PosteoValidadorExistenciaService posteoValidadorExistenciaService;

    private final DocumentoFactory documentoFactory;
    private final DirectoryReaderService directoryReaderService;

    @Override
    public void crear(File directorio) {
        if (! this.posteoValidadorExistenciaService.listaCargada()) {
            crearListaPosteo(directorio);
        }
    }

    private void crearListaPosteo(File directorio) {
        List<File> archivos = obtenerArchivosDirectorio(directorio);

        long start, end;
        float time;

        log.info("Insertando en BD...");
        start = System.currentTimeMillis();
        int c = 0;
        for (File doc: archivos) {
            c++;
            log.info("Indexando documento: " + c);
            Documento documentoActual = crearDocumento(doc.getName());
            indexarDoc(doc, documentoActual);
        }
        end = System.currentTimeMillis();
        time = (end - start) / 1000f;
        log.info("Time[s]: " + time);
        log.info("Time[m]: " + time / 60);
        log.info("Completado...");
    }

    private Documento crearDocumento(String titulo) {
        return this.documentoFactory.create(titulo);
    }

    private void indexarDoc(File documentoFile, Documento documento) {
        Map<String, Posteo> currentDocumentMap = new HashMap<>();

        try (Scanner fileScanner = new Scanner(new BufferedReader(new FileReader(documentoFile))) )  {
            fileScanner.useDelimiter(DELIMITER);
            String terminoActual;

            while (fileScanner.hasNext()) {
                // cada palabra
                terminoActual = fileScanner.next().toLowerCase();
                if (terminoActual.isBlank()) continue;
                Posteo p = currentDocumentMap.get(terminoActual);
                if (p != null) {
                    p.aumentarFrecuency();
                } else {
                    p = new Posteo(new PosteoPK(terminoActual, documento));
                    currentDocumentMap.put(terminoActual, p);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.creationDsGateway.saveAll(currentDocumentMap.values());
    }

    private List<File> obtenerArchivosDirectorio(File dir) {
        List<File> archivos;
        try {
            archivos = this.directoryReaderService.readDirectory(dir, "txt");
        } catch (FileException e) {
            throw new IndexingException(e.getMessage());
        }

        return archivos;
    }
}
