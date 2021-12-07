package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.Main;
import fr.ap.apjavafx.model.DTO.LieuDTO;
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

    public void clickAdd(ActionEvent actionEvent) {

    }

    public void clickSupp(ActionEvent actionEvent) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<LieuDTO> data = FXCollections.observableArrayList();
        System.out.println("vv");

        ArrayList <LieuDTO> desLieux =   SelectLieux();
        System.out.println("eee");

        for(LieuDTO unLieu : desLieux ){
            data.add(unLieu);
            //System.out.println(unLieu.getAdresseLieu());
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
            System.out.println(TableAffichageLieux.getSelectionModel().getSelectedItem().getIdLieu());

            FXMLLoader loader3 = new FXMLLoader();
            loader3.setLocation(Main.class.getResource("/fxml/view-SalleUnLieu.fxml"));
            Pane SalleLieux = (Pane) loader3.load();
            controllerSalleUnLieu controller = loader3.getController();
            controller.setId(TableAffichageLieux.getSelectionModel().getSelectedItem());
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
