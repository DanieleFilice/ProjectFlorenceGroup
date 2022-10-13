package com.filicedaniele.projectFG.Data;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;


public class UserRequest {

    @NotNull
    private String nome;
    @NotNull
    private String cognome;
    @NotNull
    private String email;

    public UserRequest() {
    }

    public UserRequest(String nome, String cognome, String email) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }

    @JsonProperty("nome")
    public String getNome() {
        return nome;
    }

    @JsonProperty("nome")
    public void setNome(String nome) {
        this.nome = nome;
    }

    @JsonProperty("cognome")
    public String getCognome() {
        return cognome;
    }

    @JsonProperty("cognome")
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

}
