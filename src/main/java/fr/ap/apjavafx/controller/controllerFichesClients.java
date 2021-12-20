package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.Main;
import fr.ap.apjavafx.model.DAO.EntrepriseDAO;
import fr.ap.apjavafx.model.DAO.FicheClientDAO;
import fr.ap.apjavafx.model.DAO.LoueurDAO;
import fr.ap.apjavafx.model.DTO.Entreprise;
import fr.ap.apjavafx.model.DTO.FicheClient;
import fr.ap.apjavafx.model.DTO.LoueurDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static fr.ap.apjavafx.model.DAO.LoueurDAO.loueurByFicheClient;

public class controllerFichesClients {

    @FXML private  Button btnAjouter;
    @FXML private Button btnSupprimer;
    @FXML private Button btnModifier;
    @FXML private Text txtErreur;
    @FXML private  TableView<FicheClient> tableListeClient;
    @FXML private  TableColumn<FicheClient, String> collNomEnt;
    @FXML private  TableColumn<FicheClient, String> collAdEnt;
    @FXML private  TableColumn<FicheClient, String> colVillePays;
    @FXML private  TableColumn<FicheClient, String> collTel;
    @FXML private  TableColumn<FicheClient, String> colEmail;
    @FXML private  TableColumn<FicheClient, String> colPrenomNom;
    @FXML private  TableColumn<FicheClient, String> colMailContact;
    @FXML private  TableColumn<FicheClient, String> colTelContact;
    private static LoueurDTO unLoueur;

    public void remplirTableau() {

        ObservableList<FicheClient> data = FXCollections.observableArrayList();

        ArrayList<FicheClient> LesClients = FicheClientDAO.getAllLoueur();

        for(FicheClient unClients: LesClients){
            data.add(unClients);
        }

        collNomEnt.setCellValueFactory(new PropertyValueFactory<FicheClient,String>("nomEnt"));
        collAdEnt.setCellValueFactory(new PropertyValueFactory<FicheClient, String>("adresseEnt"));
        colVillePays.setCellValueFactory(new PropertyValueFactory<FicheClient, String>("VillePays"));
        collTel.setCellValueFactory(new PropertyValueFactory<FicheClient, String>("telEnt"));
        colEmail.setCellValueFactory(new PropertyValueFactory<FicheClient, String>("emailEnt"));
        colPrenomNom.setCellValueFactory(new PropertyValueFactory<FicheClient, String>("nomPrenomContact"));
        colMailContact.setCellValueFactory(new PropertyValueFactory<FicheClient, String>("mailContact"));
        colTelContact.setCellValueFactory(new PropertyValueFactory<FicheClient, String>("telContact"));


        tableListeClient.setItems(data);
    }

    @FXML
    public void initialize(){
        txtErreur.setVisible(false);
        remplirTableau();
    }

    public void buttonAjouterClients(ActionEvent e) throws IOException {
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(Main.class.getResource("/fxml/view-ajout-fiches-clients.fxml"));
        Pane ConnexionLayout = (Pane) loader1.load();
        Stage ConnexionStage = new Stage();
        Scene ConnectScene = new Scene(ConnexionLayout);
        ConnexionStage.setScene(ConnectScene);

        Stage thisOne = (Stage) btnAjouter.getScene().getWindow();
        thisOne.close();
        ConnexionStage.setTitle("Commerciaux - fiches clients");
        ConnexionStage.initModality(Modality.APPLICATION_MODAL);
        ConnexionStage.show();
    }

    @FXML
    private void OnRemoveItems(ActionEvent e) throws IOException {
        FicheClient unFicheClient = tableListeClient.getSelectionModel().getSelectedItem();
        if(unFicheClient != null){
            int numEnt = (int) FicheClientDAO.getIdEnt(unFicheClient);
            LoueurDAO.deleteLoueur(numEnt);
            EntrepriseDAO.deleteEnt(numEnt);
            //TODO Supprimer de la table "contacter"
            txtErreur.setVisible(true);
            txtErreur.setFill(Color.GREEN);
            txtErreur.setText("Succès : le loueur a été supprimé");
            remplirTableau();
        }
        else {
            txtErreur.setVisible(true);
        }
    }

    @FXML
    private void OnModifClient(ActionEvent e) throws IOException, SQLException {
        FicheClient unFicheClient = tableListeClient.getSelectionModel().getSelectedItem();
        if(unFicheClient != null){
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(Main.class.getResource("/fxml/view-modifier-fiche-client.fxml"));
            Pane ConnexionLayout = (Pane) loader1.load();
            Stage ConnexionStage = new Stage();
            ConnexionStage.getIcons().add(new Image("/image/meetingBooking.png"));

            controllerModifFicheClient controller = new controllerModifFicheClient();
            loader1.setController(controller);

            Scene ConnectScene = new Scene(ConnexionLayout);
            ConnexionStage.setScene(ConnectScene);

            Stage thisOne = (Stage) btnModifier.getScene().getWindow();
            thisOne.close();

            ConnexionStage.setUserData(unFicheClient);
            ConnexionStage.setTitle("Commerciaux - fiches clients");
            ConnexionStage.initModality(Modality.APPLICATION_MODAL);
            ConnexionStage.show();

            controller.setLoueur(unFicheClient);
        }
        else{
            txtErreur.setVisible(true);
        }
    }
}
