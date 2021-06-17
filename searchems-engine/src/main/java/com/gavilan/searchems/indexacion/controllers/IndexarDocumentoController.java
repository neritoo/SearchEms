package com.gavilan.searchems.indexacion.controllers;

import com.gavilan.searchems.indexacion.exceptions.IndexingException;
import com.gavilan.searchems.indexacion.services.AgregarNuevoDocumentoService;
import com.gavilan.searchems.util.files.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping("/api")
public class IndexarDocumentoController {

    private final AgregarNuevoDocumentoService nuevoDocumentoService;

    @Autowired
    public IndexarDocumentoController(AgregarNuevoDocumentoService nuevoDocumentoService) {
        this.nuevoDocumentoService = nuevoDocumentoService;
    }

    /**
     * API para agregar un nuevo documento a la base de datos, e indexarlo al vocabulario y lista de posteo.
     * @param documento {@link MultipartFile} con el archivo del documento propiamente dicho.
     * @return ResponseEntity.
     */
    @PostMapping("/documentos/indexador")
    public ResponseEntity<?> indexarDocumento(@RequestParam(name = "documento")MultipartFile documento) {
        Map<String, Object> response = new HashMap<>();

        try {
            this.nuevoDocumentoService.registrarNuevoDocumento(FileUtils.convertMultipartToFile(documento));
        } catch (IOException | IndexingException e) {
            response.put("mensaje", "Error al cargar/indexar documento");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Documento  " + documento.getOriginalFilename() + " indexado correctamente");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
