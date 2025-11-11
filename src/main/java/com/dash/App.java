package com.dash;
import com.dash.dao.*;
import com.dash.model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application
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
        
        launch(args);

       // connect.getConnection();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Login - Dashboard Productivité");
        stage.setResizable(false);
        stage.show();
    }


}
