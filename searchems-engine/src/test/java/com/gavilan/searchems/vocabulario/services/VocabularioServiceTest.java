package com.gavilan.searchems.vocabulario.services;

import com.gavilan.searchems.vocabulario.exceptions.VocabularioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 27/5/2021
 */
@SpringBootTest
class VocabularioServiceTest {

    private final Logger log = LoggerFactory.getLogger(VocabularioServiceTest.class);

    @Autowired
    VocabularioAgregadorService agregadorService;

    @Autowired
    VocabularioEntradaFinderService finderService;

    @Autowired
    VocabularioInstanceService instanceService;

    @BeforeEach
    void setUp() {

    }

    @Transactional
    @Test
    void agregarVariasEntradasTiempoTest() {

        try {
            for (int i = 0; i < 100000; i++) {
                agregadorService.agregarNuevaEntrada(UUID.randomUUID().toString());
            }
        } catch (VocabularioException e) {
            e.printStackTrace();
        }

        log.info(String.valueOf(instanceService.getVocabulario().getMap().values().size()));
    }
}