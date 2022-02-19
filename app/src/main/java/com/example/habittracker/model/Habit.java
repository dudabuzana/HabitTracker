package com.example.habittracker.model;

public class Habit {
    private String  idhabito;
    private String  nome;
    private String  descricao;
    private boolean ativo;
    private String usuario; //id
    private String horario;

    public Habit(String nome, String descricao, boolean ativo, String horario, String usuario) {
        this.nome      = nome;
        this.descricao = descricao;
        this.horario     = horario;
        this.ativo     = ativo;
        this.usuario   = usuario;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
