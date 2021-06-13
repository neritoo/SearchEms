package com.gavilan.searchems.util.files;

import com.gavilan.searchems.util.files.exceptions.FileException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class DirectoryReaderServiceImpl implements DirectoryReaderService {

    @Override
    public List<File> readDirectory(File directorio, String extension) throws FileException {
        if (! directorio.exists())
            throw new FileException("No existe el directorio");

        if (! directorio.isDirectory())
            throw new FileException("El archivo " + directorio.getName() + " no es un directorio");
        return cargarArchivos(directorio, extension);
    }

    private List<File> cargarArchivos(File dir, String ext) {
        List<File> files = new ArrayList<>();
        Stream.of(Objects.requireNonNull(dir
                .listFiles((file) -> FileUtils.validateExtension(file, ext))))
                .filter(f -> f.isFile() && ! f.isHidden())
                .forEach(files::add);
        return files;
    }
}
