package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.Main;
import fr.ap.apjavafx.model.DAO.LieuDAO;
import fr.ap.apjavafx.model.DAO.LoueurDAO;
import fr.ap.apjavafx.model.DAO.ReservantDAO;
import fr.ap.apjavafx.model.DAO.ReservationDAO;
import fr.ap.apjavafx.model.DTO.*;
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
import java.sql.Date;
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
    @FXML private TableColumn<ReservationDTO, Date> collDateDebut;
    @FXML private TableColumn<ReservationDTO, Date> collDateResa;

    //Toute les variables FXML en rapport avec le montant
    @FXML private Pane paneMotant;
    @FXML private Text txtReservationMontantGlobal;
    @FXML private Text txtReservationMontant;
    //En rapport avec les reservants
    @FXML private Pane paneReservant;
    @FXML private Text txtReservantNom;
    @FXML private Text txtReservantAdresse;
    @FXML private Text txtReservantTel;
    @FXML private Text txtReservantMail;

    public static LoueurDTO unLoueur;
    private FicheClient rowData;
    public static LieuDTO lieuData;
    public static ReservationDTO reservData;
    public static ReservantDTO leReservant;

    public ArrayList<ReservationDTO> Reserv;

    @FXML
    public void initialize() throws SQLException {
        ReservationDAO.calculMontant();
        //On met tout les panes et txt en invicible
        paneReservant.setVisible(false);

        //Sauf le montant global
        paneMotant.setVisible(true);
        txtReservationMontant.setVisible(false);

        tableListeReservation.setVisible(false);
        rowData = controllerFichesClients.getRowData();
        txtTitle.setText("Informations sur les lieux proposés par "+ rowData.getNomEnt());
        unLoueur = LoueurDAO.loueurByFicheClient(rowData);
        int montant = ReservationDAO.getMontantGlobalReservation(unLoueur);
        txtReservationMontantGlobal.setText("Montant global : "+ montant +"€");
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
                    Reserv = ReservationDAO.getReservationByLieu(lieuData);

                    ObservableList<ReservationDTO> dataReserv = FXCollections.observableArrayList();

                    for(ReservationDTO desReserv: Reserv){
                        dataReserv.add(desReserv);
                    }
                    collListeDesReservations.setCellValueFactory(new PropertyValueFactory<ReservationDTO, String>("numResa"));
                    collDateResa.setCellValueFactory(new PropertyValueFactory<ReservationDTO, Date>("dateResa"));
                    collDateDebut.setCellValueFactory(new PropertyValueFactory<ReservationDTO, Date>("dateDebut"));
                    tableListeReservation.setItems(dataReserv);
                }
            });
            return row;
        });

        tableListeReservation.setRowFactory(tv -> {
            TableRow<ReservationDTO> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    reservData = row.getItem();
                    leReservant = ReservantDAO.getReservantByReservation(reservData);

                    txtReservationMontant.setVisible(true);
                    txtReservationMontant.setText("Montant de cette réservation : "+ reservData.getMontantReservation() + "€");
                    paneReservant.setVisible(true);
                    txtReservantNom.setText("Nom : " + leReservant.getNomEnt());
                    txtReservantAdresse.setText("Adresse : " + leReservant.getAdresseEnt());
                    txtReservantMail.setText("Adresse mail : " + leReservant.getEmail());
                    txtReservantTel.setText("N° de téléphone : " + leReservant.getTelEnt());
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