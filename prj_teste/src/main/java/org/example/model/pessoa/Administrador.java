package org.example.model.pessoa;

/**
 * Classe que representa um administrador do sistema.
 */
public class Administrador extends Pessoa {
    public Administrador(String nome, int numeroIdentificacao, String curso, String password, String instituicao) {
        super(nome, numeroIdentificacao, curso, password, instituicao);
    }

    @Override
    public String getTipo() {
        return "Administrador";
    }
}