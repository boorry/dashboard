package com.dash.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Activite{
    private int id;
    private int agentId;
    private int statutId;
    private LocalDateTime debut;
    private LocalDateTime fin;

    private String debutStr, finStr, statut, duree;
    private String libelle;

    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm:ss");


    public int getId (){
        return id;
    }

    public void setId (int id){
        this.id = id;
    }

    public int getAgentId (){
        return agentId;
    }

    public void setAgentId (int agentId){
        this.agentId = agentId;
    }

    public int getStatutId () {
        return statutId;
    }

    public void setStatutId (int statutId){
        this.statutId = statutId;
    }

    public LocalDateTime getDebut (){
        return debut;
    }

    public void setDebut (LocalDateTime debut){
        this.debut = debut;
    }

    public LocalDateTime getFin (){
        return fin;
    } 

    public void setFin (LocalDateTime fin) {
        this.fin = fin;
    }
   

    public String dateFormatter (LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return date.format(formatter);
    }

    @Override
    public String toString (){
        return agentId + " " + statutId + " " + dateFormatter(debut) + " au " + dateFormatter(fin);    
    }
    
    public String getDebutStr_OLD() { 
        return formatTime(debut); 
    }

    public String getFinStr_OLD() { 
        return fin != null ? formatTime(fin) : "En cours"; 
    }

    public String getLibelle() { 
        return libelle; 
    }

    public void setLibelle(String libelle) { 
        this.libelle = libelle; 
    }

    public String getDebutStr() { 
        return debut.format(TIME_FMT); 
    }

    public String getFinStr() { 
        return fin != null ? fin.format(TIME_FMT) : "En cours"; 
    }

    public String getStatut() { 
        return libelle; 
    }

    public String getDuree() {
        LocalDateTime end = fin != null ? fin : LocalDateTime.now();
        long seconds = java.time.Duration.between(debut, end).getSeconds();
        long h = seconds / 3600;
        long m = (seconds % 3600) / 60;
        long s = seconds % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    public String getDuree_OLD() {
        long seconds = java.time.Duration.between(debut, fin != null ? fin : LocalDateTime.now()).getSeconds();
        long h = seconds / 3600;
        long m = (seconds % 3600) / 60;
        long s = seconds % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    private String formatTime(LocalDateTime dt) {
        return dt.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
    }



    public Activite (){

    }
}
