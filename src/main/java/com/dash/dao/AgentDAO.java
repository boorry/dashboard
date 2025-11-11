package com.dash.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.dash.model.Agent;
import java.sql.*;

public class AgentDAO {

    public Agent login (String login, String password) {
        String sql = "SELECT * FROM agents WHERE login = ? AND password = ?";
        System.out.println(sql);

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
         
            pstmt.setString(1, login);
            pstmt.setString(2, password);
        
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Agent agent = new Agent();
                agent.setId(rs.getInt("id"));
                agent.setLogin(rs.getString("login"));
                agent.setNom(rs.getString("nom"));
                agent.setPrenom(rs.getString("prenom"));
                System.out.println("Connexion réussie pour " + agent.getPrenom());
                return agent;
            } else {
                System.out.println("Aucun agent trouvé avec ces identifiants.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AgentDAO () {}
}
