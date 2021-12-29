package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.Main;
import fr.ap.apjavafx.model.DAO.SalleDAO;
import fr.ap.apjavafx.model.DTO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static fr.ap.apjavafx.model.DAO.SalleDAO.*;
import static fr.ap.apjavafx.model.DAO.PhotoDAO.SelectPhotoIdSalle;

public class ControllerModificationUneSalle {
    public TableColumn<PhotoDTO,Integer> colIdPhoto;
    public TableColumn<PhotoDTO,String> collienPhoto;
    @FXML private Button btnAjoutPhoto;
    @FXML private TableColumn<PhotoDTO,Boolean> colPrincipal;
    @FXML private ComboBox cbxAnneBenef;
    @FXML private TableView<PhotoDTO> tablePhoto;
    @FXML private Button btnValiderSaisiSalle;
    @FXML private TextField inputNomSalle;
    @FXML private TextField inputLongueur;
    @FXML private TextField inputLargeur;
    @FXML private TextField inputSurface;
    @FXML private TextField inputHauteur;
    @FXML private TextField inputCapacite;
    @FXML private TextField inputPrixDJ;
    @FXML private TableView<ChiffreDAffaireDTO> tableCA;
    @FXML private TableColumn<ChiffreDAffaireDTO,Integer> colChiffreAffaire;
    @FXML private TableColumn<ChiffreDAffaireDTO,Integer> colAnnee;
    @FXML private TableView<ChiffreDAffaireMoisDTO> tableCAMois;
    @FXML private TableColumn<ChiffreDAffaireMoisDTO,Integer> colChiffreAffaireMois;
    @FXML private TableColumn<ChiffreDAffaireMoisDTO,Integer> colMois;

    private LieuDTO unLieu;
    private SalleDTO uneSalle;
    private String indiceBouton;
    ArrayList<SalleDTO> DesSalles= new ArrayList <SalleDTO>();

    public LieuDTO getUnLieu() {
        return unLieu;
    }

    public void setUnLieu(LieuDTO unLieu) {
        this.unLieu = unLieu;
    }

    public String getIndiceBouton() {
        return indiceBouton;
    }

    public void setIndiceBouton(String indiceBouton) {
        this.indiceBouton = indiceBouton;
    }



    public SalleDTO getSalle() {
        return uneSalle;
    }

    public void setSalle(SalleDTO uneSalle) {
        this.uneSalle = uneSalle;
    }


    @FXML
    private void comboAction(ActionEvent event) {

        System.out.println(cbxAnneBenef.getValue());
        affichageCA((Integer) cbxAnneBenef.getValue());



    }

    public void setup() {
        if(this.indiceBouton !="Add"){
            inputCapacite.setText(String.valueOf(uneSalle.getCapacite()));
            inputHauteur.setText(String.valueOf(uneSalle.getHauteur()));
            inputLargeur.setText(String.valueOf(uneSalle.getLargeur()));
            inputSurface.setText(String.valueOf(uneSalle.getSurface()));
            inputNomSalle.setText(uneSalle.getNomSalle());
            inputLongueur.setText(String.valueOf(uneSalle.getLongueur()));
            inputPrixDJ.setText(String.valueOf(uneSalle.getPrixDemiJournee()));

            //affichage desPhoto
            List<PhotoDTO> desPhotos= SelectPhotoIdSalle(this.uneSalle.getIdSalle());
            colIdPhoto.setCellValueFactory(new PropertyValueFactory<>("idPhoto"));
            collienPhoto.setCellValueFactory(new PropertyValueFactory<>("lienphoto"));
            colPrincipal.setCellValueFactory(new PropertyValueFactory<>("principal"));

            tablePhoto.setItems(FXCollections.observableList(desPhotos));


            ArrayList<Integer> desAnnee = new ArrayList<>();
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            //TODO trouver solution pour la date batard
            for (int i = 2000; i <= currentYear ; i++) {
                desAnnee.add(i);
            }
            List<Integer> desAnneeL = desAnnee;
            cbxAnneBenef.setItems(FXCollections.observableList(desAnneeL));
        }
    }




    public void affichageCA(int annee){
        ArrayList<Integer> mois= new ArrayList <Integer>();
        for (int i = 1; i <13; i++){
            mois.add(i);
        }
        //Affichage chiffre affaire par année
        ArrayList<ChiffreDAffaireDTO> desCA= selectAnnuelCA(uneSalle.getIdSalle(),annee);



        ObservableList<ChiffreDAffaireDTO> data1 = FXCollections.observableArrayList(desCA) ;



        colAnnee.setCellValueFactory(new PropertyValueFactory<ChiffreDAffaireDTO,Integer>("annee"));
        colChiffreAffaire.setCellValueFactory(new PropertyValueFactory<ChiffreDAffaireDTO,Integer>("ChiffreDaffaire"));
        tableCA.setItems(data1);
        //Affichage chiffre affaire mois
        ArrayList<ChiffreDAffaireMoisDTO> desCAM= selectMoisCA(uneSalle.getIdSalle(),annee);



        ObservableList<ChiffreDAffaireMoisDTO> data2 = FXCollections.observableArrayList(desCAM) ;




        colMois.setCellValueFactory(new PropertyValueFactory<ChiffreDAffaireMoisDTO,Integer>("mois"));
        colChiffreAffaireMois.setCellValueFactory(new PropertyValueFactory<ChiffreDAffaireMoisDTO,Integer>("CA"));
        tableCAMois.setItems(data2);




    }


    public void btnValider(ActionEvent actionEvent) {
        //pour recup l'id
        DesSalles = SalleDAO.SelectSalleCount();
        //verif de quelle bouton ils provient
        if(this.indiceBouton == "Add"){
            AjoutSalle(DesSalles.size()+1,this.unLieu.getIdLieu(),inputNomSalle.getText(), Double.valueOf(inputLongueur.getText()) , Double.valueOf(inputLongueur.getText()),Double.valueOf(inputSurface.getText()),Double.valueOf(inputHauteur.getText()),Double.valueOf(inputCapacite.getText()), Double.parseDouble(inputPrixDJ.getText()));
        }
        else{
            modificationSalle(uneSalle.getIdSalle(),inputNomSalle.getText(), Double.valueOf(inputLongueur.getText()) , Double.valueOf(inputLongueur.getText()),Double.valueOf(inputSurface.getText()),Double.valueOf(inputHauteur.getText()),Double.valueOf(inputCapacite.getText()), Double.parseDouble(inputPrixDJ.getText()));
            }
    }

    public void modifPhoto(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getClickCount() == 2){
            FXMLLoader loader3 = new FXMLLoader();
            loader3.setLocation(Main.class.getResource("/fxml/view-ModifPhoto.fxml"));
            Pane SalleLieux = loader3.load();
            ControllerModifPhoto controller = loader3.getController();
            controller.setPhoto(tablePhoto.getSelectionModel().getSelectedItem());
            controller.setIndicebtn("modification");
            controller.setup();

            Stage SalleLieuxStage = new Stage();
            Scene SalleLieuxScene = new Scene(SalleLieux);
            SalleLieuxStage.setScene(SalleLieuxScene);

            SalleLieuxStage.setTitle("Salle pour un lieu");
            SalleLieuxStage.initModality(Modality.APPLICATION_MODAL);
            SalleLieuxStage.show();
        }
    }

    public void clickAjout(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader3 = new FXMLLoader();
        loader3.setLocation(Main.class.getResource("/fxml/view-ModifPhoto.fxml"));
        Pane SalleLieux = loader3.load();
        ControllerModifPhoto controller = loader3.getController();
        controller.setIndicebtn("add");

        controller.setup();

        Stage SalleLieuxStage = new Stage();
        Scene SalleLieuxScene = new Scene(SalleLieux);
        SalleLieuxStage.setScene(SalleLieuxScene);

        SalleLieuxStage.setTitle("Salle pour un lieu");
        SalleLieuxStage.initModality(Modality.APPLICATION_MODAL);
        SalleLieuxStage.show();
    }
}
