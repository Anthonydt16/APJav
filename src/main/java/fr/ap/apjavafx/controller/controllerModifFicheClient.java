package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.Main;
import fr.ap.apjavafx.lib.AutoCompleteBox;
import fr.ap.apjavafx.model.DTO.FicheClient;
import fr.ap.apjavafx.model.DTO.LoueurDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.SQLException;

import static fr.ap.apjavafx.model.DAO.LoueurDAO.loueurByFicheClient;

public class controllerModifFicheClient {

    @FXML private Button btnEnregistrerClient;
    @FXML private Button btnQuitter;
    @FXML private TextField inputNom;
    @FXML private TextField inputAdresse;
    @FXML private ComboBox cbxVille;
    @FXML private Button btnVilleDisplay;
    @FXML private Button btnAjoutUneVille;
    @FXML private ComboBox cbxPays;
    @FXML private Button btnRemovePays;
    @FXML private TextField inputTel;
    @FXML private TextField inputEmailEnt;
    @FXML private TextField inputNomContact;
    @FXML private TextField inputPrenomContact;
    @FXML private TextField inputTelContact;
    @FXML private TextField inputEmailContact;
    @FXML private CheckBox ChxContacter;
    @FXML private ChoiceBox DplTypeInscription;
    @FXML private Text txtErreur;
    public FicheClient uneNouvelleFC;
    LoueurDTO unLoueur;



    @FXML
    public void initialize(){
        txtErreur.setVisible(false);
    }

    public void setLoueur(FicheClient unFicheClient) throws SQLException {
        uneNouvelleFC = unFicheClient;
        unLoueur = loueurByFicheClient(uneNouvelleFC);
//        inputNom.textProperty().set("Salut");
//        inputNom.setText(unLoueur.getNom());
//        inputAdresse.setText(unLoueur.getAdresse());
//        cbxPays.setValue(unLoueur.getPays());
    }

    public void remplirForm(){
        inputNom.setText(unLoueur.getNom());
        inputAdresse.setText(unLoueur.getAdresse());
    }

    @FXML
    public void OnQuitter() throws IOException{
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(Main.class.getResource("/fxml/view-commerciaux-fiches-clients.fxml"));
        Pane FicheClientLayout = (Pane) loader1.load();
        Stage FicheClientStage = new Stage();
        FicheClientStage.getIcons().add(new Image("/image/meetingBooking.png"));
        Scene ConnectScene = new Scene(FicheClientLayout);
        FicheClientStage.setScene(ConnectScene);

        Stage stage = (Stage) btnQuitter.getScene().getWindow();
        stage.close();

        FicheClientStage.setTitle("Commerciaux - Liste des fiches clients");
        FicheClientStage.initModality(Modality.APPLICATION_MODAL);
        FicheClientStage.show();
    }

}
