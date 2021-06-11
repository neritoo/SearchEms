package com.gavilan.searchems.buscador.services.impl;

import com.gavilan.searchems.buscador.services.BuscadorService;
import com.gavilan.searchems.documentos.dto.DocumentoDto;
import com.gavilan.searchems.posteo.dto.PosteoDto;
import com.gavilan.searchems.posteo.infrastucture.repositories.PosteoRepository;
import com.gavilan.searchems.posteo.services.PosteoFinderService;
import com.gavilan.searchems.rankeo.domain.Ranking;
import com.gavilan.searchems.rankeo.domain.RankingDocumento;
import com.gavilan.searchems.vocabulario.domain.EntradaVocabulario;
import com.gavilan.searchems.vocabulario.domain.Vocabulario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Eze Gavilan
 * @project SearchEms
 * @date 11/6/2021
 */
@Service
@Slf4j
public class BuscadorServiceImpl implements BuscadorService {
    private static final int R = 10;

    private final PosteoFinderService posteoFinder;
    private final PosteoRepository posteoRepository;

    @Autowired
    public BuscadorServiceImpl(PosteoFinderService posteoFinder, PosteoRepository posteoRepository) {
        this.posteoFinder = posteoFinder;
        this.posteoRepository = posteoRepository;
    }

    @Override
    public Ranking buscarDocumentosConsulta(String consulta) {
        String terminoPrueba = "irresistible";
        EntradaVocabulario entry = Vocabulario.getInstance().findVocabularioEntrada(terminoPrueba).get();
        Ranking ranking = new Ranking();
        long s, e;
        float t;
        s = System.currentTimeMillis();
        List<PosteoDto> posteos = this.posteoFinder.findTopR(terminoPrueba, R);
        e = System.currentTimeMillis();
        t = (e - s) / 1000f;
        log.info("Tiempo consulta BD[s]: " + t);

        for (PosteoDto posteo: posteos) {

            DocumentoDto docActual = posteo.getDocumento();
            float peso = 0;
            RankingDocumento documento;
            if (ranking.obtenerDocumento(docActual).isEmpty()) {
                documento = new RankingDocumento(docActual);
                ranking.agregarDocumento(documento);
            } else {
                documento = ranking.obtenerDocumento(docActual).get();
            }

            peso += posteo.getTf() * Math.log((593f / entry.getCantDocumentos()));
            documento.aumentarIr(peso);
        }

        ranking.ordenarRanking();
        ranking.getLd().forEach(System.out::println);
        return null;
    }
}
