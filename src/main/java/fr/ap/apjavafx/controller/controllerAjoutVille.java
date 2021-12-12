package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.model.DAO.PaysDAO;
import fr.ap.apjavafx.model.DAO.VilleDAO;
import fr.ap.apjavafx.model.DTO.VilleDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

public class controllerAjoutVille {
    @FXML TextField inputNomVille;
    @FXML ComboBox cbxPays;
    @FXML TextField inputCodePostal;
    @FXML
    Button btnEnregistrer;
    @FXML
    Text txtErreur;
    ArrayList<String> LesPays = PaysDAO.getAllPaysName();

    @FXML
    public void initialize() throws IOException {
        //On affiche cache le message d'erreur
        txtErreur.setVisible(false);

        //On rempli la combobox avec le noms de tout les pays
        ObservableList<String> listPays = FXCollections.observableArrayList();
        for(String unPays: LesPays){
            listPays.add(unPays);
            System.out.println(unPays);
        }
        cbxPays.setItems(listPays);
        btnEnregistrer.setOnAction(this::OnEnregistrer);

    }

    @FXML
    public void OnEnregistrer(ActionEvent e){
        if(inputNomVille.getText() != "" && inputCodePostal.getText() != "" && (String) cbxPays.getValue() != ""){
            if(!VilleDAO.getAllVilleNameByPays((String) cbxPays.getValue()).contains(inputNomVille.getText())) {
                int lastId = VilleDAO.getLastIdVille() + 1;
                int PaysId = PaysDAO.getIdByName((String) cbxPays.getValue());
                VilleDTO uneVille = new VilleDTO(lastId, PaysId, inputNomVille.getText(), inputCodePostal.getText());
                //TODO faire la requête pour insert la nouvelle ville
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


}
