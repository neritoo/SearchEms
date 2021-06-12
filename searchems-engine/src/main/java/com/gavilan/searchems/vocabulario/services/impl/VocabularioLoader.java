package com.gavilan.searchems.vocabulario.services.impl;

import com.gavilan.searchems.posteo.services.PosteoEntradaMappingService;
import com.gavilan.searchems.vocabulario.domain.Vocabulario;
import com.gavilan.searchems.vocabulario.exceptions.VocabularioException;
import com.gavilan.searchems.vocabulario.services.VocabularioAgregadorService;
import com.gavilan.searchems.vocabulario.services.VocabularioLoaderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 28/5/2021
 */
@Service
@AllArgsConstructor
@Slf4j
public class VocabularioLoader implements VocabularioLoaderService {

    private final VocabularioAgregadorService vocabularioAgregador;
    private final PosteoEntradaMappingService posteoEntradaMappingService;

    @Transactional
    @Override
    public Vocabulario cargarVocabulario() {
        Vocabulario vocabulario = Vocabulario.getInstance();

        long start, end;
        float time;
        log.info("Iniciando carga del vocabulario");
        start = System.currentTimeMillis();
        posteoEntradaMappingService.findEntradasVocabulario().forEach(entry -> {
            try {
                vocabularioAgregador.agregarNuevaEntrada(entry.getTermino(), entry.getCantDocumentos(), entry.getFrecuenciaMax());
            } catch (VocabularioException e) {
                e.printStackTrace();
            }
        });

        end = System.currentTimeMillis();
        time = (end - start) / 1000f;
        log.info("Time[s]: " + time);
        log.info("Time[m]: " + time / 60);

        log.info("Carga Completada");

        return vocabulario;
    }
}
