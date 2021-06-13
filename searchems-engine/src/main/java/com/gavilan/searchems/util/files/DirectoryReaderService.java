package com.gavilan.searchems.util.files;

import com.gavilan.searchems.util.files.exceptions.FileException;

import java.io.File;
import java.util.List;

/**
 * Servicio encargado de devolver listado de archivos de extensión requerida, dado un archivo directorio.
 */
public interface DirectoryReaderService {

    /**
     * Lee un directorio y devuelve una lista con todos los archivos, de una extensión requerida, que contiene
     * dicho directorio.
     * @param extension String extensión de los archivos que se requieren del directorio.
     * @return {@link List<File>} archivos del directorio.
     */
    List<File> readDirectory(File directorio, String extension) throws FileException;
}
