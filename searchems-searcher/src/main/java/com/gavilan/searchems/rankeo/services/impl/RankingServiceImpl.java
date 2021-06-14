package com.gavilan.searchems.rankeo.services.impl;

import com.gavilan.searchems.documentos.dto.DocumentoDto;
import com.gavilan.searchems.posteo.dto.PosteoDto;
import com.gavilan.searchems.posteo.services.PosteoFinderService;
import com.gavilan.searchems.rankeo.domain.Ranking;
import com.gavilan.searchems.rankeo.domain.RankingDocumento;
import com.gavilan.searchems.rankeo.services.RankingService;
import com.gavilan.searchems.vocabulario.domain.EntradaVocabulario;
import com.gavilan.searchems.vocabulario.domain.Vocabulario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        List<PosteoDto> posteosTerminos = obtenerPosteosTermino(terminos);

        for (EntradaVocabulario termino: terminos) {
            procesarTerminov2(ranking, posteosTerminos, termino, N);
        }

        //procesarTermino(ranking, terminos, R, N);

        /*
        for (EntradaVocabulario termino: terminos) {
            procesarTermino(ranking, termino, R, N);
        }
         */

        ranking.ordenarRanking();
        ranking.comprimirListaRanking(R);
        return generarPaginacionRanking(pageable, ranking);
    }

    private void ordenarTerminosMayorIdf(List<EntradaVocabulario> terminos) {
        terminos.sort(Comparator.reverseOrder());
    }

    private void procesarTermino(Ranking ranking, List<EntradaVocabulario> entradas, int R, int N) {
        List<PosteoDto> posteos = obtenerPosteosTermino(entradas);

        for (PosteoDto posteo: posteos) {
            DocumentoDto documentoActual = posteo.getDocumento();
            RankingDocumento rankingDocumento;
            if (ranking.obtenerDocumento(documentoActual).isEmpty()) {
                rankingDocumento = new RankingDocumento(documentoActual);
                ranking.agregarDocumento(rankingDocumento);
            }
            else {
                rankingDocumento = ranking.obtenerDocumento(documentoActual).get();
                rankingDocumento.setFactor(rankingDocumento.getFactor() + 1);
            }

            EntradaVocabulario entradaVocabulario = Vocabulario.getInstance().findVocabularioEntrada(posteo.getTermino())
                    .orElse(null);
            float peso = calcularPeso(rankingDocumento.getFactor(), posteo.getTf(), N, entradaVocabulario.getCantDocumentos());
            rankingDocumento.aumentarIr(peso);
        }

    }

    private void procesarTermino(Ranking ranking, EntradaVocabulario entradaVocabulario, int R, int N) {
        List<PosteoDto> posteos = obtenerPosteosTermino(entradaVocabulario.getTermino(), R);
        System.out.println(posteos);

        for (PosteoDto posteo: posteos) {
            DocumentoDto documentoActual = posteo.getDocumento();
            RankingDocumento rankingDocumento;
            if (ranking.obtenerDocumento(documentoActual).isEmpty()) {
                rankingDocumento = new RankingDocumento(documentoActual);
                ranking.agregarDocumento(rankingDocumento);
            }
            else {
                rankingDocumento = ranking.obtenerDocumento(documentoActual).get();
                rankingDocumento.setFactor(rankingDocumento.getFactor() + 1);
            }

            float peso = calcularPeso(rankingDocumento.getFactor(), posteo.getTf(), N, entradaVocabulario.getCantDocumentos());
            //if (( (double) entradaVocabulario.getCantDocumentos() / N) >= 0.85) // indice stop word...
            //    peso = 0.999f;
            rankingDocumento.aumentarIr(peso);
        }

    }

    private void procesarTerminov2(Ranking ranking, List<PosteoDto> posteos, EntradaVocabulario entradaVocabulario, int N) {
        posteos = filtrar(posteos, entradaVocabulario);

        for (PosteoDto posteo: posteos) {
            DocumentoDto documentoActual = posteo.getDocumento();
            RankingDocumento rankingDocumento;
            if (ranking.obtenerDocumento(documentoActual).isEmpty()) {
                rankingDocumento = new RankingDocumento(documentoActual);
                ranking.agregarDocumento(rankingDocumento);
            }
            else {
                rankingDocumento = ranking.obtenerDocumento(documentoActual).get();
                rankingDocumento.setFactor(rankingDocumento.getFactor() + 1);
            }

            float peso = calcularPeso(rankingDocumento.getFactor(), posteo.getTf(), N, entradaVocabulario.getCantDocumentos());
            rankingDocumento.aumentarIr(peso);
        }
    }

    private List<PosteoDto> filtrar(List<PosteoDto> posteos, EntradaVocabulario entrada) {
        return posteos.stream().filter(posteo -> posteo.getTermino().equals(entrada.getTermino())).collect(Collectors.toList());
    }

    private List<PosteoDto> obtenerPosteosTermino(List<EntradaVocabulario> terminos) {
        return this.posteoFinder.findByTerminos(terminos);
    }

    private List<PosteoDto> obtenerPosteosTermino(String termino, int R) {
        return this.posteoFinder.findTopR(termino, R);
    }

    private float calcularPeso(int factor, int tf, int N, int nr) {
        if (isStopWord(nr, N)) return 0.99f; // las stop words suman un valor fijo a todos los documentos
        return (float) (Math.pow(2, factor) * tf * Math.log( ( (double) N / nr) ));
    }

    private boolean isStopWord(int nr, int N) {
        return ((float) nr/N) >= 0.85;
    }

    private Page<RankingDocumento> generarPaginacionRanking(Pageable pageable, Ranking ranking) {
        List<RankingDocumento> currentPage;

        int start = (int) pageable.getOffset();

        int limit = (int) pageable.getOffset() + pageable.getPageSize();
        if (limit >= ranking.size()) limit = ranking.size();


        if (start >= ranking.size()) currentPage = new ArrayList<>();
        else currentPage = ranking.getLd().subList(start, limit);

        return new PageImpl<>(currentPage, pageable, ranking.size());
    }
}
