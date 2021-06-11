package com.gavilan.searchems.buscador.services.impl;

import com.gavilan.searchems.buscador.services.BuscadorService;
import com.gavilan.searchems.documentos.services.DocumentoCountService;
import com.gavilan.searchems.posteo.services.PosteoFinderService;
import com.gavilan.searchems.rankeo.domain.RankingDocumento;
import com.gavilan.searchems.rankeo.services.RankingService;
import com.gavilan.searchems.vocabulario.domain.EntradaVocabulario;
import com.gavilan.searchems.vocabulario.services.VocabularioEntradaFinderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Eze Gavilan
 * @project SearchEms
 * @date 11/6/2021
 */
@Service
@Slf4j
@AllArgsConstructor
public class BuscadorServiceImpl implements BuscadorService {
    private static final int R = 15;

    private final PosteoFinderService posteoFinder;
    private final DocumentoCountService documentoCountService;
    private final VocabularioEntradaFinderService vocabularioEntradaFinderService;
    private final RankingService  rankingService;

    @Override
    public Page<RankingDocumento> buscarDocumentosConsulta(Pageable pageable, String consulta) {
        List<EntradaVocabulario> terminos = obtenerTerminosConsulta(consulta);
        int totalDocumentos = this.documentoCountService.count();

        return this.rankingService.generarRanking(pageable, terminos, R, totalDocumentos);
    }

    private List<EntradaVocabulario> obtenerTerminosConsulta(String q) {
        List<String> palabras = Arrays.stream(q.split(" "))
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        List<EntradaVocabulario> entradas = new ArrayList<>();
        palabras.forEach(palabra ->
            this.vocabularioEntradaFinderService.find(palabra).ifPresent(entradas::add));

        return entradas;
    }
}
