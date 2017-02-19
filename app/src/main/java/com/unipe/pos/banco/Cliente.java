package com.unipe.pos.banco;

/**
 * Created by alexmartins on 18/02/17.
 */

public class Cliente {

    private String CPF;
    private String nome;
    private String email;
    private String senha;

    public Cliente(String CPF, String nome, String email,String senha){
        this.CPF = CPF;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
