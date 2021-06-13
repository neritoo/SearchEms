package com.gavilan.searchems.posteo.infrastucture.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Clase de entidad placeholder para luego mapear a EntradaVocabulario. NO se cargan nunca en la bd, solo
 * sirven para el posterior mapeo de: termino, nr, y max frec.
 */
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
