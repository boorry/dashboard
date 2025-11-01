package com.dash.dao;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Activite{
    private int id;
    private int agentID;
    private int statutID;
    private LocalDateTime debut;
    private LocalDateTime fin;

    public int getID (){
        return id;
    }

    public void setID (){
        this.id = id;
    }

    public int getAgentID (){
        return agentID;
    }

    public void setAgentID (){
        this.agentID = agentID;
    }

    public int getStatutID () {
        return statutID;
    }

    public void setStatutID (){
        this.statutID = statutID;
    }

    public LocalDateTime getDebut (){
        return debut;
    }

    public void setDebut (){
        this.debut = debut;
    }

    public LocalDateTime getFin (){
        return fin;
    } 

    public void setFin () {
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
