package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.Main;
import fr.ap.apjavafx.model.DAO.LieuDAO;
import fr.ap.apjavafx.model.DAO.LoueurDAO;
import fr.ap.apjavafx.model.DAO.SalleDAO;
import fr.ap.apjavafx.model.DAO.VilleDAO;
import fr.ap.apjavafx.model.DTO.LieuDTO;
import fr.ap.apjavafx.model.DTO.LoueurDTO;
import fr.ap.apjavafx.model.DTO.SalleDTO;
import fr.ap.apjavafx.model.DTO.VilleDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


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
    @FXML private TableColumn <SalleDTO,Float> prixDemiJourneeCol;

    @FXML private Text textSalle;
    @FXML private TextField inputSalle;
    @FXML private TextField inputAdresse;
    @FXML private TextField inputDescriptif;
    @FXML private TextField inputCoordX;
    @FXML private TextField inputCoordY;
    @FXML private TextField inputNomLieu;
    @FXML private ComboBox<VilleDTO> inputVille;
    @FXML private ComboBox<Integer> inputNbEtoile;
    @FXML private ComboBox<LoueurDTO> inputLoueur;
    @FXML private RadioButton inputNonAnnulation;
    @FXML private RadioButton inputOuiAnnulation;
    @FXML private Button btnValider;

    // savoir de quelle bouton il viens

    private String buttonindice;
    private LieuDTO unLieu;
    //recuperer les lieux existant er pour avoir l'id
    ArrayList <LieuDTO> desLieux = LieuDAO.SelectLieux();

    public LieuDTO getId() {
        return unLieu;
    }

    public void setId(LieuDTO unLieu) {
        this.unLieu = unLieu;
    }

    public String getButtonindice() {
        return buttonindice;
    }

    public void setButtonindice(String buttonindice) {
        this.buttonindice = buttonindice;
    }


    @FXML
    public void ChoixDuneSalle(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getClickCount() == 2){
            System.out.println(TableSalle.getSelectionModel().getSelectedItem().getIdSalle());

            FXMLLoader loader4 = new FXMLLoader();
            loader4.setLocation(Main.class.getResource("/fxml/view-Modifier-Salle.fxml"));
            Pane ModifSalle = (Pane) loader4.load();
            //envoyer un objet a l'autre controler
            ControllerModificationUneSalle controller = loader4.getController();
            controller.setSalle(TableSalle.getSelectionModel().getSelectedItem());
            //appel la methode setup
            controller.setup();

            Stage ModifSalleStage = new Stage();
            Scene ModifSalleScene = new Scene(ModifSalle);
            ModifSalleStage.setScene(ModifSalleScene);

            ModifSalleStage.setTitle("Salle pour un lieu");
            ModifSalleStage.initModality(Modality.APPLICATION_MODAL);
            ModifSalleStage.show();
        }
    }
    public void ValidationSaisi(ActionEvent actionEvent) throws IOException {




        if(inputOuiAnnulation.isSelected()){
            //controle de saisi vide


            if(inputVille == null | inputNomLieu.getText() == "" |inputCoordY.getText() == "" | inputCoordX.getText() == ""| inputAdresse.getText() == "" | inputDescriptif.getText() == ""){
                FXMLLoader loader7 = new FXMLLoader();
                loader7.setLocation(Main.class.getResource("/fxml/view-errorSaisi.fxml"));
                Pane error = (Pane) loader7.load();


                Stage errorStage = new Stage();
                Scene errorScene = new Scene(error);
                errorStage.setScene(errorScene);

                errorStage.setTitle("Salle pour un lieu");
                errorStage.initModality(Modality.APPLICATION_MODAL);
                errorStage.show();
            }
            else {
                if(this.buttonindice == "Add"){

                    LieuDAO.ajoutLieu(desLieux.size()+1, inputVille.getValue().getIdVille(), inputNomLieu.getText(), inputAdresse.getText(), Integer.parseInt(inputCoordX.getText()), Integer.parseInt(inputCoordY.getText()), "true", inputNbEtoile.getSelectionModel().getSelectedIndex(), inputDescriptif.getText(), inputLoueur.getValue().getIdLoueur());

                }else{

                    LieuDAO.modificationLieu(unLieu.getIdLieu(), inputVille.getValue().getIdVille(), inputNomLieu.getText(), inputAdresse.getText(), Integer.parseInt(inputCoordX.getText()), Integer.parseInt(inputCoordY.getText()), "true", inputNbEtoile.getSelectionModel().getSelectedIndex(), inputDescriptif.getText(), inputLoueur.getValue().getIdLoueur());
                    remplirTableauSalle();
                }

            }
        }
        else if(inputNonAnnulation.isSelected()){   //                                                                                                          pourquoi la convertion se passe mal
            if(inputVille == null | inputNomLieu.getText() == "" |inputCoordY.getText() == "" | inputCoordX.getText() == ""| inputAdresse.getText() == "" | inputDescriptif.getText() == ""){
                FXMLLoader loader7 = new FXMLLoader();
                loader7.setLocation(Main.class.getResource("/fxml/view-errorSaisi.fxml"));
                Pane error = (Pane) loader7.load();


                Stage errorStage = new Stage();
                Scene errorScene = new Scene(error);
                errorStage.setScene(errorScene);

                errorStage.setTitle("Salle pour un lieu");
                errorStage.initModality(Modality.APPLICATION_MODAL);
                errorStage.show();
            }
            else{
                if(this.buttonindice == "Add"){


                    LieuDAO.ajoutLieu(desLieux.size()+1, inputVille.getValue().getIdVille(), inputNomLieu.getText(), inputAdresse.getText(), Integer.parseInt(inputCoordX.getText()), Integer.parseInt(inputCoordY.getText()), "false", inputNbEtoile.getSelectionModel().getSelectedIndex(), inputDescriptif.getText(), inputLoueur.getValue().getIdLoueur());

                }else {
                    LieuDAO.modificationLieu(unLieu.getIdLieu(), inputVille.getValue().getIdVille(), inputNomLieu.getText(), inputAdresse.getText(),Integer.parseInt(inputCoordX.getText()), Integer.parseInt(inputCoordY.getText()), "false", inputNbEtoile.getSelectionModel().getSelectedIndex(), inputDescriptif.getText(), inputLoueur.getValue().getIdLoueur());
                }
            }
        }
        else if (inputNonAnnulation.isSelected() == true && inputOuiAnnulation.isSelected() == true){
            //pop up error saisi
            FXMLLoader loader7 = new FXMLLoader();
            loader7.setLocation(Main.class.getResource("/fxml/view-errorSaisi.fxml"));
            Pane error = (Pane) loader7.load();


            Stage errorStage = new Stage();
            Scene errorScene = new Scene(error);
            errorStage.setScene(errorScene);

            errorStage.setTitle("Salle pour un lieu");
            errorStage.initModality(Modality.APPLICATION_MODAL);
            errorStage.show();
        }
        else{
            //pop up error saisi
            FXMLLoader loader7 = new FXMLLoader();
            loader7.setLocation(Main.class.getResource("/fxml/view-errorSaisi.fxml"));
            Pane error = (Pane) loader7.load();


            Stage errorStage = new Stage();
            Scene errorScene = new Scene(error);
            errorStage.setScene(errorScene);

            errorStage.setTitle("Salle pour un lieu");
            errorStage.initModality(Modality.APPLICATION_MODAL);
            errorStage.show();
        }




    }


    public void remplirTableauSalle(){
        ObservableList<SalleDTO> data = FXCollections.observableArrayList();

        ArrayList<SalleDTO> desSalles= new ArrayList <SalleDTO>();
        desSalles = SalleDAO.SelectSalle(String.valueOf(this.unLieu.getIdLieu()));


        for(SalleDTO uneSalle : desSalles ){
            data.add(uneSalle);
            System.out.println(uneSalle.getIdSalle());
        }
        IDSalleCol.setCellValueFactory(new PropertyValueFactory<>("idSalle"));
        NomLieuCol.setCellValueFactory(new PropertyValueFactory<>("nomLieu"));
        NomSalleCol.setCellValueFactory(new PropertyValueFactory<>("nomSalle"));
        LargeurCol.setCellValueFactory(new PropertyValueFactory<>("largeur"));
        LongueurCol.setCellValueFactory(new PropertyValueFactory<>("longueur"));
        SurfaceCol.setCellValueFactory(new PropertyValueFactory<>("surface"));
        HauteurCol.setCellValueFactory(new PropertyValueFactory<>("hauteur"));
        CapaciteCol.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        prixDemiJourneeCol.setCellValueFactory(new PropertyValueFactory<>("prixDemiJournee"));

        TableSalle.setItems(data);
    }

    public void setup(){

        if(this.buttonindice == "modif") {


            //affichage des infos pouvent être modifier


            remplirTableauSalle();

            inputAdresse.setText(this.unLieu.getAdresseLieu());
            inputCoordX.setText(String.valueOf(this.unLieu.getCoordX()));
            inputCoordY.setText(String.valueOf(this.unLieu.getCoordY()));
            inputNomLieu.setText(this.unLieu.getLibelleLieu());
            inputDescriptif.setText(this.unLieu.getDescriptif());
            if (this.unLieu.getAnnulationGratuite() == "true") {
                inputOuiAnnulation.setSelected(true);
            } else {
                inputNonAnnulation.setSelected(true);
            }
            ArrayList<Integer> nbEtoiles = new ArrayList<Integer>();
            for (int i = 0; i < 6; i++) {
                nbEtoiles.add(i);
            }
            ObservableList<Integer> list = FXCollections.observableArrayList(nbEtoiles);
            inputNbEtoile.setItems(list);
            inputNbEtoile.setValue(this.unLieu.getNbEtoile());

            List<VilleDTO> lesVilles = VilleDAO.SelectLesVilles();
            inputVille.setItems(FXCollections.observableList(lesVilles));
            inputVille.setValue(lesVilles.stream().filter(villeDto -> villeDto.getNomVille().equals(this.unLieu.getVille())).findFirst().get());

            List<LoueurDTO> lesLoueurs = LoueurDAO.SelectLesLoueurs();
            inputLoueur.setItems(FXCollections.observableArrayList(lesLoueurs));
            inputLoueur.setValue(lesLoueurs.stream().filter(loueurDTO -> loueurDTO.getNom().equals(this.unLieu.getLoueur())).findFirst().get());

            //affichage des Salles d'un lieu

            remplirTableauSalle();
        }
        //si je viens du button modif
        else if(this.buttonindice == "Add"){
            //System.out.println("rentre dans le if");
            ArrayList<Integer> nbEtoiles = new ArrayList<Integer>();
            for (int i = 0; i < 6; i++) {
                nbEtoiles.add(i);
            }
            ObservableList<Integer> list = FXCollections.observableArrayList(nbEtoiles);
            inputNbEtoile.setItems(list);

            List<VilleDTO> lesVilles = VilleDAO.SelectLesVilles();
            inputVille.setItems(FXCollections.observableList(lesVilles));
            //inputVille.setValue(lesVilles.stream().filter(villeDto -> villeDto.getNomVille().equals(this.unLieu.getVille())).findFirst().get());

            List<LoueurDTO> lesLoueurs = LoueurDAO.SelectLesLoueurs();
            inputLoueur.setItems(FXCollections.observableArrayList(lesLoueurs));
            //inputLoueur.setValue(lesLoueurs.stream().filter(loueurDTO -> loueurDTO.getNom().equals(this.unLieu.getLoueur())).findFirst().get());

        }
    }

    @FXML
    public void clickAddSalle(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader4 = new FXMLLoader();
        loader4.setLocation(Main.class.getResource("/fxml/view-Modifier-Salle.fxml"));
        Pane ModifSalle = (Pane) loader4.load();
        //envoyer un objet a l'autre controler
        ControllerModificationUneSalle controller = loader4.getController();
        controller.setUnLieu(unLieu);
        controller.setIndiceBouton("Add");
        //appel la methode setup

        controller.setup();

        Stage ModifSalleStage = new Stage();
        Scene ModifSalleScene = new Scene(ModifSalle);
        ModifSalleStage.setScene(ModifSalleScene);

        ModifSalleStage.setTitle("Salle pour un lieu");
        ModifSalleStage.initModality(Modality.APPLICATION_MODAL);
        ModifSalleStage.show();
    }

    @FXML
    public void clickDelSalle(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader3 = new FXMLLoader();
        loader3.setLocation(Main.class.getResource("/fxml/view-ConfirmeSuppression.fxml"));
        Pane SalleLieux = loader3.load();
        controllerSuppression controller = loader3.getController();
        controller.setIndiceBtn("DelSalle");
        controller.setup();

        Stage SalleLieuxStage = new Stage();
        Scene SalleLieuxScene = new Scene(SalleLieux);
        SalleLieuxStage.setScene(SalleLieuxScene);

        SalleLieuxStage.setTitle("Salle pour un lieu Add");
        SalleLieuxStage.initModality(Modality.APPLICATION_MODAL);
        SalleLieuxStage.show();
    }
}
