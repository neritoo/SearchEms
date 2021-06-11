package com.gavilan.searchems.posteo.services;

import com.gavilan.searchems.posteo.dto.PosteoDto;

import java.util.List;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 1/6/2021
 */
public interface PosteoFinderService {
    List<PosteoDto> find(String termino);

    List<PosteoDto> findTopR(String termino, int r);
}
