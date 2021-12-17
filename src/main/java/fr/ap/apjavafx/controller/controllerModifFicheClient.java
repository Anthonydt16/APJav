package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.model.DTO.FicheClient;
import fr.ap.apjavafx.model.DTO.LoueurDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

    public void setLoueur(FicheClient unFicheClient) throws SQLException {
        uneNouvelleFC = unFicheClient;
        if(uneNouvelleFC == null){
            System.out.println("Nop y a r");
        }
        //TODO faire la bonne requête
        unLoueur = loueurByFicheClient(uneNouvelleFC);
        if(unLoueur == null){
            System.out.println("Aie aie aie");
        }

//        System.out.println(uneNouvelleFC.getNomEnt());
//        ;
//        inputAdresse.setText(uneNouvelleFC.getAdresseEnt());
    }

}
