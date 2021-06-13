package com.gavilan.searchems.vocabulario.services;

import com.gavilan.searchems.vocabulario.domain.Vocabulario;
import com.gavilan.searchems.vocabulario.exceptions.VocabularioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@SpringBootTest
@ActiveProfiles("mysql")
class VocabularioServiceTest {

    private final Logger log = LoggerFactory.getLogger(VocabularioServiceTest.class);

    @Autowired
    VocabularioService vocabularioService;
    @BeforeEach
    void setUp() {

    }

    @Transactional
    @Test
    void agregarVariasEntradasTiempoTest() {

        try {
            for (int i = 0; i < 100000; i++) {
                vocabularioService.agregarNuevaEntrada(UUID.randomUUID().toString(), 0, 0);
            }
        } catch (VocabularioException e) {
            e.printStackTrace();
        }

        log.info(String.valueOf(Vocabulario.getInstance().getMap().values().size()));
    }
}