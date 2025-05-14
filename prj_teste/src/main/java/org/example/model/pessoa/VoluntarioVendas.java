package org.example.model.pessoa;

/**
 * Classe que representa um voluntário de vendas.
 */
public class VoluntarioVendas extends Voluntario {
    private double totalVendas;

    public VoluntarioVendas(String nome, int numeroIdentificacao, String curso, String password, String instituicao) {
        super(nome, numeroIdentificacao, curso, password, instituicao);
        this.totalVendas = 0.0;
    }

    public double getTotalVendas() {
        return totalVendas;
    }

    public void adicionarVenda(double valor) {
        this.totalVendas += valor;
    }

    public String getClassificacao() {
        if(totalVendas < 500) return "Bronze";
        else if(totalVendas < 1000) return "Prata";
        else return "Ouro";
    }
    @Override
    public String getTipo() {
        return "Voluntário de Vendas";
    }
}
