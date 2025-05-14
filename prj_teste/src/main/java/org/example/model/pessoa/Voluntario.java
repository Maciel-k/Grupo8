package org.example.model.pessoa;

import org.example.model.Barraca;

/**
 * Classe que representa um voluntário genérico.
 */
public abstract class Voluntario extends Pessoa {
    private Barraca barracaAssociada;
    private String diaEscala;

    public Voluntario(String nome, int numeroIdentificacao, String curso, String password, String instituicao) {
        super(nome, numeroIdentificacao, curso, password, instituicao);
    }

    public Barraca getBarracaAssociada() {
        return barracaAssociada;
    }

    public void setBarracaAssociada(Barraca barracaAssociada) {
        this.barracaAssociada = barracaAssociada;
    }

    public String getDiaEscala() {
        return diaEscala;
    }

    public void setDiaEscala(String diaEscala) {
        this.diaEscala = diaEscala;
    }
}