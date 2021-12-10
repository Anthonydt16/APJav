package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.Main;
import fr.ap.apjavafx.model.DTO.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static fr.ap.apjavafx.model.DAO.UtilisateurDAO.*;

public class controllerConnexion implements Initializable {


    /**
     * Les variables du fichier FXML associ�
     */

    @FXML private TextField inputLogin;
    @FXML private TextField inputPassword;
    @FXML private Button btnValider;
    @FXML	protected void onClickValide(ActionEvent e) throws IOException, SQLException {
        System.out.println("teste");
        Adherent unAdherent =null;
        Administrateur unAdmin =null;
        LoueurDTO unLoueur =null;
        Commercial unCommercial = null;
        if(inputLogin.getText() == null || inputPassword.getText() == null){
            System.out.println("null comme connexion");
        }else{
            Utilisateur unUtilisateur = authentification(inputLogin.getText() , inputPassword.getText());
            System.out.println(unUtilisateur.getStatut());
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(Main.class.getResource("/fxml/view-AffichageLieux.fxml"));
            Pane ConnexionLayout = loader1.load();
            Stage ConnexionStage = new Stage();
            controllerAfficherLieu controller = loader1.getController();
            if(unUtilisateur != null) {

                System.out.println(unUtilisateur.getLOGIN());
                unAdherent = statutAdherent(unUtilisateur);
                //si l'user est un adherent
                if (unAdherent != null) {
                    unUtilisateur.setStatut("Adherent");
                    controller.setUnAdherent(unAdherent);
                }
                unLoueur = statutLoueur(unUtilisateur);
                if (unLoueur != null) {
                    unUtilisateur.setStatut("Loueur");
                    controller.setUnLoueur(unLoueur);
                }
                unCommercial = statutCommercial(unUtilisateur);
                //si le le type d'user est un commercial
                if (unCommercial != null) {
                    unUtilisateur.setStatut("Commercial");
                    controller.setUnCommercial(unCommercial);
                }
                unAdmin = statutAdmin(unUtilisateur);
                //si le le type d'user est un commercial
                if (unAdmin != null) {
                    unUtilisateur.setStatut("Admin");
                    controller.setUnAdmin(unAdmin);
                }
                Scene ConnectScene = new Scene(ConnexionLayout);
                ConnexionStage.setScene(ConnectScene);
            }
            System.out.println(unUtilisateur.getStatut());
            ConnexionStage.setTitle("Connexion");
            ConnexionStage.initModality(Modality.APPLICATION_MODAL);
            ConnexionStage.show();
        }

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
