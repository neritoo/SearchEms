package com.gavilan.searchems.util.files;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class FileUtils {

    public static File convertMultipartToFile(MultipartFile multipartFile) throws IOException {
        File archivoConvertido = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fileOutputStream = new FileOutputStream(archivoConvertido);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();

        return archivoConvertido;
    }

    public static boolean validateExtension(File file, String ext) {
        ext = ".".concat(ext);
        return file.getName().endsWith(ext);
    }
}
