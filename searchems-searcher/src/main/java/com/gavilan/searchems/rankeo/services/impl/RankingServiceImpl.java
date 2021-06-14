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
            procesarTermino(ranking, posteosTerminos, termino, N, R);
        }

        ranking.ordenarRanking();
        ranking.comprimirListaRanking(R);
        return generarPaginacionRanking(pageable, ranking);
    }

    private void ordenarTerminosMayorIdf(List<EntradaVocabulario> terminos) {
        terminos.sort(Comparator.reverseOrder());
    }

    private List<PosteoDto> obtenerPosteosTermino(List<EntradaVocabulario> terminos) {
        return this.posteoFinder.findByTerminos(terminos);
    }

    private void procesarTermino(Ranking ranking, List<PosteoDto> posteos, EntradaVocabulario termino, int N, int R) {
        List<PosteoDto> posteosTermino = filtrarPosteosTermino(posteos, termino);

        for (PosteoDto posteo: posteosTermino) {
            DocumentoDto documentoActual = posteo.getDocumento();
            RankingDocumento rankingDocumento;
            if (ranking.obtenerDocumento(documentoActual).isEmpty()) {
                // No agregar documentos si la lista está llena (con R documentos rankeados). En este caso, solo sumar
                // los ir de los documentos ya existentes en ld (ranking).
                if (ranking.size() >= R) break;
                rankingDocumento = new RankingDocumento(documentoActual);
                ranking.agregarDocumento(rankingDocumento);
            }
            else {
                rankingDocumento = ranking.obtenerDocumento(documentoActual).get();
                rankingDocumento.setFactor(rankingDocumento.getFactor() + 1);
            }

            float peso = calcularPeso(rankingDocumento.getFactor(), posteo.getTf(), N, termino.getCantDocumentos());
            rankingDocumento.aumentarIr(peso);
        }
    }

    private List<PosteoDto> filtrarPosteosTermino(List<PosteoDto> posteos, EntradaVocabulario entrada) {
        return posteos.stream()
                .filter(posteo -> posteo.getTermino().equals(entrada.getTermino()))
                .collect(Collectors.toList());
    }

    private float calcularPeso(int factor, int tf, int N, int nr) {
        // las stop words suman un valor fijo a todos los documentos (deberían sumar algo, sino, no aparecerían si N=nr cuando buscamos una stop word).
        if (isStopWord(nr, N)) return 0.99f;
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
