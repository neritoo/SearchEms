package com.gavilan.searchems.documentos.exceptions;

/**
 * @author Eze Gavilan
 * @project SearChems
 * @date 28/5/2021
 */
public class DocumentoNoExisteException extends Exception {
    public DocumentoNoExisteException(String exMsg) {
        super(exMsg);
    }
}
