package com.gavilan.searchems.documentos.infrastructure.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "documentos")
@Data
public class Documento {

    @Id
    private String titulo;
    private String url;

    public Documento() {

    }

    public Documento(String titulo, String url) {
        this.titulo = titulo;
        this.url = url;
    }
}
