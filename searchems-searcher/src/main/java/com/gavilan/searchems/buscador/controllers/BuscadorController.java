package com.gavilan.searchems.buscador.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

        // Obviamente esto no lo hace la API, si no que el servicio que debemos implementar (probablemente "BuscadorService").
        // Es más, debería hacerlo otro service llamado, por ej.: "SeparadorPalabras", pero podría hacerse en un método
        // privado del BuscadorService...
        List<String> terminos = Arrays.stream(consulta.split(" "))
                .map(String::toLowerCase).collect(Collectors.toList());
        terminos.forEach(System.out::println);

        // La API, entonces, deberá DELEGAR la consulta a un servicio, que nos devolverá un LISTADO DOCUMENTOS más
        // relevantes de la consulta, que es lo que "mostraremos" (devolveremos)...

        // Entonces, la API depende del caso de uso buscar documentos relevantes, que tiene la lógica para, a través de
        // un rankeo, calcular y devolver los documentos con mayor puntos de ranking para la consulta solicitada...
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
