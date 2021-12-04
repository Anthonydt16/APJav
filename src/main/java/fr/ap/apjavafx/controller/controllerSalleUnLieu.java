package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.model.DAO.LoueurDAO;
import fr.ap.apjavafx.model.DAO.SalleDAO;
import fr.ap.apjavafx.model.DAO.VilleDAO;
import fr.ap.apjavafx.model.DTO.LieuDTO;
import fr.ap.apjavafx.model.DTO.LoueurDTO;
import fr.ap.apjavafx.model.DTO.SalleDTO;
import fr.ap.apjavafx.model.DTO.VilleDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.util.ArrayList;


public class controllerSalleUnLieu {

    @FXML private TableView<SalleDTO> TableSalle;
    @FXML private TableColumn<SalleDTO,Integer> IDSalleCol;
    @FXML private TableColumn <SalleDTO,String> NomLieuCol;
    @FXML private TableColumn <SalleDTO,String> NomSalleCol;
    @FXML private TableColumn <SalleDTO,Float> LargeurCol;
    @FXML private TableColumn <SalleDTO,Float> LongueurCol;
    @FXML private TableColumn <SalleDTO,Float> SurfaceCol;
    @FXML private TableColumn <SalleDTO,Float> HauteurCol;
    @FXML private TableColumn <SalleDTO,Integer> CapaciteCol;
    @FXML private Text textSalle;
    @FXML private TextField inputSalle;
    @FXML private TextField inputAdresse;
    @FXML private TextField inputDescriptif;
    @FXML private TextField inputCoordX;
    @FXML private TextField inputCoordY;
    @FXML private TextField inputNomLieu;
    @FXML private ComboBox inputVille;
    @FXML private ComboBox inputNbEtoile;
    @FXML private ComboBox inputLoueur;
    @FXML private RadioButton inputNonAnnulation;
    @FXML private RadioButton inputOuiAnnulation;







    private LieuDTO unLieu;

    public LieuDTO getId() {
        return unLieu;
    }

    public void setId(LieuDTO unLieu) {
        this.unLieu = unLieu;
    }

    public void remplirTableauSalle(){
        ObservableList<SalleDTO> data = FXCollections.observableArrayList();

        ArrayList<SalleDTO> desLieux = new ArrayList <SalleDTO>();
        desLieux = SalleDAO.SelectSalle(String.valueOf(this.unLieu));


        for(SalleDTO uneSalle : desLieux ){
            data.add(uneSalle);
            System.out.println(uneSalle.getIdSalle());
        }
        IDSalleCol.setCellValueFactory(new PropertyValueFactory<SalleDTO,Integer>("idSalle"));
        NomLieuCol.setCellValueFactory(new PropertyValueFactory<SalleDTO,String>("nomLieu"));
        NomSalleCol.setCellValueFactory(new PropertyValueFactory<SalleDTO,String>("nomSalle"));
        LargeurCol.setCellValueFactory(new PropertyValueFactory<SalleDTO,Float>("largeur"));
        LongueurCol.setCellValueFactory(new PropertyValueFactory<SalleDTO,Float>("longueur"));
        SurfaceCol.setCellValueFactory(new PropertyValueFactory<SalleDTO,Float>("surface"));
        HauteurCol.setCellValueFactory(new PropertyValueFactory<SalleDTO,Float>("hauteur"));
        CapaciteCol.setCellValueFactory(new PropertyValueFactory<SalleDTO,Integer>("capacite"));

        TableSalle.setItems(data);
    }

    public void setup() {
        inputSalle.setText(String.valueOf(this.unLieu.getIdLieu()));

        remplirTableauSalle();

        inputAdresse.setText(this.unLieu.getAdresseLieu());
        inputCoordX.setText(String.valueOf(this.unLieu.getCoordX()));
        inputCoordY.setText(String.valueOf(this.unLieu.getCoordY()));
        inputNomLieu.setText(this.unLieu.getLibelleLieu());
        inputDescriptif.setText(this.unLieu.getDescriptif());
        if(this.unLieu.getAnnulationGratuite() == "true"){
            inputOuiAnnulation.setSelected(true);
        }
        else {
            inputNonAnnulation.setSelected(true);
        }
        ArrayList<Integer> nbEtoiles = new ArrayList <Integer>();
        for (int i = 0; i<6; i++){
            nbEtoiles.add(i);
        }
        ObservableList<Integer> list = FXCollections.observableArrayList(nbEtoiles);
        inputNbEtoile.setItems(list);
        inputNbEtoile.setValue(this.unLieu.getNbEtoile());


        ArrayList<String> NomVille = new ArrayList <String>();
        ArrayList<VilleDTO> lesVilles = new ArrayList<VilleDTO>();
        lesVilles= VilleDAO.SelectLesVilles();
        for (VilleDTO uneVille:  lesVilles) {
            NomVille.add(uneVille.getNomVille());
        }
        ObservableList<String> listNomVille = FXCollections.observableArrayList(NomVille);
        inputVille.setItems(listNomVille);
        inputVille.setValue(this.unLieu.getVille());

        ArrayList<String> NomLoueur = new ArrayList <String>();
        ArrayList<LoueurDTO> lesLoueurs = new ArrayList<LoueurDTO>();
        lesLoueurs= LoueurDAO.SelectLesLoueurs();
        for (LoueurDTO unLoueur:  lesLoueurs) {
            NomLoueur.add(unLoueur.getNom());
        }
        ObservableList<String> listNomLoueur = FXCollections.observableArrayList(NomLoueur);
        inputLoueur.setItems(listNomVille);
        inputVille.setValue(this.unLieu.getVille());














    }
}
