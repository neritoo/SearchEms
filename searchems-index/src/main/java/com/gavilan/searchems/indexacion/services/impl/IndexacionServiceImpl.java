package com.gavilan.searchems.indexacion.services.impl;

import com.gavilan.searchems.documentos.infrastructure.entities.Documento;
import com.gavilan.searchems.documentos.infrastructure.repositories.DocumentoRepository;
import com.gavilan.searchems.documentos.services.DocumentoFactory;
import com.gavilan.searchems.documentos.util.DocumentoConstants;
import com.gavilan.searchems.indexacion.exceptions.IndexingException;
import com.gavilan.searchems.indexacion.services.IndexacionService;
import com.gavilan.searchems.util.files.DirectoryReaderService;
import com.gavilan.searchems.util.files.exceptions.FileException;
import com.gavilan.searchems.vocabulario.domain.Vocabulario;
import com.gavilan.searchems.vocabulario.exceptions.VocabularioException;
import com.gavilan.searchems.vocabulario.services.VocabularioLoaderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 28/5/2021
 */
@Service
@AllArgsConstructor
@Slf4j
public class IndexacionServiceImpl implements IndexacionService {

    private final DocumentoFactory documentoFactory;
    private final VocabularioLoaderService vocabularioLoader;

    // de prueba
    private final DocumentoRepository documentoRepository;
    private final DirectoryReaderService directoryReaderService;

    @Override
    public void indexar() {
        File documentosDir = obtenerDirectorioDocumentos();
        cargarVocabulario(documentosDir);

        // pruebas docs
        List<Documento>  documentos = cargarDocumentos(documentosDir);
        this.documentoRepository.saveAll(documentos);
    }

    private List<Documento> cargarDocumentos(File dir) {
        List<File> files;
        List<Documento> documentos = new ArrayList<>();
        try {
            files = directoryReaderService.readDirectory(dir, "txt");

            String tituloActual;
            for (File f: files) {
                documentos.add(documentoFactory.create(f));
            }
        } catch (FileException e) {
            e.printStackTrace();
        }

        return documentos;
    }

    @Override
    public void indexarDocumento() {

    }

    private File obtenerDirectorioDocumentos() {
        return new File(DocumentoConstants.DIRECTORIO_DOCUMENTOS);
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

    @PostConstruct
    private void init() {
        try {
            Files.createDirectories(Paths.get(DocumentoConstants.DIRECTORIO_DOCUMENTOS));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.indexar();
    }
}
