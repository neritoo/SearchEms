package com.gavilan.searchems.buscador.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ConsultaRequest implements Serializable {

    private String consulta;
    private int page;

    public ConsultaRequest() {

    }

    public ConsultaRequest(String consulta, int page) {
        this.consulta = consulta;
        this.page = page;
    }
}
