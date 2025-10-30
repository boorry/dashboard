package com.dash;
import com.dash.dao.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        DatabaseConnection connect = new DatabaseConnection();
        
        try( Connection connection = connect.getConnection()){
             System.out.println("Connected successfully!");
         } catch(SQLException e){
             System.err.println("Connection failed: " + e.getMessage());
         }


       // connect.getConnection();
    }
}
