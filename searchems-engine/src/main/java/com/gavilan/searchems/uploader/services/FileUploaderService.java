package com.gavilan.searchems.uploader.services;

import com.gavilan.searchems.util.files.exceptions.FileException;

import java.io.File;

/**
 * Service que sube un archivo documento y devuelve el file creado.
 */
public interface FileUploaderService {
    File uploadFile(File file) throws FileException;
}
