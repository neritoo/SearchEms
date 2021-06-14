package com.gavilan.searchems.posteo.services.impl;

import com.gavilan.searchems.documentos.mapper.DocumentoMapper;
import com.gavilan.searchems.posteo.dto.PosteoDto;
import com.gavilan.searchems.posteo.infrastucture.ds.PosteoDsGateway;
import com.gavilan.searchems.posteo.infrastucture.entities.Posteo;
import com.gavilan.searchems.posteo.services.PosteoFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PosteoFinder implements PosteoFinderService {

    private final PosteoDsGateway posteoDsGateway;
    private final DocumentoMapper documentoMapper;

    @Autowired
    public PosteoFinder(PosteoDsGateway posteoDsGateway, DocumentoMapper documentoMapper) {
        this.posteoDsGateway = posteoDsGateway;
        this.documentoMapper = documentoMapper;
    }

    @Override
    public List<PosteoDto> find(String termino) {
        return this.posteoDsGateway.findByTermino(termino)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PosteoDto> findByTerminos(List<String> terminos) {
        return this.posteoDsGateway.findByTerminos(terminos)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private PosteoDto mapToDto(Posteo posteoEntity) {
        return PosteoDto.builder()
                .termino(posteoEntity.getPosteoPK().getTermino())
                .documento(this.documentoMapper.map(posteoEntity.getPosteoPK().getDocumento()))
                .tf(posteoEntity.getTerminoFrecuency()).build();
    }
}
