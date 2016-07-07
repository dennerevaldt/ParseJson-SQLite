package br.unisinos.tarefa02rr.models;

import java.io.Serializable;

/**
 * Created by dennerevaldtmachado on 06/07/16.
 */
public class User implements Serializable {
    private String id;
    private String name;
    private String username;
    private String email;

    /**
     * Construtor
     */
    public User(String id, String name, String username, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
    }

    /**
     * Getters e Setters
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
