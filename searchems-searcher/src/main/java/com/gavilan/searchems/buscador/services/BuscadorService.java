package com.gavilan.searchems.buscador.services;

import com.gavilan.searchems.rankeo.domain.RankingDocumento;

import java.util.List;

/**
 * @author Eze Gavilan
 * @project SearchEms
 * @date 11/6/2021
 */
public interface BuscadorService {
    List<RankingDocumento> buscarDocumentosConsulta(String consulta);
}
