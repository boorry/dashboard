package com.dash.controller;

//import com.dash.dao.DatabaseConnection;
//import com.dash.dao.ActiviteDAO;
//import com.dash.dao.StatutDAO;
import com.dash.dao.*;
import java.util.*;
import com.dash.model.Activite;
import com.dash.model.Agent;
import com.dash.model.Statut;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableView;
import java.io.FileWriter;
import java.time.LocalDateTime;

import javafx.scene.control.Alert;
import javafx.application.Platform;

public class DashboardController {

    @FXML private Label lblWelcome;
    @FXML private Label lblCurrentStatus;
    @FXML private Label lblTimer;

    @FXML private TableView<Activite> tableHistorique;
    @FXML private PieChart pieChart;
    private ObservableList<Activite> historiqueData = FXCollections.observableArrayList();

    private Agent agent;
    private ActiviteDAO activiteDAO = new ActiviteDAO();
    private StatutDAO statutDAO = new StatutDAO();
    private Timeline timelinie;
    private DatabaseConnection connect = new DatabaseConnection();

    public void setAgent(Agent agent) {
        this.agent = agent;
        lblWelcome.setText("Bienvenue, " + agent.getPrenom() + " !");
        lblCurrentStatus.setText("Hors ligne");
        startTimer();

        historiqueData.setAll(activiteDAO.getHistoriqueAujourdHui(agent.getId()));
        tableHistorique.setItems(historiqueData);
        updateChart();
    }

    private void updateChart() {
        Map<String, Long> temps = activiteDAO.getTempsParStatut(agent.getId());
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
        temps.forEach((libelle, secondes) -> {
            double heures = secondes / 3600.0;
            pieData.add(new PieChart.Data(libelle + " (" + String.format("%.1f", heures) + "h)", heures));
        });
        pieChart.setData(pieData);
    }

    @FXML
    private void exportCSV() {
        try (FileWriter writer = new FileWriter("rapport_" + agent.getLogin() + "_" + LocalDateTime.now() + ".csv")) {
            writer.write("Début,Fin,Statut,Durée\n");
            for (Activite a : historiqueData) {
                writer.write(a.getDebutStr() + "," + a.getFinStr() + "," + a.getStatut() + "," + a.getDuree() + "\n");
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Exporté avec succès !");
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void handleLogout() {
        // Retour au login (à bien implémenter)
        System.out.println("Déconnexion...");
        // Optionnel : terminer l'activité avant logout
        activiteDAO.terminerActiviteEnCours(agent.getId());
        System.exit(0);
    }

    @FXML private void setStatusDisponible() { updateStatus("DISPONIBLE"); }
    @FXML private void setStatusEnAppel()     { updateStatus("EN_APPEL"); }
    @FXML private void setStatusEnPause()     { updateStatus("EN_PAUSE"); }
    @FXML private void setStatusHorsLigne()   { updateStatus("HORS_LIGNE"); }

    private void updateStatus(String status) {
        lblCurrentStatus.setText(status);
        // À connecter à la DB plus tard
    }

    private void chargerStatutActuel() {
        Activite actuelle = activiteDAO.getActiviteEnCours(agent.getId());
        if (actuelle != null) {
            Statut s = statutDAO.getByLibelle(null); // on récupère via jointure plus simple
            String sql = "SELECT libelle FROM statuts WHERE id = (SELECT statut_id FROM activites WHERE id = ?)";
            // … ou plus simple :
            lblCurrentStatus.setText(getLibelleFromActivite(actuelle));
        } else {
            lblCurrentStatus.setText("HORS_LIGNE");
        }
    }

    private String getLibelleFromActivite(Activite a) {
        try (var c = connect.getConnection();
             var ps = c.prepareStatement("SELECT libelle FROM statuts WHERE id = ?")) {
            ps.setInt(1, a.getStatutId());
            var rs = ps.executeQuery();
            if (rs.next()) return rs.getString(1);
        } catch (Exception e) { e.printStackTrace(); }
        return "INCONNU";
    }

    private void changerStatut(String libelle) {
        Statut statut = statutDAO.getByLibelle(libelle);
        if (statut == null) return;

        // 1. On termine l'ancienne activité
        activiteDAO.terminerActiviteEnCours(agent.getId());

        // 2. On crée la nouvelle
        activiteDAO.creerActivite(agent.getId(), statut.getId());

        // 3. UI
        lblCurrentStatus.setText(libelle);

        // 4. Rafraîchit historique + graphique
        Platform.runLater(() -> {
            historiqueData.setAll(activiteDAO.getHistoriqueAujourdHui(agent.getId()));
            updateChart();
        });
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

