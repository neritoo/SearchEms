package com.gavilan.searchems.vocabulario.services.impl;

import com.gavilan.searchems.vocabulario.domain.Vocabulario;
import com.gavilan.searchems.vocabulario.services.VocabularioInstanceService;
import org.springframework.stereotype.Service;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 27/5/2021
 */
@Service
public class VocabularioInstanciador implements VocabularioInstanceService {

    @Override
    public Vocabulario getVocabulario() {
        return Vocabulario.getInstance();
    }
}
