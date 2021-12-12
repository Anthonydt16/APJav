package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class controllerAccueil implements Initializable {
    /**
     * Les variables du fichier FXML associ�
     */
    @FXML private AnchorPane Accueil;
    @FXML
    private Label textTitre;
    @FXML private Button inputConnex;
    @FXML private Button inputInscipt;
    private Pane rootLayout;
    @FXML protected void clickConnex(ActionEvent e) throws IOException {
        Accueil.setManaged(false);
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(Main.class.getResource("/fxml/view-Connexion.fxml"));
        Pane ConnexionLayout = (Pane) loader1.load();
        Stage ConnexionStage = new Stage();
        Scene ConnectScene = new Scene(ConnexionLayout);
        ConnexionStage.setScene(ConnectScene);

        ConnexionStage.setTitle("Connexion");
        ConnexionStage.initModality(Modality.APPLICATION_MODAL);
        ConnexionStage.show();
    }

    @FXML	protected void clickInscription(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/fxml/view-Inscription.fxml"));
        Pane InscriptionLayout = (Pane) loader.load();
        Stage InscriptionStage = new Stage();
        Scene InscriptionScene = new Scene(InscriptionLayout);
        InscriptionStage.setScene(InscriptionScene);

        InscriptionStage.setTitle("Inscription");
        InscriptionStage.initModality(Modality.APPLICATION_MODAL);
        InscriptionStage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
