package com.example.habittracker.model;

public class HabitInterno {
    private String  idhabito;
    private String  nome;
    private String  descricao;
    private String  icone;
    private boolean ativo;
    private String cor;
    private User usuario; //id

    public HabitInterno(String nome, String icone, String descricao, boolean ativo, String cor, User usuario) {
        this.nome      = nome;
        this.descricao = descricao;
        this.icone     = icone;
        this.ativo     = ativo;
        this.cor       = cor;
        this.usuario   = usuario;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
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
