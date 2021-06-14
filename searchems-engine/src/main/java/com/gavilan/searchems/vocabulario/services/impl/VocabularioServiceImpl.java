package com.gavilan.searchems.vocabulario.services.impl;

import com.gavilan.searchems.posteo.services.PosteoEntradaMappingService;
import com.gavilan.searchems.vocabulario.domain.EntradaVocabulario;
import com.gavilan.searchems.vocabulario.domain.Vocabulario;
import com.gavilan.searchems.vocabulario.exceptions.VocabularioException;
import com.gavilan.searchems.vocabulario.services.VocabularioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@EnableAsync
@Slf4j
public class VocabularioServiceImpl implements VocabularioService {

    private final PosteoEntradaMappingService posteoEntradaMapper;

    @Autowired
    public VocabularioServiceImpl(PosteoEntradaMappingService posteoEntradaMapper) {
        this.posteoEntradaMapper = posteoEntradaMapper;
    }

    @Override
    public void agregarNuevaEntrada(String termino, int nr, int maxFrec) throws VocabularioException {
        Vocabulario.getInstance().agregarEntrada(new EntradaVocabulario(termino, nr, maxFrec));
    }

    @Override
    public Optional<EntradaVocabulario> find(String termino) {
        return Vocabulario.getInstance().findVocabularioEntrada(termino);
    }

    @Transactional(readOnly = true)
    @Async
    @Override
    public void cargarVocabulario() {
        long start, end;
        float time;
        log.info("Iniciando carga del vocabulario");
        start = System.currentTimeMillis();
        posteoEntradaMapper.findEntradasVocabulario().forEach(entry -> {
            try {
                agregarNuevaEntrada(entry.getTermino(), entry.getCantDocumentos(), entry.getFrecuenciaMax());
            } catch (VocabularioException e) {
                e.printStackTrace();
            }
        });

        end = System.currentTimeMillis();
        time = (end - start) / 1000f;
        log.info("Time[s]: " + time);
        log.info("Time[m]: " + time / 60);

        log.info("Carga Completada");
    }
}
