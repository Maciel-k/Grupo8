package org.example.model.pessoa;

/**
 * Classe que representa um voluntário de stock.
 */
public class VoluntarioStock extends Voluntario {
    public VoluntarioStock(String nome, int numeroIdentificacao, String curso, String password, String instituicao) {
        super(nome, numeroIdentificacao, curso, password, instituicao);
    }

    @Override
    public String getTipo() {
        return "Voluntário de Stock";
    }
}