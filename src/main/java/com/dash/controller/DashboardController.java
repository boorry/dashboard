package com.dash.controller;

import com.dash.model.Agent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML private Label lblWelcome;
    @FXML private Label lblCurrentStatus;
    @FXML private Label lblTimer;

    private Agent agent;

    public void setAgent(Agent agent) {
        this.agent = agent;
        lblWelcome.setText("Bienvenue, " + agent.getPrenom() + " !");
        lblCurrentStatus.setText("Hors ligne");
        startTimer();
    }

    @FXML private void handleLogout() {
        // Retour au login (à implémenter plus tard)
        System.out.println("Déconnexion...");
    }

    @FXML private void setStatusDisponible() { updateStatus("DISPONIBLE"); }
    @FXML private void setStatusEnAppel()     { updateStatus("EN_APPEL"); }
    @FXML private void setStatusEnPause()     { updateStatus("EN_PAUSE"); }
    @FXML private void setStatusHorsLigne()   { updateStatus("HORS_LIGNE"); }

    private void updateStatus(String status) {
        lblCurrentStatus.setText(status);
        // À connecter à la DB plus tard
    }

    private void startTimer() {
        // Simulation de timer - à revoir
        new Thread(() -> {
            int seconds = 0;
            while (true) {
                int h = seconds / 3600;
                int m = (seconds % 3600) / 60;
                int s = seconds % 60;
                String time = String.format("%02d:%02d:%02d", h, m, s);
                javafx.application.Platform.runLater(() -> lblTimer.setText(time));
                try { Thread.sleep(1000); } catch (Exception e) {}
                seconds++;
            }
        }).start();
    }
}
