package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.Main;
import fr.ap.apjavafx.lib.AutoCompleteBox;
import fr.ap.apjavafx.model.DAO.PaysDAO;
import fr.ap.apjavafx.model.DAO.VilleDAO;
import fr.ap.apjavafx.model.DTO.VilleDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class controllerAjoutVille {
    @FXML Button btnBack;
    @FXML
    Pane AjoutVille;
    @FXML TextField inputNomVille;
    @FXML ComboBox cbxPays;
    @FXML TextField inputCodePostal;
    @FXML
    Button btnEnregistrer;
    @FXML
    Text txtErreur;
    @FXML
    CheckBox chxClose;
    ArrayList<String> LesPays = PaysDAO.getAllPaysName();

    @FXML
    public void initialize() throws IOException {
        //On affiche cache le message d'erreur
        txtErreur.setVisible(false);
        chxClose.setSelected(true);

        new AutoCompleteBox(cbxPays);

        //On rempli la combobox avec le noms de tout les pays
        ObservableList<String> listPays = FXCollections.observableArrayList();
        for(String unPays: LesPays){
            listPays.add(unPays);
        }
        cbxPays.setItems(listPays);
    }

    @FXML
    public void OnEnregistrer(ActionEvent e) throws IOException {
        if(inputNomVille.getText() != "" && (String) cbxPays.getValue() != ""){
            if(!VilleDAO.getAllVilleNameByPays((String) cbxPays.getValue()).contains(inputNomVille.getText())) {
                int lastId = VilleDAO.getLastIdVille() + 1;
                int PaysId = PaysDAO.getIdByName((String) cbxPays.getValue());
                if(inputCodePostal.getText() == ""){
                    VilleDTO uneVille = new VilleDTO(lastId, PaysId, inputNomVille.getText());
                    VilleDAO.insertVille(uneVille);
                }
                else{
                    VilleDTO uneVille = new VilleDTO(lastId, PaysId, inputNomVille.getText(), (String) inputCodePostal.getText());
                    VilleDAO.insertVille(uneVille);
                }
                if(chxClose.isSelected()){
                    FXMLLoader loader1 = new FXMLLoader();
                    loader1.setLocation(Main.class.getResource("/fxml/view-ajout-fiches-clients.fxml"));
                    Pane AjoutVilleLayout = (Pane) loader1.load();
                    Stage AjoutVilleStage = new Stage();
                    Scene ConnectScene = new Scene(AjoutVilleLayout);
                    AjoutVilleStage.setScene(ConnectScene);

                    Stage stage = (Stage) btnEnregistrer.getScene().getWindow();
                    stage.close();

                    AjoutVilleStage.setTitle("Commerciaux - liste fiche client");
                    AjoutVilleStage.initModality(Modality.APPLICATION_MODAL);
                    AjoutVilleStage.show();
                }
                else{
                    inputNomVille.setText("");
                    inputCodePostal.setText("");
                    cbxPays.setValue(null);
                }
            }
            else{
                txtErreur.setText("Erreur : la ville saisie existe déjà");
                txtErreur.setVisible(true);
            }
        }
        else{
            txtErreur.setVisible(true);
        }
    }

    @FXML
    public void OnBack(ActionEvent e) throws IOException {
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(Main.class.getResource("/fxml/view-ajout-fiches-clients.fxml"));
        Pane AjoutVilleLayout = (Pane) loader1.load();
        Stage AjoutVilleStage = new Stage();
        Scene ConnectScene = new Scene(AjoutVilleLayout);
        AjoutVilleStage.setScene(ConnectScene);

        Stage stage = (Stage) btnEnregistrer.getScene().getWindow();
        stage.close();

        AjoutVilleStage.setTitle("Commerciaux - liste fiche client");
        AjoutVilleStage.initModality(Modality.APPLICATION_MODAL);
        AjoutVilleStage.show();
    }
}
