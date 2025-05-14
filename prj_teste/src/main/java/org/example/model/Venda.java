package model;

import org.example.model.Produto;
import org.example.model.pessoa.VoluntarioVendas;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Classe que representa uma venda no sistema.
 */
public class Venda implements Serializable {
    private Produto produto;
    private int quantidade;
    private double valorTotal;
    private LocalDateTime dataHora;
    private VoluntarioVendas voluntario;

    public Venda(Produto produto, int quantidade, VoluntarioVendas voluntario) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorTotal = produto.getPreco() * quantidade;
        this.dataHora = LocalDateTime.now();
        this.voluntario = voluntario;
    }

    // Getters
    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public VoluntarioVendas getVoluntario() {
        return voluntario;
    }
}