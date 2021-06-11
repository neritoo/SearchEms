package com.gavilan.searchems.rankeo.services.impl;

import com.gavilan.searchems.documentos.dto.DocumentoDto;
import com.gavilan.searchems.posteo.dto.PosteoDto;
import com.gavilan.searchems.posteo.services.PosteoFinderService;
import com.gavilan.searchems.rankeo.domain.Ranking;
import com.gavilan.searchems.rankeo.domain.RankingDocumento;
import com.gavilan.searchems.rankeo.services.RankingService;
import com.gavilan.searchems.vocabulario.domain.EntradaVocabulario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RankingServiceImpl implements RankingService {

    private final PosteoFinderService posteoFinder;

    @Autowired
    public RankingServiceImpl(PosteoFinderService posteoFinder) {
        this.posteoFinder = posteoFinder;
    }

    @Override
    public Page<RankingDocumento> generarRanking(Pageable pageable, List<EntradaVocabulario> terminos, int R, int N) {
        Ranking ranking = new Ranking();
        ordenarTerminosMayorIdf(terminos);
        for (EntradaVocabulario termino: terminos) {
            procesarTermino(ranking, termino, R, N);
        }

        ranking.ordenarRanking();
        ranking.comprimirListaRanking(R);
        return generarPaginacionRanking(pageable, ranking);
    }

    private void ordenarTerminosMayorIdf(List<EntradaVocabulario> terminos) {
        terminos.sort(Comparator.reverseOrder());
    }

    private void procesarTermino(Ranking ranking, EntradaVocabulario entradaVocabulario, int R, int N) {
        List<PosteoDto> posteos = obtenerPosteosTermino(entradaVocabulario.getTermino(), R);

        for (PosteoDto posteo: posteos) {
            DocumentoDto documentoActual = posteo.getDocumento();
            RankingDocumento rankingDocumento;
            if (ranking.obtenerDocumento(documentoActual).isEmpty()) {
                rankingDocumento = new RankingDocumento(documentoActual);
                ranking.agregarDocumento(rankingDocumento);
            }
            else
                rankingDocumento = ranking.obtenerDocumento(documentoActual).get();

            float peso = calcularPeso(posteo.getTf(), N, entradaVocabulario.getCantDocumentos());
            rankingDocumento.aumentarIr(peso);
        }

    }

    private List<PosteoDto> obtenerPosteosTermino(String termino, int R) {
        return this.posteoFinder.findTopR(termino, R);
    }

    private float calcularPeso(int tf, int N, int nr) {
        return (float) (tf * Math.log( ( (float) N / nr) ));
    }

    private Page<RankingDocumento> generarPaginacionRanking(Pageable pageable, Ranking ranking) {
        List<RankingDocumento> currentPage;

        int start = (int) pageable.getOffset();

        int limit = (int) pageable.getOffset() + pageable.getPageSize();
        if (limit >= ranking.size()) limit = ranking.size();


        if (start >= ranking.size()) currentPage = new ArrayList<>();
        else currentPage = ranking.getLd().subList(start, limit);

        ranking.getLd().forEach(System.out::println);

        return new PageImpl<>(currentPage, pageable, ranking.size());
    }
}
