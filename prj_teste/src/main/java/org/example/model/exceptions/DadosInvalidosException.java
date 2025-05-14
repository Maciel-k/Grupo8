package org.example.model.exceptions;

/**
 * Exceção lançada quando os dados fornecidos são inválidos.
 */
public class DadosInvalidosException extends Exception {
    public DadosInvalidosException(String mensagem) {
        super(mensagem);
    }
}