package com.example.agenda_dmos5.model;

import androidx.annotation.NonNull;

public class Contato {
    private Integer id;
    private String nome;
    private String telefone;
    private String celular;
    private String email;

    public Contato(String nome, String telefone, String celular, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.celular = celular;

    }

    public Contato(int s,String nome, String telefone, String celular, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.celular = celular;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {
        return getNome();
    }

}
