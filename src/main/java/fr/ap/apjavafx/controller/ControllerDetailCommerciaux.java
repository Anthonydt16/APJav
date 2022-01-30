package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.model.DAO.ContacterDAO;
import fr.ap.apjavafx.model.DTO.Commercial;
import fr.ap.apjavafx.model.DTO.ContacterDTO;
import fr.ap.apjavafx.model.DTO.EntrepriseDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Date;

public class ControllerDetailCommerciaux {
    @FXML
    private TableView<ContacterDAO> tableContactCommerciaux=new TableView<>();
     @FXML
    private TableColumn<ContacterDAO, String> colEntreprise=new TableColumn<>();
    @FXML
    private TableColumn<ContacterDAO, Date> colDate=new TableColumn<>();

    @FXML
    private ObservableList<ContacterDAO> data = FXCollections.observableArrayList();

    /**
     * Remplissage le la tableView "tableContactCommerciaux"
     */
    public void remplirTableauContactCommerciaux(){
        /*ContacterDAO ContacterDAO = new ContacterDAO();
        ArrayList<ContacterDTO> desContactsCom = new ArrayList<ContacterDTO>();
        desContactsCom = ContacterDAO.lireContact();

        for(ContacterDTO unContact : desContactsCom ){
            data.add(unContact);
            //System.out.println(unContact.getLogin());
        }

        colEntreprise.setCellValueFactory(new PropertyValueFactory<EntrepriseDTO,String>("noment"));
        colDate.setCellValueFactory(new PropertyValueFactory<ContacterDTO,Date>("date"));


        tableContactCommerciaux().setItems(data);*/
    }
}
