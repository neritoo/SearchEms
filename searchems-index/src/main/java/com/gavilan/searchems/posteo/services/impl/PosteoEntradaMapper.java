package com.gavilan.searchems.posteo.services.impl;

import com.gavilan.searchems.posteo.infrastucture.ds.PosteoEntradaMappingDsGateway;
import com.gavilan.searchems.posteo.infrastucture.entities.PosteoEntrada;
import com.gavilan.searchems.posteo.services.PosteoEntradaMappingService;
import com.gavilan.searchems.vocabulario.domain.EntradaVocabulario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PosteoEntradaMapper implements PosteoEntradaMappingService {

    private final PosteoEntradaMappingDsGateway posteoEntradaMappingDsGateway;

    @Autowired
    public PosteoEntradaMapper(PosteoEntradaMappingDsGateway posteoEntradaMappingDsGateway) {
        this.posteoEntradaMappingDsGateway = posteoEntradaMappingDsGateway;
    }

    @Override
    public List<EntradaVocabulario> findEntradasVocabulario() {
        return this.posteoEntradaMappingDsGateway.findPosteoEntradas()
                .stream()
                .map(this::mapToEntradaVocabulario)
                .collect(Collectors.toList());
    }

    private EntradaVocabulario mapToEntradaVocabulario(PosteoEntrada posteoEntrada) {
        return new EntradaVocabulario(posteoEntrada.getTermino(), posteoEntrada.getCantDocumentos(), posteoEntrada.getFrecuenciaMax());
    }
}
