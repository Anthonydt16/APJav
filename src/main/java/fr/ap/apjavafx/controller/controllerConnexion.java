package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.Main;
import fr.ap.apjavafx.model.DTO.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static fr.ap.apjavafx.model.DAO.DBConnex.authentification;

public class controllerConnexion implements Initializable {


    /**
     * Les variables du fichier FXML associ�
     */

    @FXML private TextField inputLogin;
    @FXML private TextField inputPassword;
    @FXML private Button btnValider;
    @FXML	protected void onClickValide(ActionEvent e) throws IOException, SQLException {
        System.out.println("teste");
        if(inputLogin.getText() == null && inputPassword.getText() == null){
            System.out.println("null");
        }else{
            Utilisateur unUtilisateur = authentification(inputLogin.getText() , inputPassword.getText());
             System.out.println(unUtilisateur.getLOGIN());

            System.out.println(authentification(inputLogin.getText() , inputPassword.getText()));
        }


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
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
