package com.gavilan.searchems.indexacion.services.impl;

import com.gavilan.searchems.indexacion.services.AgregarNuevoDocumentoService;
import com.gavilan.searchems.util.files.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Slf4j
public class AgregadorNuevoDocumento implements AgregarNuevoDocumentoService {

    @Override
    public void registrarNuevoDocumento(File file) {
        log.info(file.getName());
        validarExtension(file);
    }

    private boolean validarExtension(File file) {
        log.info(String.valueOf(FileUtils.validateExtension(file, "txt")));
        return false;
    }
}
