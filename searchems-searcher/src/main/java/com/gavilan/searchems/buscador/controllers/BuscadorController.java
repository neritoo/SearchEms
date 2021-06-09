package com.gavilan.searchems.buscador.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eze Gavilan
 * @project SearchEms
 * @date 9/6/2021
 */
@RestController
@RequestMapping("/api")
public class BuscadorController {

    @GetMapping("/buscador")
    public ResponseEntity<?> buscarConsulta(@RequestParam(name = "consulta") String consulta) {
        System.out.println("Consulta: " + consulta);
        String[] terminos = consulta.split(" ");
        for (String t: terminos) {
            System.out.println(t);
        }

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
