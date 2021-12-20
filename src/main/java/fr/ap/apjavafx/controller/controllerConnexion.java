package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.Main;
import fr.ap.apjavafx.model.DTO.Adherent;
import fr.ap.apjavafx.model.DTO.Commercial;
import fr.ap.apjavafx.model.DTO.Utilisateur;
import fr.ap.apjavafx.model.DTO.administrateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static fr.ap.apjavafx.model.DAO.UtilisateurDAO.*;

public class controllerConnexion implements Initializable {

    //Fonction qui permet d'avoir un mot de passe en MD5
    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    /**
     * Les variables du fichier FXML associé
     */

    @FXML private AnchorPane connectionPane;
    @FXML private TextField inputLogin;
    @FXML private TextField inputPassword;
    @FXML private Text textIncorrect;
    @FXML private Button btnValider;

    @FXML	protected void onClickValide(ActionEvent e) throws IOException, SQLException {
        Adherent unAdherent = null;
        administrateur unAdmin =null;
        Commercial unCommercial = null;

        if(inputLogin.getText() == null || MD5(inputPassword.getText()) == null){
            System.out.println("null");
        }else{
            Utilisateur unUtilisateur = authentification(inputLogin.getText() , MD5(inputPassword.getText()));

            if(unUtilisateur != null){
                unAdherent = statutAdherent(unUtilisateur);
                //si l'user est un adherent
                if(unAdherent != null){
                    unUtilisateur.setStatut("Adherent");
                }
                unCommercial = statutCommercial(unUtilisateur);
                //si le type d'user est un commercial
                if(unCommercial != null){
                    unUtilisateur.setStatut("Commercial");
                }
                unAdmin = statutAdmin(unUtilisateur);
                //si le type d'user est un commercial
                if(unAdmin != null){
                    unUtilisateur.setStatut("Admin");
                }
            }
            else{
                textIncorrect.setText("Le login ou le mot de passe est incorrecte");
            }

            if(unUtilisateur.getStatut() == "Commercial"){
                FXMLLoader loader1 = new FXMLLoader();
                loader1.setLocation(Main.class.getResource("/fxml/view-commerciaux-fiches-clients.fxml"));
                Pane ConnexionLayout = (Pane) loader1.load();
                Stage ConnexionStage = new Stage();
                ConnexionStage.getIcons().add(new Image("/image/meetingBooking.png"));
                Scene ConnectScene = new Scene(ConnexionLayout);
                ConnexionStage.setScene(ConnectScene);

                Stage stage = (Stage) btnValider.getScene().getWindow();
                stage.close();

                ConnexionStage.setTitle("Commerciaux - fiches clients");
                ConnexionStage.initModality(Modality.APPLICATION_MODAL);
                ConnexionStage.show();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.connectionPane.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                this.btnValider.fire();
            }
        });
    }

}