package com.gavilan.searchems.posteo.services.impl;

import com.gavilan.searchems.documentos.mapper.DocumentoMapper;
import com.gavilan.searchems.posteo.dto.PosteoDto;
import com.gavilan.searchems.posteo.infrastucture.ds.PosteoFinderDsGateway;
import com.gavilan.searchems.posteo.infrastucture.entities.Posteo;
import com.gavilan.searchems.posteo.services.PosteoFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 1/6/2021
 */
@Service
public class PosteoFinder implements PosteoFinderService {

    private final PosteoFinderDsGateway posteoFinderDsGateway;
    private final DocumentoMapper documentoMapper;

    @Autowired
    public PosteoFinder(PosteoFinderDsGateway posteoFinderDsGateway, DocumentoMapper documentoMapper) {
        this.posteoFinderDsGateway = posteoFinderDsGateway;
        this.documentoMapper = documentoMapper;
    }

    @Override
    public List<PosteoDto> find(String termino) {
        return this.posteoFinderDsGateway.findByTermino(termino)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PosteoDto> findTopR(String termino, int r) {
        return this.posteoFinderDsGateway.findByTermino(termino, r)
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
