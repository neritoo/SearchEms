package com.gavilan.searchems.posteo.services.impl;

import com.gavilan.searchems.documentos.infrastructure.entities.Documento;
import com.gavilan.searchems.posteo.infrastucture.ds.PosteoDsGateway;
import com.gavilan.searchems.posteo.infrastucture.entities.Posteo;
import com.gavilan.searchems.posteo.infrastucture.entities.PosteoPK;
import com.gavilan.searchems.posteo.services.PosteoProcesadorService;
import com.gavilan.searchems.util.delimiter.Delimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Service
public class PosteoProcesador implements PosteoProcesadorService {
    private static final String DELIMITER = Delimiter.DELIMITER;

    private final PosteoDsGateway posteoDsGateway;

    @Autowired
    public PosteoProcesador(PosteoDsGateway posteoDsGateway) {
        this.posteoDsGateway = posteoDsGateway;
    }

    @Override
    public void procesarDocumentoPosteo(File documentoFile, Documento documento) {
        Map<String, Posteo> currentDocumentMap = new HashMap<>();

        try (Scanner fileScanner = new Scanner(new BufferedReader(new FileReader(documentoFile))) )  {
            fileScanner.useDelimiter(DELIMITER);
            String terminoActual;

            while (fileScanner.hasNext()) {
                // cada palabra
                terminoActual = fileScanner.next().toLowerCase();
                if (terminoActual.isBlank()) continue;
                Posteo p = currentDocumentMap.get(terminoActual);
                if (p != null) {
                    p.aumentarFrecuency();
                } else {
                    p = new Posteo(new PosteoPK(terminoActual, documento));
                    currentDocumentMap.put(terminoActual, p);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.posteoDsGateway.saveAll(currentDocumentMap.values());
    }
}
