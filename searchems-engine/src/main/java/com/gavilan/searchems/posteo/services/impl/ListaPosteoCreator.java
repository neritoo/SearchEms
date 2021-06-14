package com.gavilan.searchems.posteo.services.impl;

import com.gavilan.searchems.documentos.infrastructure.entities.Documento;
import com.gavilan.searchems.documentos.services.DocumentoFactory;
import com.gavilan.searchems.indexacion.exceptions.IndexingException;
import com.gavilan.searchems.posteo.infrastucture.ds.PosteoIndexSqlService;
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
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class ListaPosteoCreator implements ListaPosteoCreationService {

    private final PosteoValidadorExistenciaService posteoValidadorExistenciaService;
    private final PosteoProcesadorService posteoProcesadorService;
    private final PosteoIndexSqlService posteoIndexSqlService;

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

        eliminarIndex();
        log.info("Insertando en BD...");
        start = System.currentTimeMillis();
        int docActual = 0;
        int cantDocs = Objects.requireNonNull(directorio.list()).length;
        for (File doc: archivos) {
            docActual++;

            ConsolePrinter.animate(((float) docActual/cantDocs) * 100 + " %");
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
        this.posteoIndexSqlService.createIndex();
    }

    private void eliminarIndex() {
        this.posteoIndexSqlService.deleteIndex();
    }

    private static class ConsolePrinter {
        private static String lastLine = "";
        private static byte anim;

        public static void print(String line) {
            if (lastLine.length() > line.length()) {
                String temp = "";
                for (int i = 0; i < lastLine.length(); i++) {
                    temp += " ";
                }
                if (temp.length() > 1) System.out.print("\r" + temp);
            }
            System.out.print("\r" + line);
            lastLine = line;
        }

        public static void animate(String line) {
            switch (anim) {
                case 1:
                    print("[ \\ ] " + line);
                    break;
                case 2:
                    print("[ | ] " + line);
                    break;
                case 3:
                    print("[ / ] " + line);
                    break;
                default:
                    anim = 0;
                    print("[ - ] " + line);
            }
            anim++;
        }
    }
}
