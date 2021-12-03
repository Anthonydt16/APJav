package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.Main;
import fr.ap.apjavafx.model.DTO.loueur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class controllerFichesClients {

    @FXML private Button btnQuitter;
    @FXML private  Button btnAjouter;

    @FXML private TableView<loueur> tableListeLoueur;
    @FXML private TableColumn<loueur, String> LoginLoueur;
    @FXML private TableColumn<loueur, String> nomLoueur;
    @FXML private TableColumn<loueur, Boolean> contacterLoueur;
    @FXML private TableColumn<loueur, String> typeInscriptionLoueur;
    @FXML private TableColumn<loueur, String> mailLoueur;
    @FXML private TableColumn<loueur, Integer> telLoueur;


    private void remplirTableau() {
        ObservableList<loueur> data = FXCollections.observableArrayList();

    }

    public void buttonCloseListeFichesComptableClick(ActionEvent e) {
        Stage stage = (Stage) btnQuitter.getScene().getWindow();
        stage.close();
    }

    public void buttonAjouterClients(ActionEvent e) throws IOException {
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(Main.class.getResource("/fxml/view-ajout-fiches-clients.fxml"));
        Pane ConnexionLayout = (Pane) loader1.load();
        Stage ConnexionStage = new Stage();
        Scene ConnectScene = new Scene(ConnexionLayout);
        ConnexionStage.setScene(ConnectScene);

        ConnexionStage.setTitle("Commerciaux - fiches clients");
        ConnexionStage.initModality(Modality.APPLICATION_MODAL);
        ConnexionStage.show();
    }


}
