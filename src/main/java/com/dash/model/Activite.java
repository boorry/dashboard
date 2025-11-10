package com.dash.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Activite{
    private int id;
    private int agentID;
    private int statutID;
    private LocalDateTime debut;
    private LocalDateTime fin;

    public int getId (){
        return id;
    }

    public void setId (int id){
        this.id = id;
    }

    public int getAgentId (){
        return agentID;
    }

    public void setAgentId (int agentID){
        this.agentID = agentID;
    }

    public int getStatutId () {
        return statutID;
    }

    public void setStatutId (int statutID){
        this.statutID = statutID;
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
        return agentID + " " + statutID + " " + dateFormatter(debut) + " au " + dateFormatter(fin);    
    }


    public Activite (){

    }
}
