package com.gavilan.searchems.posteo.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posteos")
@Data
public class Posteo {

    @Id
    private String termino;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "posteo_termino")
    private List<PosteoItem> entradas;

    public Posteo() {

    }

    public Posteo(String termino) {
        this(termino, new ArrayList<>());
    }

    public Posteo(String termino, List<PosteoItem> entradas) {
        this.termino = termino;
        this.entradas = entradas;
    }
}
