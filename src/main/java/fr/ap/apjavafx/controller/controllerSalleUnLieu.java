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
    @FXML private Button btnValider;

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
    public void ValidationSaisi(ActionEvent actionEvent) {
        //verifier pourquoi le inputOuiAnnulation est Null
        System.out.println(inputOuiAnnulation.isSelected()+"iputOui");
        System.out.println(inputNonAnnulation.isSelected()+"iputNon");


        if(inputOuiAnnulation.isSelected()){
            LieuDAO.modificationLieu(unLieu.getIdLieu(),inputVille.getValue().getIdVille(), inputNomLieu.getText(),inputAdresse.getText(),Integer.parseInt(inputCoordX.getText()), Integer.parseInt(inputCoordY.getText()),"true", inputNbEtoile.getSelectionModel().getSelectedIndex(),inputDescriptif.getText(), inputLoueur.getValue().getIdLoueur() );
            remplirTableauSalle();
        }
        else if(inputNonAnnulation.isSelected()){   //                                                                                                          pourquoi la convertion se passe mal
            LieuDAO.modificationLieu(unLieu.getIdLieu(),inputVille.getValue().getIdVille(), inputNomLieu.getText(),inputAdresse.getText(),1,2,"false", inputNbEtoile.getSelectionModel().getSelectedIndex(),inputDescriptif.getText(), inputLoueur.getValue().getIdLoueur());

        }




    }
    private LieuDTO unLieu;

    public LieuDTO getId() {
        return unLieu;
    }

    public void setId(LieuDTO unLieu) {
        this.unLieu = unLieu;
    }

    public void remplirTableauSalle(){
        ObservableList<SalleDTO> data = FXCollections.observableArrayList();

        ArrayList<SalleDTO> desSalles= new ArrayList <SalleDTO>();
        desSalles = SalleDAO.SelectSalle(String.valueOf(this.unLieu.getIdLieu()));


        for(SalleDTO uneSalle : desSalles ){
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

    public void setup(){
        //affichage des infos pouvent être modifier
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

        List<VilleDTO> lesVilles = VilleDAO.SelectLesVilles();
        inputVille.setItems(FXCollections.observableList(lesVilles));
        inputVille.setValue(lesVilles.stream().filter(villeDto -> villeDto.getNomVille().equals(this.unLieu.getVille())).findFirst().get());

        List<LoueurDTO> lesLoueurs = LoueurDAO.SelectLesLoueurs();
        inputLoueur.setItems(FXCollections.observableArrayList(lesLoueurs));
        inputLoueur.setValue(lesLoueurs.stream().filter(loueurDTO -> loueurDTO.getNom().equals(this.unLieu.getLoueur())).findFirst().get());

        //affichage des Salles d'un lieu

        remplirTableauSalle();
    }


}
