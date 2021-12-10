package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.Main;
import fr.ap.apjavafx.model.DAO.FicheClientDAO;
import fr.ap.apjavafx.model.DAO.LoueurDAO;
import fr.ap.apjavafx.model.DTO.FicheClient;
import fr.ap.apjavafx.model.DTO.loueur;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class controllerFichesClients {

    @FXML private Button btnQuitter;
    @FXML private  Button btnAjouter;

    @FXML private TableView<String> tableListeLoueur;
    @FXML private TableColumn<FicheClient, String> LoginLoueur;
    @FXML private TableColumn<FicheClient, String> nomLoueur;
    @FXML private TableColumn<FicheClient, String> contacterLoueur;
    @FXML private TableColumn<FicheClient, String> typeInscriptionLoueur;
    @FXML private TableColumn<FicheClient, String> mailLoueur;
    @FXML private TableColumn<FicheClient, String> telLoueur;

    private void remplirTableau() throws SQLException {

        ObservableList<FicheClient> data = FXCollections.observableArrayList();
        //TODO FAIRE SUPP CA ET CONTINUER

        ArrayList<FicheClient> LesClients = FicheClientDAO.getAllLoueur();

        while (LesClients.next()) {
            FicheClient UneFicheClient = new FicheClient(LesClients.getString(1) , LesClients.getString(2) ,
                    LesClients.getString(3) ,LesClients.getString(4)  ,
                    LesClients.getString(5)  ,LesClients.getString(6)  ,
                    LesClients.getString(7) , LesClients.getString(8));
            data.add(UneFicheClient);

        }
    }

    @FXML
    public void initialize() throws SQLException {
        remplirTableau();
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
