package com.gavilan.searchems.posteo.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@Embeddable
public class PosteoItemPK implements Serializable {


    private String termino;
    @Column(name = "id_documento")
    private Long idDocumento;

    public PosteoItemPK() {

    }

    public PosteoItemPK(String termino, Long idDocumento) {
        this.termino = termino;
        this.idDocumento = idDocumento;
    }
}
