package com.gavilan.searchems.rankeo.services;

import com.gavilan.searchems.rankeo.domain.RankingDocumento;
import com.gavilan.searchems.vocabulario.domain.EntradaVocabulario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Servicio que se encarga de generar y mantener ordenado el Ranking de documentos mas relevantes frente a una consulta.
 */
public interface RankingService {

    /**
     * Arma el rankeo de los R documentos más relevantes para un listado de términos (o palabras) requerido.
     *
     * @param pageable {@link Pageable} con la información para generar la página {@link Page}.
     * @param terminos {@link EntradaVocabulario} que representa a todos los términos a tener en cuenta para el rankeo.
     * @param R int que representa la cantidad de documentos a devolver.
     * @param N int es la cantidad de documentos totales
     * @return {@link Page<RankingDocumento>} con R documentos, calculado y ordenado por índice de ranking (relevancia)
     *                                        y paginados de acuerdo al {@link Pageable} establecido por el cliente.
     */
    Page<RankingDocumento> generarRanking(Pageable pageable, List<EntradaVocabulario> terminos, int R, int N);
}
