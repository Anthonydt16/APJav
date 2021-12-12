package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.lib.AutoCompleteBox;
import fr.ap.apjavafx.model.DAO.PaysDAO;
import fr.ap.apjavafx.model.DAO.VilleDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class controllerAjoutFichesClients {

    @FXML
    private Button btnEnregistrerClient;
    @FXML private TextField inputNom;
    @FXML private TextField inputAdresse;
    @FXML private TextField inputVille;
    @FXML private ComboBox cbxPays;
    @FXML private TextField inputTel;
    @FXML private TextField inputEmailEnt;
    @FXML private TextField inputNomContact;
    @FXML private TextField inputPrenomContact;
    @FXML private TextField inputTelContact;
    @FXML private TextField inputEmailContact;
    @FXML private CheckBox inputChxContacter;
    @FXML private ChoiceBox inputDplTypeInscription;
    @FXML private Text TxtErreur;
    ArrayList<String> LesPays = PaysDAO.getAllPaysName();

    @FXML
    protected void onEnregistrerClient(ActionEvent e){
        //Contrôle de saisie : NOT NULL
        if(inputNom.getText() != "" || inputAdresse.getText() != "" || inputVille.getText() != "" || inputTel.getText() != "" || inputEmailEnt.getText() != "" || inputNomContact.getText() != ""
                || inputPrenomContact.getText() != "" || inputTelContact.getText() != "" || inputEmailContact.getText() != ""){
            //Contrôle de saisie : EXIST
            if(LesPays.contains(cbxPays.getValue())){

            }
            else{
                TxtErreur.setText("Erreur : le pays sélectionner n'existe pas");
                TxtErreur.setVisible(true);
            }
        }
        else{
            TxtErreur.setVisible(true);
        }
    }

    @FXML
    public void initialize() throws SQLException {
        //On créer une comboBox custom pour faire des recherches parmis tout les pays
        new AutoCompleteBox(cbxPays);

        //On rempli la combobox avec le noms de tout les pays
        ObservableList<String> listPays = FXCollections.observableArrayList();
        for(String unPays: LesPays){
            listPays.add(unPays);
            System.out.println(unPays);
        }
        cbxPays.setItems(listPays);

        TxtErreur.setVisible(false);
        btnEnregistrerClient.setOnAction(this::onEnregistrerClient);
    }
}