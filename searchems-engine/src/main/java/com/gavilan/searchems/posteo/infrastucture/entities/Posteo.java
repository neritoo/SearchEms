package com.gavilan.searchems.posteo.infrastucture.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "posteos")
@Data
public class Posteo {

    @EmbeddedId
    private PosteoPK posteoPK;
    @Column(name = "termino_frecuency")
    private int terminoFrecuency;

    public Posteo() {

    }

    public Posteo(PosteoPK posteoPK) {
        this(posteoPK, 1);
    }

    public Posteo(PosteoPK posteoPK, int terminoFrecuency) {
        this.posteoPK = posteoPK;
        this.terminoFrecuency = terminoFrecuency;
    }

    public boolean esDeDocumento(String titulo) {
        return this.posteoPK.getDocumento().getTitulo().equals(titulo);
    }

    public void aumentarFrecuency() {
        this.terminoFrecuency++;
    }
}
