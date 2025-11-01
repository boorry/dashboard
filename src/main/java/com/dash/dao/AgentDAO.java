package com.dash.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.dash.model.Agent;
import java.sql.*;

public class AgentDAO {

    public Agent login (String login, String password) {
        String sql = "SELECT * FROM agents WHERE login = ? AND password = ?";
        
        DatabaseConnection connect = new DatabaseConnection();
         
        try (Connection conn = connect.getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(sql);
             
            pstmt.setString(1, login);
            pstmt.setString(2, password); // À hasher plus tard !
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Agent agent = new Agent();
                agent.setId(rs.getInt("id"));
                agent.setLogin(rs.getString("login"));
                agent.setNom(rs.getString("nom"));
                agent.setPrenom(rs.getString("prenom"));
                return agent;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Échec
    }

    public AgentDAO () {}
}
