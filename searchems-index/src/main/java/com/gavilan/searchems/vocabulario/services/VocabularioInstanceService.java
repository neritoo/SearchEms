package com.gavilan.searchems.vocabulario.services;

import com.gavilan.searchems.vocabulario.domain.Vocabulario;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 27/5/2021
 *
 * Servicio encargado de devolver la instancia del {@link Vocabulario}.
 */
public interface VocabularioInstanceService {
    Vocabulario getVocabulario();
}
