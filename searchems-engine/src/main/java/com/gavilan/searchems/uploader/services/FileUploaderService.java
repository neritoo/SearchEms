package com.gavilan.searchems.uploader.services;

import com.gavilan.searchems.util.files.exceptions.FileException;

import java.io.File;

/**
 * Service que sube un archivo documento y devuelve un String con el nombre del archivo (incluyendo extensión).
 */
public interface FileUploaderService {
    String uploadFile(File file) throws FileException;
}
