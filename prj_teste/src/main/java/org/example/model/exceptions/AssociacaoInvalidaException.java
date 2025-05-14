package org.example.model.exceptions;

/**
 * Exceção lançada quando ocorre um erro na associação de voluntários a barracas.
 */
public class AssociacaoInvalidaException extends Exception {
    public AssociacaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}