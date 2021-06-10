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

        /*
        Conceptos:
        tf: frecuencia del término en del documento.
        N: cantidad de documentos actual.
        nr: cantidad de documentos donde aparece un termino.
        idf: Frecuencia inversa. Es el logaritmo del cociente (N/nr).
        peso(termino, documento) = tf * idf.

        Posibles clases:
        Ranking (ld: RankingDocumento[])
        RankingDocumento (ir: int, documento: Documento)
        RankeoService ( --> algun service del paquete Posteo)
        BuscadorService ( --> RankeoService)
        BuscadorController ( --> BuscadorService)

        Posibles métodos:
        buscarDocumentos()
        procesarTermino()     --> comenzar con término con mayor idf (menor nr da un idf mayor)
        obtenerListaPosteo()  --> obtenerlos ordenados desc por tf
        recuperarDocumentos() --> R es la cant. documentos a recuperar
        generarRanking()      --> mantener documentos ordenados por orden de llagada. subir ranking del doc si aparece en otro término
        calificarDocumento()  --> cada término de la consulta se califica para cada documento de su lista de posteo y se suman las calificaciones,
                                  el valor de la suma es el valor de ranking de ese documento (ir)
        calcularPeso()        --> calcula el peso = tf * log(N/nr)


        Algoritmo para resolver una consulta:
        Separar la consulta q en x términos, siendo x = cant. palabras de q.
        Crear Ranking (LD).
        Por cada termino se lo procesa, tomando de menor a mayor nr (de mayor a menor idf).
        Por cada posteo del término, se repite R veces: agregar documento al ranking con ir (indice ranking) = 0.
                                                        si no está en ld, sumar en el ir del documento el valor tf * idf.
        Continuar con el siguiente término de la consulta q.
        Devolver R primeros documentos del Ranking (ld, con ir más alto).
         */
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
