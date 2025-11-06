package com.dash.controller;

import com.dash.model.Agent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML private Label lblWelcome;

    private Agent agent;

    public void setAgent(Agent agent) {
        this.agent = agent;
        lblWelcome.setText("Bienvenue, " + agent.getPrenom() + " !");
    }
}
