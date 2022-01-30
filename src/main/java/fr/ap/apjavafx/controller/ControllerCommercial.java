package fr.ap.apjavafx.controller;

import javafx.application.Platform;
import fr.ap.apjavafx.Main;
import java.io.IOException;

import java.util.ArrayList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import fr.ap.apjavafx.model.DTO.Commercial;

import fr.ap.apjavafx.model.DAO.CommercialDAO;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;


public class ControllerCommercial {
    /**
     * Les variables du fichier FXML associ�
     */

    @FXML
    private TableView<Commercial> tableDetailCommerciaux=new TableView<>();
    @FXML
    private TableColumn<Commercial, String> colLogin=new TableColumn<>();
    @FXML
    private TableColumn<Commercial, Double> colPourcentage=new TableColumn<>();
    @FXML
    private TableColumn<Commercial, String> colNom=new TableColumn<>();
    @FXML
    private TableColumn<Commercial, String> colPrenom=new TableColumn<>();
    @FXML
    private TableColumn<Commercial, String> colAdresse=new TableColumn<>();
    @FXML
    private TableColumn<Commercial, String> colTelephone=new TableColumn<>();
    @FXML
    private TableColumn<Commercial, String> colMail=new TableColumn<>();

    @FXML private ObservableList<Commercial> data = FXCollections.observableArrayList();




    /**
     * M�thode associée � l'évênement click sur le bouton valider
     * @param e
     */

    @FXML	protected void buttonAjouterCommercialClick(ActionEvent e) throws IOException {
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(Main.class.getResource("/fxml/view-Inscription-Commercial.fxml"));
        Pane ConnexionLayout = (Pane) loader1.load();
        Stage ConnexionStage = new Stage();
        Scene ConnectScene = new Scene(ConnexionLayout);
        ConnexionStage.setScene(ConnectScene);

        ConnexionStage.setTitle("Inscription");
        ConnexionStage.initModality(Modality.APPLICATION_MODAL);
        ConnexionStage.show();
    }

    /**
     * Remplissage le la tableView "TableauCommerciaux"
     */
    public void remplirTableauCommerciaux(){
        CommercialDAO commercialDAO = new CommercialDAO();
        ArrayList<Commercial> desCommerciaux = new ArrayList<Commercial>();
        desCommerciaux = commercialDAO.lireCommerciaux();

        for(Commercial unCom : desCommerciaux ){
            data.add(unCom);

        }

        colLogin.setCellValueFactory(new PropertyValueFactory<Commercial,String>("login"));
        colPourcentage.setCellValueFactory(new PropertyValueFactory<Commercial,Double>("pourcentageCommercial"));
        colNom.setCellValueFactory(new PropertyValueFactory<Commercial,String>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<Commercial,String>("prenom"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<Commercial,String>("adresse"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<Commercial,String>("telephone"));
        colMail.setCellValueFactory(new PropertyValueFactory<Commercial,String>("mail"));

        tableDetailCommerciaux.setItems(data);
    }
    /**
     * M�thode associée � l'évênement click sur le bouton Quitter
     * @param e
     */

    @FXML	protected void buttonCloseCommercialClick(ActionEvent e) throws IOException {
        Platform.exit();
    }

    /**
     * M�thode associée � l'évênement click sur le bouton Modifier
     * @param e
     */
    @FXML	protected void buttonModifierClick(ActionEvent e) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/fxml/view-Modification-Commercial.fxml"));
        Pane InscriptionLayout = (Pane) loader.load();
        Stage InscriptionStage = new Stage();
        Scene InscriptionScene = new Scene(InscriptionLayout);
        loader.setController(ControllerCommercial.class);
        ControllerCommercial controllerCommercial = new ControllerCommercial();
        controllerCommercial.remplirTableauCommerciaux();

        InscriptionStage.setScene(InscriptionScene);
        InscriptionStage.setTitle("Modification Commercial");
        InscriptionStage.initModality(Modality.APPLICATION_MODAL);


        InscriptionStage.show();

    }
    @FXML	protected void buttonSupprimerClick(ActionEvent e) throws IOException {

    }
    @FXML	protected void buttonDetailClick(ActionEvent e) throws IOException {
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(Main.class.getResource("/fxml/view-Detail-Commercial.fxml"));
        Pane InscriptionLayout = (Pane) loader2.load();
        Stage InscriptionStage = new Stage();
        Scene InscriptionScene = new Scene(InscriptionLayout);
        loader2.setController(ControllerDetailCommerciaux.class);
        ControllerDetailCommerciaux ControllerDetailCommerciaux = new ControllerDetailCommerciaux();
        ControllerDetailCommerciaux.remplirTableauContactCommerciaux();

        InscriptionStage.setScene(InscriptionScene);
        InscriptionStage.setTitle("Detail contact commercial");
        InscriptionStage.initModality(Modality.APPLICATION_MODAL);


        InscriptionStage.show();
    }


}
