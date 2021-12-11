package com.example.habittracker.model;

public class RecuperarSenhaJSON {
    private String senha;
    private String novaSenha;
    private String confirmaSenha;

    public RecuperarSenhaJSON(String senha, String novaSenha, String confirmaSenha) {
        this.senha = senha;
        this.novaSenha = novaSenha;
        this.confirmaSenha = confirmaSenha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }
}
