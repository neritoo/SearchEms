package com.gavilan.searchems.posteo.infrastucture.entities;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "posteo_items")
@Data
public class PosteoItem {

    @EmbeddedId
    private PosteoItemPK posteoItemPK;
    private int terminoFrecuency;

    public PosteoItem() {

    }

    public PosteoItem(PosteoItemPK posteoItemPK) {
        this(posteoItemPK, 0);
    }

    public PosteoItem(PosteoItemPK posteoItemPK, int terminoFrecuency) {
        this.posteoItemPK = posteoItemPK;
        this.terminoFrecuency = terminoFrecuency;
    }
}
