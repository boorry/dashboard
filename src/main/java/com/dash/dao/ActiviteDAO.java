// src/main/java/com/dash/dao/ActiviteDAO.java
package com.dash.dao;

import com.dash.model.Activite;
import java.sql.*;
import java.time.LocalDateTime;

public class ActiviteDAO {
    DatabaseConnection connect = new DatabaseConnection();
    // Ferme l'activité en cours de l'agent
    public void terminerActiviteEnCours(int agentId) {
        String sql = "UPDATE activites SET fin = NOW() WHERE agent_id = ? AND fin IS NULL";
        try (Connection c = connect.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, agentId);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // Crée une nouvelle activité
    public void creerActivite(int agentId, int statutId) {
        String sql = "INSERT INTO activites (agent_id, statut_id) VALUES (?, ?)";
        try (Connection c = connect.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, agentId);
            ps.setInt(2, statutId);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // Récupère l'activité en cours (pour le timer)
    public Activite getActiviteEnCours(int agentId) {
        String sql = "SELECT a.*, s.libelle FROM activites a JOIN statuts s ON a.statut_id = s.id WHERE a.agent_id = ? AND a.fin IS NULL ";
        try (Connection c = connect.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, agentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Activite a = new Activite();
                a.setId(rs.getInt("id"));
                a.setAgentId(rs.getInt("agent_id"));
                a.setStatutId(rs.getInt("statut_id"));
                a.setDebut(rs.getTimestamp("debut").toLocalDateTime());
                return a;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}
