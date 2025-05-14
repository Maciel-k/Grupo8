package org.example.model.exceptions;

/**
 * Exceção lançada quando não há stock suficiente para realizar uma operação.
 */
public class StockInsuficienteException extends Exception {
    public StockInsuficienteException(String mensagem) {
        super(mensagem);
    }
}