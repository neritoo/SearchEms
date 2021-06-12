package com.gavilan.searchems.indexacion.services.impl;

import com.gavilan.searchems.indexacion.exceptions.IndexingException;
import com.gavilan.searchems.indexacion.services.AgregarNuevoDocumentoService;
import com.gavilan.searchems.indexacion.services.IndexingService;
import com.gavilan.searchems.uploader.services.FileUploaderService;
import com.gavilan.searchems.util.files.FileUtils;
import com.gavilan.searchems.util.files.exceptions.FileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Slf4j
public class AgregadorNuevoDocumento implements AgregarNuevoDocumentoService {

    private final FileUploaderService fileUploaderService;
    private final IndexingService indexingService;

    @Autowired
    public AgregadorNuevoDocumento(FileUploaderService fileUploaderService,
                                   @Qualifier("indexadorDocumento") IndexingService indexingService) {
        this.fileUploaderService = fileUploaderService;
        this.indexingService = indexingService;
    }

    @Override
    public void registrarNuevoDocumento(File file) throws IndexingException {
        File documentoFile = subirArchivo(file);
        indexarDocumento(documentoFile);
    }

    private File subirArchivo(File file) {
        if (! validarExtension(file)) throw new IndexingException("Solo se pueden indexar archivos .txt");

        File documento;
        try {
            documento = this.fileUploaderService.uploadFile(file);
        } catch (FileException e) {
            throw new IndexingException(e.getMessage());
        }
        log.info(documento.getName().concat(" : ").concat(documento.getAbsolutePath()));

        return documento;
    }

    private boolean validarExtension(File file) {
        return FileUtils.validateExtension(file, "txt");
    }

    private void indexarDocumento(File archivo) {
        long start, end;
        float time;

        start = System.currentTimeMillis();
        this.indexingService.indexar(archivo);
        end = System.currentTimeMillis();

        time = (end - start) / 1000f;
        log.info("Time[s]: " + time);
    }
}
