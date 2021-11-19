package fr.ap.apjavafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class controllerConnexion implements Initializable {


    /**
     * Les variables du fichier FXML associ�
     */
    @FXML private Label textLogin;
    @FXML private Label TextPassword;
    @FXML private TextField inputLogin;
    @FXML private TextField inputPassword;
    @FXML private Button btnValider;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
