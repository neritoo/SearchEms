package com.gavilan.searchems.posteo.infrastucture.entities;

import com.gavilan.searchems.documentos.infrastructure.entities.Documento;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@Embeddable
public class PosteoItemPK implements Serializable {


    private String termino;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "documento_id")
    private Documento documento;

    public PosteoItemPK() {

    }

    public PosteoItemPK(String termino, Documento documento) {
        this.termino = termino;
        this.documento = documento;
    }
}