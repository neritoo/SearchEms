package com.gavilan.searchems.indexacion.services.impl;

import com.gavilan.searchems.documentos.infrastructure.entities.Documento;
import com.gavilan.searchems.documentos.infrastructure.repositories.DocumentoRepository;
import com.gavilan.searchems.documentos.services.DocumentoBatchInsertService;
import com.gavilan.searchems.documentos.services.DocumentoFactory;
import com.gavilan.searchems.documentos.util.DocumentoConstants;
import com.gavilan.searchems.indexacion.exceptions.IndexingException;
import com.gavilan.searchems.indexacion.services.IndexingService;
import com.gavilan.searchems.posteo.infrastucture.entities.Posteo;
import com.gavilan.searchems.posteo.infrastucture.entities.PosteoPK;
import com.gavilan.searchems.posteo.infrastucture.repositories.PosteoRepository;
import com.gavilan.searchems.util.delimiter.Delimiter;
import com.gavilan.searchems.util.files.DirectoryReaderService;
import com.gavilan.searchems.util.files.exceptions.FileException;
import com.gavilan.searchems.vocabulario.domain.EntradaVocabulario;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private static final String DELIMITER = Delimiter.DELIMITER;

    // TODO: Lógica de negocio para creación de la LISTA DE POSTEO implementarla en un servicio de dicho paquete, y usarlo aca.

    private final DocumentoFactory documentoFactory;
    private final VocabularioLoaderService vocabularioLoader;

    private final DirectoryReaderService directoryReaderService;
    private final PosteoRepository posteoRepository;
    private final DocumentoBatchInsertService documentoBatchInsertService;
    private final DocumentoRepository documentoRepository;

    @Override
    public void indexar() {
        File documentosDir = obtenerDirectorioDocumentos();
        cargarVocabulario(documentosDir);
        if (this.documentoRepository.findAll().size() == 0) {
            crearListaPosteo(documentosDir);
        }
    }


    @Override
    public void indexarDocumento() {

    }

    private File obtenerDirectorioDocumentos() {
        return new File(DocumentoConstants.DIRECTORIO_DOCUMENTOS);
    }

    private void crearListaPosteo(File directorio) {
        List<File> archivos = obtenerArchivosDirectorio(directorio);

        log.info("Iniciando...");
        for (File doc: archivos) {
            Documento documentoActual = crearDocumento(doc.getName());
            // TODO: usar servicio de documentos...
            this.documentoRepository.save(documentoActual);
            // TODO: usar servicio de lista de posteo...
            // TODO: cambiar impl carga de vocabulario para hacerlo accediendo a la lista de posteo (p/ obtener maxFr & nr)
            indexarDoc(doc, documentoActual);
        }

        log.info("Insertando en BD...");
        log.info("Completado...");
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

    private List<File> obtenerArchivosDirectorio(File dir) {
        List<File> archivos;
        try {
            archivos = this.directoryReaderService.readDirectory(dir, "txt");
        } catch (FileException e) {
            throw new IndexingException(e.getMessage());
        }

        return archivos;
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
                    // Aprovechamos para sumar al vocabulario (en memoria) uno al nr:
                    // Se aumenta uno porque en este documento se creó su posteo para el término actual, lo que significa
                    // que, para este término actual, aumentó la cantidad de apariciones en docs que tuvo.

                    // NOTA: Impl no sirve para cuando se inicia y ya existe la lista en la base de datos, porque no se
                    // re-indexan los documentos, por ende el vocabulario tendrá inconsistencia con la lista de posteo.
                    Vocabulario.getInstance().findVocabularioEntrada(terminoActual).ifPresent(EntradaVocabulario::aumentarCantidadDocumentos);
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.posteoRepository.saveAll(currentDocumentMap.values());
    }
}
