package com.gavilan.searchems.uploader.services.impl;

import com.gavilan.searchems.documentos.util.DocumentoConstants;
import com.gavilan.searchems.uploader.services.FileUploaderService;
import com.gavilan.searchems.util.files.exceptions.FileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Implementación de subida de archivo local, en el directorio "documentos".
 */
@Service
@Slf4j
public class FileLocalUploader implements FileUploaderService {

    @Override
    public String uploadFile(File file) throws FileException {
        String filename = file.getName();
        Path filePath = getPath(filename);

        try (FileInputStream fis = new FileInputStream(file)) {
            Files.copy(fis, filePath);
        } catch (FileAlreadyExistsException e) {
            deleteFile(file);
            throw new FileException("Ya existe un archivo con el nombre: " + filename);
        } catch (IOException e) {
            deleteFile(file);
            throw new FileException(e.getMessage());
        }

        deleteFile(file);
        return filename;
    }

    private Path getPath(String filename) {
        String RUTA_DIR = DocumentoConstants.DIRECTORIO_DOCUMENTOS;
        return Paths.get(RUTA_DIR).resolve(filename).toAbsolutePath();
    }

    private void deleteFile(File file) {
        if (! file.delete()) log.error("Error al eliminar archivo de carga");
    }
}
