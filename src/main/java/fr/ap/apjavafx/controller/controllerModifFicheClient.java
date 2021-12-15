package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.model.DTO.FicheClient;
import fr.ap.apjavafx.model.DTO.LoueurDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;

import static fr.ap.apjavafx.model.DAO.LoueurDAO.loueurByFicheClient;

public class controllerModifFicheClient {
    LoueurDTO unLoueur;

    public void setUser(FicheClient unFicheClient) throws SQLException {
        FicheClient uneNouvelleFC = unFicheClient;
        System.out.println(uneNouvelleFC.getNomEnt());
        unLoueur = loueurByFicheClient(uneNouvelleFC);
    }

    @FXML
    public void initialize(){

    }


}
