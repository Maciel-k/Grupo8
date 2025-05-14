package org.example.model;


import model.sistema.Classificavel;
import org.example.model.pessoa.Voluntario;
import org.example.model.exceptions.AssociacaoInvalidaException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma barraca no sistema.
 */
public class Barraca implements Serializable, Classificavel {
    private String nome;
    private String instituicao;
    private List<Produto> produtos;
    private List<Voluntario> voluntarios;
    private double vendasTotais;
    private int stockFinalDiario;
    private List<model.Venda> historicoVendas;

    public Barraca(String nome, String instituicao) {
        this.nome = nome;
        this.instituicao = instituicao;
        this.produtos = new ArrayList<>();
        this.voluntarios = new ArrayList<>();
        this.vendasTotais = 0.0;
        this.stockFinalDiario = 0;
        this.historicoVendas = new ArrayList<>();
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public List<Voluntario> getVoluntarios() {
        return voluntarios;
    }

    public double getVendasTotais() {
        return vendasTotais;
    }

    public void adicionarVenda(double valor) {
        this.vendasTotais += valor;
    }

    public int getStockFinalDiario() {
        return stockFinalDiario;
    }

    public void setStockFinalDiario(int stockFinalDiario) {
        this.stockFinalDiario = stockFinalDiario;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void adicionarVoluntario(Voluntario voluntario) throws AssociacaoInvalidaException {
        if (!voluntario.getInstituicao().equals(this.instituicao)) {
            throw new AssociacaoInvalidaException("O voluntário não pertence à mesma instituição da barraca.");
        }

        if (voluntario.getBarracaAssociada() != null) {
            throw new AssociacaoInvalidaException("O voluntário já está associado a outra barraca.");
        }

        voluntarios.add(voluntario);
        voluntario.setBarracaAssociada(this);
    }
    private double totalVendas;
    private List<model.Venda> historicoVendas;

    // ... outros campos e métodos

    public void registrarVenda(model.Venda venda) {
        this.totalVendas += venda.getValorTotal();
        this.historicoVendas.add(venda);
    }


    @Override
    public String getClassificacao() {
        if (stockFinalDiario > 100) {
            return "Bronze";
        } else if (stockFinalDiario >= 50) {
            return "Prata";
        } else {
            return "Ouro";
        }
    }
}