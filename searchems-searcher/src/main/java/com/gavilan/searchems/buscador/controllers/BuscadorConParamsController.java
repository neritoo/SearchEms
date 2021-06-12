package com.gavilan.searchems.buscador.controllers;

import com.gavilan.searchems.buscador.services.BuscadorService;
import com.gavilan.searchems.rankeo.domain.RankingDocumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BuscadorConParamsController {

    private final BuscadorService buscadorService;

    @Autowired
    public BuscadorConParamsController(BuscadorService buscadorService) {
        this.buscadorService = buscadorService;
    }

    @GetMapping("/buscador/params")
    public ResponseEntity<?> buscarConsulta(@RequestParam(name = "consulta") String consulta,
                                            @RequestParam(name = "page") Integer page) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable = PageRequest.of(page, 4);
        Page<RankingDocumento> ranking;

        ranking = this.buscadorService.buscarDocumentosConsulta(pageable, consulta);

        if (ranking.getContent().size() == 0) {
            response.put("mensaje", "Lista vacía");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("ranking", ranking);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
