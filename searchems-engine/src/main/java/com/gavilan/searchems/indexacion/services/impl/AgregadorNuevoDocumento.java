package com.gavilan.searchems.indexacion.services.impl;

import com.gavilan.searchems.indexacion.exceptions.IndexingException;
import com.gavilan.searchems.indexacion.services.AgregarNuevoDocumentoService;
import com.gavilan.searchems.uploader.services.FileUploaderService;
import com.gavilan.searchems.util.files.FileUtils;
import com.gavilan.searchems.util.files.exceptions.FileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Slf4j
public class AgregadorNuevoDocumento implements AgregarNuevoDocumentoService {

    private final FileUploaderService fileUploaderService;

    @Autowired
    public AgregadorNuevoDocumento(FileUploaderService fileUploaderService) {
        this.fileUploaderService = fileUploaderService;
    }

    @Override
    public void registrarNuevoDocumento(File file) {
        if (! validarExtension(file)) throw new IndexingException("Solo se pueden indexar archivos .txt");

        String filename;
        try {
            filename = this.fileUploaderService.uploadFile(file);
        } catch (FileException e) {
            throw new IndexingException(e.getMessage());
        }

        log.info(filename);
    }

    private boolean validarExtension(File file) {
        return FileUtils.validateExtension(file, "txt");
    }
}
