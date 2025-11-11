package com.dash.controller;

import com.dash.dao.AgentDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.dash.model.Agent;

import java.io.IOException;

public class LoginController {

    @FXML private TextField txtLogin;
    @FXML private PasswordField txtPassword;
    @FXML private Button btnLogin;
    @FXML private Label lblError;

    private AgentDAO agentDAO = new AgentDAO();

    @FXML
    private void handleLogin() {
        String login = txtLogin.getText().trim();
        String password = txtPassword.getText();
        
        System.out.println("=== DEBUG LOGIN ===");
        System.out.println("Login tapé : '" + login + "'");
        System.out.println("Password tapé : '" + password + "'");
        System.out.println("Longueur password : " + password.length());
        System.out.println("===================");

        if (login.isEmpty() || password.isEmpty()) {
            lblError.setText("Veuillez remplir tous les champs.");
            return;
        }

        Agent agent = agentDAO.login(login, password);

        if (agent != null) {
            openDashboard(agent);
        } else {
            lblError.setText("Login ou mot de passe incorrect.");
        }
    }

    private void openDashboard(Agent agent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard.fxml"));
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            Scene scene = new Scene(loader.load());

            DashboardController controller = loader.getController();
            controller.setAgent(agent); // Passe l'agent au dashboard

            stage.setScene(scene);
            stage.setTitle("Dashboard - " + agent.getNom());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }







}
