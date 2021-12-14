package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.model.DAO.LieuDAO;
import fr.ap.apjavafx.model.DAO.LoueurDAO;
import fr.ap.apjavafx.model.DAO.SalleDAO;
import fr.ap.apjavafx.model.DTO.LieuDTO;
import fr.ap.apjavafx.model.DTO.LoueurDTO;
import fr.ap.apjavafx.model.DTO.SalleDTO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.List;

import static fr.ap.apjavafx.model.DAO.LieuDAO.suppLieu;
import static fr.ap.apjavafx.model.DAO.SalleDAO.suppSalle;
import static fr.ap.apjavafx.model.DAO.SalleDAO.suppSalleIdLieu;

public class controllerSuppression {

    @FXML
    public Text txtTitre;

    private String indiceBtn;

    private LieuDTO unLieu;
    @FXML
    public ComboBox<LieuDTO> cbxChoiceLieu;
    public ComboBox<SalleDTO> cbxChoiceSalle;
    @FXML
    public Button btnValider;

    public String getIndiceBtn() {
        return indiceBtn;
    }

    public void setIndiceBtn(String indiceBtn) {
        this.indiceBtn = indiceBtn;
    }

    public LieuDTO getUnLieu() {
        return unLieu;
    }

    public void setUnLieu(LieuDTO unLieu) {
        this.unLieu = unLieu;
    }

    public void clickValide(ActionEvent actionEvent) {
        if(this.indiceBtn == "DelSalle"){
            //supp de la salle
            suppSalle(cbxChoiceSalle.getValue().getIdSalle());
        }
        else{
            //supp le lieux
            suppLieu(cbxChoiceLieu.getValue().getIdLieu());
            //supp la salle associer au lieux
            suppSalleIdLieu(cbxChoiceLieu.getValue().getIdLieu());
        }

    }

    public void setup(){
        if(this.indiceBtn == "DelSalle"){
            txtTitre.setText("Suppression d'une Salle");
            List<SalleDTO> lesSalle = SalleDAO.SelectSalle();
            //mettre un autre combo box cacher pour l'afficher
           cbxChoiceSalle.setItems(FXCollections.observableArrayList(lesSalle));
           cbxChoiceSalle.setValue(lesSalle.stream().findFirst().get());
           cbxChoiceLieu.setVisible(false);
            System.out.println("salle");
        }
        else {
            txtTitre.setText("Suppression d'un lieu");
            List<LieuDTO> lesLieu = LieuDAO.SelectLieux();
            cbxChoiceLieu.setItems(FXCollections.observableArrayList(lesLieu));
            cbxChoiceLieu.setValue(lesLieu.stream().findFirst().get());
            cbxChoiceSalle.setVisible(false);
            System.out.println("lieu");
        }

    }
}
