package fr.ap.apjavafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;


public class controllerSalleUnLieu implements Initializable {

    @FXML private Text textSalle;
    @FXML private TextField inputSalle;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inputSalle.setText(String.valueOf(controllerAfficherSalle.getMyVariable()));
    }
}
