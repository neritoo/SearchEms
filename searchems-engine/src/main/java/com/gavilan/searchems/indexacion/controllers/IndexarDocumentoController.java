package com.gavilan.searchems.indexacion.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class IndexarDocumentoController {

    /**
     * API para agregar un nuevo documento a la base de datos, e indexarlo al vocabulario y lista de posteo.
     * @param documento {@link MultipartFile} con el archivo del documento propiamente dicho.
     * @return ResponseEntity.
     */
    public ResponseEntity<?> indexarDocumento(@RequestParam(name = "documento")MultipartFile documento) {
        return null;
    }

}
