package com.gavilan.searchems.posteo.infrastucture.ds;

import com.gavilan.searchems.posteo.infrastucture.entities.PosteoEntrada;

import java.util.List;

public interface PosteoEntradaMappingDsGateway {
    List<PosteoEntrada> findPosteoEntradas();

}
