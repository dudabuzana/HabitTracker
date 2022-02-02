package com.example.habittracker.model;

public class Habit {
    private String  idhabito;
    private String  nome;
    private String  descricao;
    private String  icone;
    private boolean ativo;

    public Habit(String nome, String icone, String descricao, boolean ativo) {
        this.nome      = nome;
        this.descricao = descricao;
        this.icone     = icone;
        this.ativo     = ativo;
    }

    public String getIdhabito() {
        return idhabito;
    }

    public String getId() {
        return idhabito;
    }

    public void setId(String id) {
        this.idhabito = idhabito;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String foto) {
        this.icone = foto;
    }
}
