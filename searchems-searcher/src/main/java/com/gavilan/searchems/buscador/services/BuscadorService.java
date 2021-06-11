package com.gavilan.searchems.buscador.services;

import com.gavilan.searchems.rankeo.domain.RankingDocumento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Eze Gavilan
 * @project SearchEms
 * @date 11/6/2021
 */
public interface BuscadorService {
    Page<RankingDocumento> buscarDocumentosConsulta(Pageable pageable, String consulta);
}
