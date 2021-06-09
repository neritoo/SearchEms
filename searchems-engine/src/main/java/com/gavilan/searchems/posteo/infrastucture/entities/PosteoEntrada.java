package com.gavilan.searchems.posteo.infrastucture.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PosteoEntrada {

    @Id
    private String termino;
    private int cantDocumentos;
    private int frecuenciaMax;
}
