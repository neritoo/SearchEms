package com.gavilan.searchems.uploader.services.impl;

import com.gavilan.searchems.uploader.services.FileUploaderService;
import com.gavilan.searchems.util.files.exceptions.FileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    private final String indexDirectory;

    @Autowired
    public FileLocalUploader(@Qualifier("INDEX_DIRECTORY") String indexDirectory) {
        this.indexDirectory = indexDirectory;
    }

    @Override
    public File uploadFile(File file) throws FileException {
        String filename = file.getName();
        Path filePath = getPath(filename);

        try (FileInputStream fis = new FileInputStream(file)) {
            Files.copy(fis, filePath);
        } catch (FileAlreadyExistsException e) {
            deleteOldFile(file);
            throw new FileException("Ya existe un archivo con el nombre: " + filename);
        } catch (IOException e) {
            deleteOldFile(file);
            throw new FileException(e.getMessage());
        }

        deleteOldFile(file);
        Path newFilePath = getPath(filename);
        return newFilePath.toFile();
    }

    private Path getPath(String filename) {
        return Paths.get(indexDirectory).resolve(filename).toAbsolutePath();
    }

    private void deleteOldFile(File file) {
        if (! file.delete()) log.error("Error al eliminar archivo de carga");
    }
}
