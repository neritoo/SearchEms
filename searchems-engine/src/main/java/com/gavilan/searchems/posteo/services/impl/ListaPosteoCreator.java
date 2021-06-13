package com.gavilan.searchems.posteo.services.impl;

import com.gavilan.searchems.documentos.infrastructure.entities.Documento;
import com.gavilan.searchems.documentos.services.DocumentoFactory;
import com.gavilan.searchems.indexacion.exceptions.IndexingException;
import com.gavilan.searchems.posteo.infrastucture.ds.PosteoIndexSqlCreationService;
import com.gavilan.searchems.posteo.services.ListaPosteoCreationService;
import com.gavilan.searchems.posteo.services.PosteoProcesadorService;
import com.gavilan.searchems.posteo.services.PosteoValidadorExistenciaService;
import com.gavilan.searchems.util.files.DirectoryReaderService;
import com.gavilan.searchems.util.files.exceptions.FileException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ListaPosteoCreator implements ListaPosteoCreationService {

    private final PosteoValidadorExistenciaService posteoValidadorExistenciaService;
    private final PosteoProcesadorService posteoProcesadorService;
    private final PosteoIndexSqlCreationService posteoIndexCreator;

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

        log.info("Creando índices posteo");
        crearIndex();
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
        this.posteoProcesadorService.procesarDocumentoPosteo(documentoFile, documento);
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

    private void crearIndex() {
        this.posteoIndexCreator.createIndex();
    }
}
