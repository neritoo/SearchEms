package com.gavilan.searchems.posteo;

import com.gavilan.searchems.documentos.infrastructure.repositories.DocumentoRepository;
import com.gavilan.searchems.posteo.infrastucture.ds.PosteoCreationDsGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 1/6/2021
 */
@Component
@AllArgsConstructor
public class TestPosteo {

    private final PosteoCreationDsGateway posteoCreation;
    private final DocumentoRepository documentoRepository;

    @PostConstruct
    public void init() {
        /*
        // docs
        Documento d1 = new Documento("tuvieja.txt", "algo");
        Documento d2 = new Documento("divina-comedia.txt", "some");
        documentoRepository.saveAll(Arrays.asList(d1, d2));

        List<Posteo> listaPosteo = new ArrayList<>();
        // posteo para "casa" --> termino1.
        String termino1 = "casa";
        PosteoItem pe1 = new PosteoItem(new PosteoItemPK(termino1, d1));
        PosteoItem pe2 = new PosteoItem(new PosteoItemPK(termino1, d2));

        Posteo pCasa = new Posteo(termino1, new ArrayList<>(Arrays.asList(pe1, pe2)));
        listaPosteo.add(pCasa);

        // posteo para "bici" --> termino2.
        String termino2 = "bici";
        PosteoItem pe3 = new PosteoItem(new PosteoItemPK(termino2, d1));
        PosteoItem pe4 = new PosteoItem(new PosteoItemPK(termino2, d2));

        Posteo pBici = new Posteo(termino2, new ArrayList<>(Arrays.asList(pe3, pe4)));
        listaPosteo.add(pBici);

        posteoCreation.saveAll(listaPosteo);

         */
    }
}
