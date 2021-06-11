package com.gavilan.searchems.buscador.services;

import com.gavilan.searchems.rankeo.domain.Ranking;

/**
 * @author Eze Gavilan
 * @project SearchEms
 * @date 11/6/2021
 */
public interface BuscadorService {
    Ranking buscarDocumentosConsulta(String consulta);
}
