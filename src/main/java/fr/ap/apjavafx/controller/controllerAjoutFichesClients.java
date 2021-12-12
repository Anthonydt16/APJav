package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.Main;
import fr.ap.apjavafx.lib.AutoCompleteBox;
import fr.ap.apjavafx.model.DAO.PaysDAO;
import fr.ap.apjavafx.model.DAO.VilleDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class controllerAjoutFichesClients {

    @FXML
    private Button btnEnregistrerClient;
    @FXML private TextField inputNom;
    @FXML private TextField inputAdresse;
    @FXML private ComboBox cbxVille;
    @FXML private Button btnVilleDisplay;
    @FXML private Button btnAjoutUneVille;
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
        if(inputNom.getText() != "" || inputAdresse.getText() != "" || cbxVille.getValue() != "" || inputTel.getText() != "" || inputEmailEnt.getText() != "" || inputNomContact.getText() != ""
                || inputPrenomContact.getText() != "" || inputTelContact.getText() != "" || inputEmailContact.getText() != ""){
            //Contrôle de saisie : EXIST
            if(LesPays.contains((String) cbxPays.getValue())){

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
    protected void OnVilleDisplay(ActionEvent e){
        cbxVille.setVisible(true);
        cbxPays.setDisable(true);
        btnVilleDisplay.setVisible(false);
        btnAjoutUneVille.setVisible(true);
        new AutoCompleteBox(cbxVille);
        ArrayList<String> LesVilles = VilleDAO.getAllVilleNameByPays((String) cbxPays.getValue());
        ObservableList<String> listVille = FXCollections.observableArrayList();
        for(String uneVille: LesVilles){
            listVille.add(uneVille);
            System.out.println(uneVille);
        }
        cbxVille.setItems(listVille);
    }

    @FXML
    public void initialize() throws IOException {
        //On met affiche pas les villes tant que l'utilisateur n'a pas choisi le pays
        cbxVille.setVisible(false);
        btnAjoutUneVille.setVisible(false);
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
        btnVilleDisplay.setOnAction(this::OnVilleDisplay);
    }

    @FXML
    private void OnAjoutUneVille(ActionEvent e) throws IOException {
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(Main.class.getResource("/fxml/view-ajout-ville.fxml"));
        Pane AjoutVilleLayout = (Pane) loader1.load();
        Stage AjoutVilleStage = new Stage();
        Scene ConnectScene = new Scene(AjoutVilleLayout);
        AjoutVilleStage.setScene(ConnectScene);

        AjoutVilleStage.setTitle("Commerciaux - Ajout ville");
        AjoutVilleStage.initModality(Modality.APPLICATION_MODAL);
        AjoutVilleStage.show();
    }
}