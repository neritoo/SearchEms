package com.gavilan.searchems.vocabulario.services.impl;

import com.gavilan.searchems.vocabulario.domain.EntradaVocabulario;
import com.gavilan.searchems.vocabulario.domain.Vocabulario;
import com.gavilan.searchems.vocabulario.exceptions.VocabularioException;
import com.gavilan.searchems.vocabulario.services.VocabularioAgregadorService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 27/5/2021
 */
@Service
@Qualifier("vocabularioAgregador")
public class VocabularioAgregador implements VocabularioAgregadorService {

    @Override
    public void agregarNuevaEntrada(String termino, int nr, int maxFrec) throws VocabularioException {
        //Vocabulario vocabulario = vocabularioInstance.getVocabulario();
        //EntradaVocabulario entrada = new EntradaVocabulario(termino);

        Vocabulario.getInstance().agregarEntrada(new EntradaVocabulario(termino, nr, maxFrec));
    }
}
