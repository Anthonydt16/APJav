package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.model.DTO.SalleDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import static fr.ap.apjavafx.model.DAO.SalleDAO.modificationSalle;

public class ControllerModificationUneSalle {
    @FXML private Button btnValiderSaisiSalle;
    @FXML private TextField inputNomSalle;
    @FXML private TextField inputLongueur;
    @FXML private TextField inputLargeur;
    @FXML private TextField inputSurface;
    @FXML private TextField inputHauteur;
    @FXML private TextField inputCapacite;

    private SalleDTO uneSalle;

    public SalleDTO getSalle() {
        return uneSalle;
    }

    public void setSalle(SalleDTO uneSalle) {
        this.uneSalle = uneSalle;
    }


    public void setup() {
        inputCapacite.setText(String.valueOf(uneSalle.getCapacite()));
        inputHauteur.setText(String.valueOf(uneSalle.getHauteur()));
        inputLargeur.setText(String.valueOf(uneSalle.getLargeur()));
        inputSurface.setText(String.valueOf(uneSalle.getSurface()));
        inputNomSalle.setText(uneSalle.getNomSalle());
        inputLongueur.setText(String.valueOf(uneSalle.getLongueur()));
    }
    public void btnValider(ActionEvent actionEvent) {


        modificationSalle(uneSalle.getIdSalle(),inputNomSalle.getText(), Double.valueOf(inputLongueur.getText()) , Double.valueOf(inputLongueur.getText()),Double.valueOf(inputSurface.getText()),Double.valueOf(inputHauteur.getText()),Double.valueOf(inputCapacite.getText()));
        System.out.println("btn envoie clicker");
    }
}
