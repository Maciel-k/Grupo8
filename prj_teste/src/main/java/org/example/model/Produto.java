package org.example.model;

import java.io.Serializable;
import org.example.model.exceptions.StockInsuficienteException;

/**
 * Classe que representa um produto no sistema.
 */
public class Produto implements Serializable {
    private String nome;
    private double preco;
    private int stock;

    public Produto(String nome, double preco, int stock) {
        this.nome = nome;
        this.preco = preco;
        this.stock = stock;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void reduzirStock(int quantidade) throws StockInsuficienteException {
        if (quantidade > stock) {
            throw new StockInsuficienteException("Stock insuficiente para o produto " + nome);
        }
        stock -= quantidade;
    }
    public void setPreco(double preco) {
        if(preco > 0) this.preco = preco;
    }
    public void reporStock(int quantidade) {
        stock += quantidade;
    }
}