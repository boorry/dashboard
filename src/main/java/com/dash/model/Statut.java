package com.dash.model;

public class Statut{
    private int id;
    private String libelle;

    public int getID (){
        return id;
    }

    public void setID (){
        this.id = id;
    }

    public String getLibelle(){
        return libelle;
    }

    public void setLibelle (){
        this.libelle = libelle;
    }

    @Override
    public String toString(){
        return libelle;
    }

    public Statut (){ }

    public Statut(int id, String libelle){
        this.id = id;
        this.libelle = libelle;
    }
}
