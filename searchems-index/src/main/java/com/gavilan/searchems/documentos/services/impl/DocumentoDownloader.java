package com.gavilan.searchems.documentos.services.impl;

import com.gavilan.searchems.documentos.exceptions.DocumentoNoExisteException;
import com.gavilan.searchems.documentos.infrastructure.entities.Documento;
import com.gavilan.searchems.documentos.services.DocumentoDownloadService;
import com.gavilan.searchems.documentos.util.DocumentoConstants;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 28/5/2021
 */
@Service
public class DocumentoDownloader implements DocumentoDownloadService {

    @Override
    public byte[] descargarDocumento(Documento documento) throws DocumentoNoExisteException {
        Path rutaDocumento = getPath(documento.getTitulo());
        byte[] content;
        Resource resource;

        try {
            resource = new UrlResource(rutaDocumento.toUri());
        } catch (MalformedURLException e) {
            throw new DocumentoNoExisteException(e.getMessage());
        }

        try (InputStream inputStream = resource.getInputStream()) {
            content = inputStream.readAllBytes();
        } catch (IOException e) {
            throw new DocumentoNoExisteException(e.getMessage());
        }

        return content;
    }

    /**
     * Obtiene el path de un archivo a través de su nombre.
     * @param filename String nombre del archivo.
     * @return {@link Path}.
     */
    private Path getPath(String filename) {
        return Paths.get(DocumentoConstants.DIRECTORIO_DOCUMENTOS).resolve(filename).toAbsolutePath();
    }
}
