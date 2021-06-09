package com.gavilan.searchems.vocabulario.domain;

import com.gavilan.searchems.vocabulario.exceptions.VocabularioException;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 27/5/2021
 *
 * Esta clase representa al Vocabulario de términos guardado en memoria principal.
 * <br>
 * Implementación del patrón Singleton, para tener una única instancia global con un único punto de
 * acceso al vocabulario.
 */
@Getter
public class Vocabulario {

    private final Map<String, EntradaVocabulario> map;
    private static Vocabulario VOCABULARIO_INSTANCE;

    private Vocabulario() {
        this.map = new HashMap<>();
    }

    public static Vocabulario getInstance() {
        if (VOCABULARIO_INSTANCE == null) VOCABULARIO_INSTANCE = new Vocabulario();
        return VOCABULARIO_INSTANCE;
    }

    /**
     * Agrega una nueva entrada al Vocabulario actual.
     * @param entrada {@link EntradaVocabulario} con los datos por defecto de la entrada.
     */
    public void agregarEntrada(EntradaVocabulario entrada) throws VocabularioException {
        if (entrada.getTermino() == null)
            throw new VocabularioException("No se puede agregar una entrada si su término es null");

        this.map.put(entrada.getTermino(), entrada);
    }

    /**
     * Obtiene una {@link EntradaVocabulario} en el mapa del vocabulario, si existe. Busca por término.
     * @param termino String término de la entrada que se desea obtener.
     * @return {@link EntradaVocabulario} encontrada.
     */
    public Optional<EntradaVocabulario> findVocabularioEntrada(String termino) {
        return Optional.of(this.map.get(termino));
    }

    /**
     * Calcula y devuelve el tamaño del vocabulario (que es, el size del map que usamos como estructura)
     * @return int tamaño del vocabulario, es decir, cantidad de términos.
     */
    public int size() {
        return this.map.size();
    }

    @Override
    public String toString() {
        return this.map.toString();
    }
}
