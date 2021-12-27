package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.Main;
import fr.ap.apjavafx.model.DAO.LieuDAO;
import fr.ap.apjavafx.model.DAO.LoueurDAO;
import fr.ap.apjavafx.model.DTO.FicheClient;
import fr.ap.apjavafx.model.DTO.LieuDTO;
import fr.ap.apjavafx.model.DTO.LoueurDTO;
import fr.ap.apjavafx.model.DTO.ReservationDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class controllerListeLieux {

    @FXML
    private Text txtTitle;
    @FXML private Button btnQuitter;
    @FXML private TableView<LieuDTO> tableListeLieux;
    @FXML private TableColumn<LieuDTO, String> collListeLieux;
    @FXML private TableView<ReservationDTO> tableListeReservation;
    @FXML private TableColumn<ReservationDTO, String> collListeDesReservations;

    public static LoueurDTO unLoueur;
    private FicheClient rowData;
    public static LieuDTO lieuData;

    @FXML
    public void initialize() throws SQLException {
        tableListeReservation.setVisible(false);
        rowData = controllerFichesClients.getRowData();
        txtTitle.setText("Liste des lieux proposés par "+ rowData.getNomEnt());
        unLoueur = LoueurDAO.loueurByFicheClient(rowData);

        ObservableList<LieuDTO> data = FXCollections.observableArrayList();

        ArrayList<LieuDTO> LesLieux = LieuDAO.getLieuxByLoueur(unLoueur);

        for(LieuDTO desLieux: LesLieux){
            data.add(desLieux);
        }
        collListeLieux.setCellValueFactory(new PropertyValueFactory<LieuDTO,String>("libLieu"));
        tableListeLieux.setItems(data);



        tableListeLieux.setRowFactory(tv -> {
            TableRow<LieuDTO> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    lieuData = row.getItem();
                    tableListeReservation.setVisible(true);
                }
            });
            return row;
        });
    }

    @FXML
    public void OnQuitter() throws IOException {
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(Main.class.getResource("/fxml/view-commerciaux-fiches-clients.fxml"));
        Pane FicheClientLayout = (Pane) loader1.load();
        Stage FicheClientStage = new Stage();
        FicheClientStage.getIcons().add(new Image("/image/MB.png"));
        Scene ConnectScene = new Scene(FicheClientLayout);
        FicheClientStage.setScene(ConnectScene);

        Stage stage = (Stage) btnQuitter.getScene().getWindow();
        stage.close();

        FicheClientStage.setTitle("Commerciaux - Liste des fiches clients");
        FicheClientStage.initModality(Modality.APPLICATION_MODAL);
        FicheClientStage.show();
    }
}