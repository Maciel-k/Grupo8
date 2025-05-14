package org.example.model.pessoa;

import java.io.Serializable;

/**
 * Classe abstrata que representa uma pessoa no sistema.
 */
public abstract class Pessoa implements Serializable {
    private String nome;
    private int numeroIdentificacao;
    private String curso;
    private String password;
    private String instituicao;

    public Pessoa(String nome, int numeroIdentificacao, String curso, String password, String instituicao) {
        this.nome = nome;
        this.numeroIdentificacao = numeroIdentificacao;
        this.curso = curso;
        this.password = password;
        this.instituicao = instituicao;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public int getNumeroIdentificacao() {
        return numeroIdentificacao;
    }

    public String getCurso() {
        return curso;
    }

    public String getPassword() {
        return password;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public abstract String getTipo();
}