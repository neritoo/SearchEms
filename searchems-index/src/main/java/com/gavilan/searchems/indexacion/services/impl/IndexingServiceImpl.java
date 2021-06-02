package com.gavilan.searchems.indexacion.services.impl;

import com.gavilan.searchems.documentos.infrastructure.entities.Documento;
import com.gavilan.searchems.documentos.services.DocumentoBatchInsertService;
import com.gavilan.searchems.documentos.services.DocumentoFactory;
import com.gavilan.searchems.documentos.util.DocumentoConstants;
import com.gavilan.searchems.indexacion.exceptions.IndexingException;
import com.gavilan.searchems.indexacion.services.IndexingService;
import com.gavilan.searchems.posteo.infrastucture.entities.Posteo;
import com.gavilan.searchems.posteo.infrastucture.entities.PosteoItem;
import com.gavilan.searchems.posteo.infrastucture.entities.PosteoItemPK;
import com.gavilan.searchems.posteo.infrastucture.repositories.PosteoRepository;
import com.gavilan.searchems.posteo.services.PosteoFinderService;
import com.gavilan.searchems.util.delimiter.Delimiter;
import com.gavilan.searchems.util.files.DirectoryReaderService;
import com.gavilan.searchems.util.files.exceptions.FileException;
import com.gavilan.searchems.vocabulario.domain.Vocabulario;
import com.gavilan.searchems.vocabulario.exceptions.VocabularioException;
import com.gavilan.searchems.vocabulario.services.VocabularioLoaderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 28/5/2021
 */
@Service
@AllArgsConstructor
@Slf4j
public class IndexingServiceImpl implements IndexingService {
    private static final String DELIMITER = Delimiter.DELIMITER;

    // TODO: Lógica de negocio para creación de la LISTA DE POSTEO implementarla en un servicio de dicho paquete, y usarlo aca.

    private final DocumentoFactory documentoFactory;
    private final VocabularioLoaderService vocabularioLoader;

    private final DirectoryReaderService directoryReaderService;
    private final PosteoFinderService posteoFinder;
    private final PosteoRepository posteoRepository;
    private final DocumentoBatchInsertService documentoBatchInsertService;

    // Usamos HASHMAP como estructura de datos de soporte, previo a la insersión en BD...
    private final Map<String, Posteo> posteoMap = new HashMap<>();

    @Override
    public void indexar() {
        File documentosDir = obtenerDirectorioDocumentos();
        cargarVocabulario(documentosDir);
        crearListaPosteo(documentosDir);
    }


    @Override
    public void indexarDocumento() {

    }

    private File obtenerDirectorioDocumentos() {
        return new File(DocumentoConstants.DIRECTORIO_DOCUMENTOS);
    }

    private void crearListaPosteo(File directorio) {
        long start, end;
        float time;

        List<File> archivos = obtenerArchivosDirectorio(directorio);
        List<Documento> listaDocumentos = new ArrayList<>();
        List<Posteo> listaPosteo = new ArrayList<>();

        log.info("Iniciando...");
        start = System.currentTimeMillis();
        for (File doc: archivos) {
            // cada documento
            String tituloActual = doc.getName();
            Documento documentoActual = crearDocumento(tituloActual);
            listaDocumentos.add(documentoActual);

            indexarDoc(doc, tituloActual, documentoActual, listaPosteo);
        }

        log.info("Insertando en BD...");
        this.documentoBatchInsertService.guardarDocumentos(listaDocumentos);
        this.posteoRepository.saveAll(listaPosteo);
        end = System.currentTimeMillis();
        time = ((end - start) / 1000f);
        log.info("Completado...");
        log.info("Time[s]: " + time);
        log.info("Time[m]: " + time / 60);
    }

    private void cargarVocabulario(File directorio) {
        long start, end;
        float timeSec, timeMin;
        Vocabulario vocabulario;
        try {
            start = System.currentTimeMillis();
            System.out.println("cargando...");
            vocabulario = this.vocabularioLoader.cargarVocabulario(directorio);
            end = System.currentTimeMillis();
            timeSec = (end - start) / 1000f;
            timeMin = timeSec / 60;
        } catch (VocabularioException e) {
            throw new IndexingException(e.getMessage());
        }

        log.info("Time[s]: " + timeSec);
        log.info("Time[m]: " + timeMin);
        log.info("Vocabulario Size: " + vocabulario.getMap().size());
    }

    private List<File> obtenerArchivosDirectorio(File dir) {
        List<File> archivos;
        try {
            archivos = this.directoryReaderService.readDirectory(dir, "txt");
        } catch (FileException e) {
            throw new IndexingException(e.getMessage());
        }

        return archivos;
    }

    private Documento crearDocumento(String titulo) {
        return this.documentoFactory.create(titulo);
    }

    private void indexarDoc(File documentoFile, String titulo, Documento documento, List<Posteo> listaPosteo) {
        try (Scanner fileScanner = new Scanner(new BufferedReader(new FileReader(documentoFile.getPath()))) )  {
            fileScanner.useDelimiter(DELIMITER);
            String terminoActual;
            while (fileScanner.hasNext()) {
                // cada palabra
                terminoActual = fileScanner.next().toLowerCase();
                if (terminoActual.isBlank()) continue;
                if (this.posteoMap.containsKey(terminoActual)) {
                    Posteo p = this.posteoMap.get(terminoActual);
                    // Ya existe el posteo para ese término, pero es un NUEVO documento, por ende, hay que crear un
                    // nuevo POSTEO ITEM
                    // TODO: Al separar lógica usar servicios para ocultar detalles de implementación de CREAR nuevo post.
                    if (p.getItemPorTitulo(titulo) == null) {
                        PosteoItem posteoItem = new PosteoItem(new PosteoItemPK(terminoActual, documento), 1);
                        p.getEntradas().add(posteoItem);
                    } else {
                        p.getItemPorTitulo(titulo).sumarFrecuencia();
                    }
                } else {
                    PosteoItem posteoItem = new PosteoItem(new PosteoItemPK(terminoActual, documento), 1);
                    Posteo posteo = new Posteo(terminoActual);
                    posteo.getEntradas().add(posteoItem);
                    listaPosteo.add(posteo);
                    this.posteoMap.put(terminoActual, posteo);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
