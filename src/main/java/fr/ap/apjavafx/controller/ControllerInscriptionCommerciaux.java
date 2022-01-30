package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.Main;
import fr.ap.apjavafx.model.DAO.CommercialDAO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ControllerInscriptionCommerciaux implements Initializable {


    /**
     * Les variables du fichier FXML associ�
     */

    @FXML
    private TextField prenomTextField;
    @FXML
    private TextField nomTextField;
    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField mdpPasswordField;
    @FXML
    private TextField adresseTextField;
    @FXML
    private TextField telephoneTextField;
    @FXML
    private TextField mailTextField;
    @FXML
    private TextField pourcentageTextField;

    /**
     * M�thode associ�e � l'�v�nement click sur le bouton valider
     *
     * @param e
     */
    @FXML
    protected void buttonValiderInscriptionClick(ActionEvent e) throws IOException {

        //Contrôle de saisie : NOT NULL
        if (loginTextField.getText() != "" || pourcentageTextField.getText() != "" || mdpPasswordField.getText() != ""
				|| nomTextField.getText() != "" || prenomTextField.getText() != "" || adresseTextField.getText() != ""
                || telephoneTextField.getText() != "" || mailTextField.getText() != "") {

            //Ajout du commercial

            CommercialDAO.ajouterCommercial(loginTextField.getText(), Double.valueOf(pourcentageTextField.getText()),
					mdpPasswordField.getText(), nomTextField.getText(), prenomTextField.getText(),
					adresseTextField.getText(), telephoneTextField.getText(), mailTextField.getText());

		System.out.println("---Ajout commercial : " + loginTextField.getText() + ", " + Double.valueOf(pourcentageTextField.getText())+"," +
		mdpPasswordField.getText()+ ", " + nomTextField.getText()+ ", " + prenomTextField.getText()+ ", " +
		adresseTextField.getText()+ ", " + telephoneTextField.getText()+ ", " + mailTextField.getText());

		//Je change de scene après l'ajout d'un commercial
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(Main.class.getResource("/fxml/view-Commercial.fxml"));
            Pane ConnexionLayout = (Pane) loader1.load();
            Stage ConnexionStage = new Stage();
            Scene ConnectScene = new Scene(ConnexionLayout);
            ConnexionStage.setScene(ConnectScene);

            ConnexionStage.setTitle("Commercial");
            ConnexionStage.initModality(Modality.APPLICATION_MODAL);
            ConnexionStage.show();

        } else {
            System.out.println();
        }
    }

    /**
     * Fermeture de l'application associ�e au click sur le boton quitter
     *
     * @param e
     */
    @FXML
    protected void quitterIdentificationButton(ActionEvent e) {
        Platform.exit();

    }

    /**
     * Valeurs par d�faut pour la connexion au SGBD
     * A l'inicilisation du contr�leur
     ***/
    @Override
    public void initialize(URL location, ResourceBundle ressources) {

    }
}