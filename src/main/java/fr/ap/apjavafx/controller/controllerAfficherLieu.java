package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.Main;
import fr.ap.apjavafx.model.DTO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static fr.ap.apjavafx.model.DAO.LieuDAO.SelectLieux;
import static fr.ap.apjavafx.model.DAO.LieuDAO.SelectLieuxIdLoueur;

public class controllerAfficherLieu {


    @FXML private TableView <LieuDTO> TableAffichageLieux;
    @FXML private TableColumn<LieuDTO,Integer> idLieuCol;
    @FXML private TableColumn <LieuDTO,String> NomLieuCol;
    @FXML private TableColumn <LieuDTO,String> AdresseCol;
    @FXML private TableColumn <LieuDTO,String> AnnulationCol;
    @FXML private TableColumn <LieuDTO,Integer> NbEtoileCol;
    @FXML private TableColumn <LieuDTO,String> DescriptifCol;
    @FXML private TableColumn <LieuDTO,Integer> CoordonneXCol;
    @FXML private TableColumn <LieuDTO,Integer> CoordonneYCol;
    @FXML private TableColumn <LieuDTO,Integer> VilleCol;
    @FXML private TableColumn <LieuDTO,String> nomLoueurCol;

    @FXML private Button buttonAdd;
    @FXML private Button ButtonSupp;
    private Adherent unAdherent ;
    private Administrateur unAdmin ;
    private LoueurDTO unLoueur ;
    private Commercial unCommercial;

    public Adherent getUnAdherent() {
        return unAdherent;
    }

    public void setUnAdherent(Adherent unAdherent) {
        this.unAdherent = unAdherent;
    }

    public Administrateur getUnAdmin() {
        return this.unAdmin;
    }

    public void setUnAdmin(Administrateur unAdmin) {
        this.unAdmin = unAdmin;
    }

    public LoueurDTO getUnLoueur() {
        return unLoueur;
    }

    public void setUnLoueur(LoueurDTO unLoueur) {
        this.unLoueur = unLoueur;
    }

    public Commercial getUnCommercial() {
        return unCommercial;
    }

    public void setUnCommercial(Commercial unCommercial) {
        this.unCommercial = unCommercial;
    }


    @FXML
    public void clickAdd(ActionEvent actionEvent) throws IOException {

        //ajout lieux

        FXMLLoader loader3 = new FXMLLoader();
        loader3.setLocation(Main.class.getResource("/fxml/view-SalleUnLieu.fxml"));
        Pane SalleLieux = loader3.load();
        controllerSalleUnLieu controller = loader3.getController();
        controller.setId(TableAffichageLieux.getSelectionModel().getSelectedItem());
        controller.setButtonindice("Add");
        controller.setup();

        Stage SalleLieuxStage = new Stage();
        Scene SalleLieuxScene = new Scene(SalleLieux);

        SalleLieuxStage.setScene(SalleLieuxScene);

        SalleLieuxStage.setTitle("Salle pour un lieu Add");
        SalleLieuxStage.initModality(Modality.APPLICATION_MODAL);
        SalleLieuxStage.show();

    }
    @FXML public void clickSupp(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader3 = new FXMLLoader();
        loader3.setLocation(Main.class.getResource("/fxml/view-ConfirmeSuppression.fxml"));
        Pane SalleLieux = loader3.load();
        controllerSuppression controller = loader3.getController();

        controller.setup();

        Stage SalleLieuxStage = new Stage();
        Scene SalleLieuxScene = new Scene(SalleLieux);
        SalleLieuxStage.setScene(SalleLieuxScene);

        SalleLieuxStage.setTitle("Salle pour un lieu Add");
        SalleLieuxStage.initModality(Modality.APPLICATION_MODAL);
        SalleLieuxStage.show();
    }

    public void setup() {
        //on les reaffiche au cas ou
        buttonAdd.setVisible(true);
        ButtonSupp.setVisible(true);
        ObservableList<LieuDTO> data = FXCollections.observableArrayList();
        ArrayList <LieuDTO> desLieux = new ArrayList<>();
        if(this.unLoueur != null){
            System.out.println("un loueur");
             desLieux = SelectLieuxIdLoueur(this.unLoueur.getIdLoueur());
        }
        else{

            //savoir si c'est un adherent est log pour eviter qu'il est les droit de modification juste de voir les lieux
            if(this.unAdherent != null){
                buttonAdd.setVisible(false);
                ButtonSupp.setVisible(false);
                desLieux =   SelectLieux();

            }
            else{
                System.out.println("un visiteur/admin");
                desLieux =   SelectLieux();
                buttonAdd.setVisible(true);
                ButtonSupp.setVisible(true);
            }

        }


        for(LieuDTO unLieu : desLieux ){
            data.add(unLieu);
            System.out.println(unLieu.getAdresseLieu());
        }

        idLieuCol.setCellValueFactory(new PropertyValueFactory<LieuDTO,Integer>("idLieu"));
        NomLieuCol.setCellValueFactory(new PropertyValueFactory<LieuDTO,String>("libelleLieu"));
        AdresseCol.setCellValueFactory(new PropertyValueFactory<LieuDTO,String>("adresseLieu"));
        AnnulationCol.setCellValueFactory(new PropertyValueFactory<LieuDTO,String>("annulationGratuite"));
        NbEtoileCol.setCellValueFactory(new PropertyValueFactory<>("nbEtoile"));
        DescriptifCol.setCellValueFactory(new PropertyValueFactory<LieuDTO,String>("descriptif"));
        CoordonneXCol.setCellValueFactory(new PropertyValueFactory<LieuDTO,Integer>("coordX"));
        CoordonneYCol.setCellValueFactory(new PropertyValueFactory<LieuDTO,Integer>("coordY"));
        VilleCol.setCellValueFactory(new PropertyValueFactory<LieuDTO,Integer>("ville"));
        nomLoueurCol.setCellValueFactory(new PropertyValueFactory<LieuDTO,String>("loueur"));

        TableAffichageLieux.setItems(data);

    }



    public void clickEnregistrement(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getClickCount() == 2){
            //permet de verifié si l'user est un inscrit ou pas car seul les loueur, admin et commercial peuvent voir les salle
            if(this.unAdherent == null){
                System.out.println(TableAffichageLieux.getSelectionModel().getSelectedItem().getIdLieu());

                FXMLLoader loader3 = new FXMLLoader();
                loader3.setLocation(Main.class.getResource("/fxml/view-SalleUnLieu.fxml"));
                Pane SalleLieux = loader3.load();
                controllerSalleUnLieu controller = loader3.getController();
                controller.setId(TableAffichageLieux.getSelectionModel().getSelectedItem());
                controller.setButtonindice("modif");
                controller.setup();

                Stage SalleLieuxStage = new Stage();
                Scene SalleLieuxScene = new Scene(SalleLieux);
                SalleLieuxStage.setScene(SalleLieuxScene);

                SalleLieuxStage.setTitle("Salle pour un lieu");
                SalleLieuxStage.initModality(Modality.APPLICATION_MODAL);
                SalleLieuxStage.show();
            }

        }
    }


}
