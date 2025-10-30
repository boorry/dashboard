package com.dash;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection{
    
    public Connection getConnection() throws SQLException{
        final String url = "jdbc:postgresql://localhost:5432/dashboard";
        final String user = "postgres";
        final String password = "jaolava22";
        Connection returConnect = null;
        try
        {
            returConnect = DriverManager.getConnection(url, user, password);
        } catch(SQLException e){
            System.err.println("Connection failed: " + e.getMessage());
        }
       // return DriverManager.getConnection(url, user, password);
       return returConnect;
    }


    public DatabaseConnection(){

    }


}
