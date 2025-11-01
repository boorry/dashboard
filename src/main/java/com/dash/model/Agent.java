package com.dash.model;

public class Agent {
    private int id;
    private String login;
    private String nom;
    private String prenom;

    public int getId() { 
        return id; 
    }

    public void setId(int id) { 
        this.id = id; 
    }

    public String getLogin() { 
        return login; 
    }

    public void setLogin(String login) { 
        this.login = login; 
    }

    public String getNom() { 
        return nom; 
    }

    public void setNom(String nom) { 
        this.nom = nom; 
    }

    public String getPrenom() { 
        return prenom; 
    }

    public void setPrenom(String prenom) { 
        this.prenom = prenom; 
    }

    @Override
    public String toString() {
        return prenom + " " + nom + " (" + login + ")";
    }

    public Agent(){

    }
}
