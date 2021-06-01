package com.gavilan.searchems.posteo.services.mock;

import com.gavilan.searchems.posteo.infrastucture.entities.Posteo;
import org.springframework.stereotype.Service;

@Service
public class PosteoService implements PosteoMockService {
    @Override
    public Posteo crearEntradaPosteo(Posteo posteo) {
        return null;
    }

    /*
    private final PosteoRepository posteoRepository;
    private final TerminoRepository terminoRepository;

    @Autowired
    public PosteoEntradaCreationService(PosteoRepository posteoRepository, TerminoRepository terminoRepository) {
        this.posteoRepository = posteoRepository;
        this.terminoRepository = terminoRepository;
    }

    @Transactional
    @Override
    public Posteo crearEntradaPosteo(Posteo posteo) {
        return this.posteoRepository.save(posteo);
    }

    @PostConstruct
    public void init() {
        Termino casa = new Termino("casa");
        Termino casaBD = this.terminoRepository.save(casa);
        Posteo posteo1 = new Posteo(new PosteoPK(casaBD));
        PosteoItem posteo1Item = new PosteoItem(new PosteoItemPK(casa, 1L), 9);
        /* TODO: Lo importante del CU para crear una entrada a la lista de posteos, es la creación del PosteoItem con el mismo TERMINO.
         */

        // TODO: No usar clase Termino, solo String... validar si es necesario.
    /*
        posteo1.getDocumentos().add(posteo1Item);
        crearEntradaPosteo(posteo1);
    }

     */
}
