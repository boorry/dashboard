// src/main/java/com/dash/dao/StatutDAO.java

package com.dash.dao;

import com.dash.model.Statut;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatutDAO {

    public List<Statut> getAll() {
        List<Statut> list = new ArrayList<>();
        String sql = "SELECT id, libelle FROM statuts ORDER BY id";
        DatabaseConnection connect = new DatabaseConnection();
        try (Connection c = connect.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Statut s = new Statut(rs.getInt("id"), rs.getString("libelle"));
                list.add(s);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public Statut getByLibelle(String libelle) {
        String sql = "SELECT id, libelle FROM statuts WHERE libelle = ?";
        DatabaseConnection connect = new DatabaseConnection();
        try (Connection c = connect.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, libelle);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return new Statut(rs.getInt("id"), rs.getString("libelle"));
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}
