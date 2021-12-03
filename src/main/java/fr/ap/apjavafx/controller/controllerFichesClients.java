package fr.ap.apjavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class controllerFichesClients {

    @FXML private Button btnQuitter;
    public void buttonCloseListeFichesComptableClick(ActionEvent e) {
        Stage stage = (Stage) btnQuitter.getScene().getWindow();
        stage.close();
    }
}
