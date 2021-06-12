package com.gavilan.searchems.documentos.controllers.download;

import com.gavilan.searchems.documentos.exceptions.DocumentoNoEncontradoException;
import com.gavilan.searchems.documentos.exceptions.DocumentoNoExisteException;
import com.gavilan.searchems.documentos.infrastructure.entities.Documento;
import com.gavilan.searchems.documentos.services.DocumentoDownloadService;
import com.gavilan.searchems.documentos.services.DocumentoFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 28/5/2021
 */
@RestController
@RequestMapping("/api")
public class DocumentoDownloadController {

    private final DocumentoFinderService documentoFinderService;
    private final DocumentoDownloadService documentoDownloadService;

    @Autowired
    public DocumentoDownloadController(DocumentoFinderService documentoFinderService,
                                       DocumentoDownloadService documentoDownloadService) {
        this.documentoFinderService = documentoFinderService;
        this.documentoDownloadService = documentoDownloadService;
    }

    @GetMapping("/documentos/{titulo:.+}")
    public ResponseEntity<?> descargarDocumento(@PathVariable String titulo) {
        Documento documento;
        byte[] docBytes;
        Resource docAsResource;
        String filename;

        try {
            documento = this.documentoFinderService.find(titulo);
            docBytes = this.documentoDownloadService.descargarDocumento(documento);
            docAsResource = new ByteArrayResource(docBytes);
            filename = documento.getTitulo();
        } catch (DocumentoNoExisteException | DocumentoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=\"" + filename + "\"")
                .body(docAsResource);
    }
}
