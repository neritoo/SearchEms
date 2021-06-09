package com.gavilan.searchems.vocabulario.services.impl;

import com.gavilan.searchems.vocabulario.domain.EntradaVocabulario;
import com.gavilan.searchems.vocabulario.domain.Vocabulario;
import com.gavilan.searchems.vocabulario.services.VocabularioEntradaFinderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 27/5/2021
 */
@Service
@Qualifier("vocabularioEntradaFinder")
public class VocabularioEntradaFinder implements VocabularioEntradaFinderService {

    @Override
    public Optional<EntradaVocabulario> find(String termino) {
        return Vocabulario.getInstance().findVocabularioEntrada(termino);
    }
}
