package fr.ap.apjavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class controllerAccueil implements Initializable {
    /**
     * Les variables du fichier FXML associ�
     */
    @FXML
    private Label textTitre;
    @FXML private Button inputConnex;
    @FXML private Button inputInscipt;

    @FXML	protected void clickConnex(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/view-Connexion.fxml"));
    }



    @Override

    public void initialize(URL location, ResourceBundle resources) {

    }
}
