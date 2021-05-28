package com.gavilan.searchems;

import com.gavilan.searchems.vocabulario.domain.Vocabulario;
import com.gavilan.searchems.vocabulario.exceptions.VocabularioException;
import com.gavilan.searchems.vocabulario.services.VocabularioLoaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 28/5/2021
 */
@RestController
@RequestMapping("/api/demo")
public class VocabularioDemoController {

    private final Logger log = LoggerFactory.getLogger(VocabularioDemoController.class);
    private final VocabularioLoaderService vocabularioLoader;

    @Autowired
    public VocabularioDemoController(VocabularioLoaderService vocabularioLoader) {
        this.vocabularioLoader = vocabularioLoader;
    }

    @GetMapping("/vocabulario")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> cargarVocabularioDemo() {
        File documentos = new File("documentos/");
        Vocabulario vocabulario;
        long start, end;
        float time;
        try {
            start = System.currentTimeMillis();
            vocabulario = vocabularioLoader.cargarVocabulario(documentos);
            end = System.currentTimeMillis();
            time = ((end - start) / 1000f);
        } catch (VocabularioException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("Tiempo: " + time + " segundos");
        log.info(String.valueOf(vocabulario.getMap().size()));
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
