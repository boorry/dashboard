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
    
    public String getDebutStr() { 
        return formatTime(debut); 
    }

    public String getFinStr() { 
        return fin != null ? formatTime(fin) : "En cours"; 
    }
/*
    public String getStatut() {
        try (var c = DatabaseConnection.getConnection();
             var ps = c.prepareStatement("SELECT libelle FROM statuts WHERE id = ?")) {
            ps.setInt(1, statutId);
            var rs = ps.executeQuery();
            return rs.next() ? rs.getString(1) : "Inconnu";
        } catch (Exception e) { return "Erreur"; }
    }
*/
    public String getDuree() {
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
