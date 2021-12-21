package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.Main;
import fr.ap.apjavafx.model.DTO.FicheClient;
import fr.ap.apjavafx.model.DTO.LieuDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class controllerListeLieux {
    private FicheClient rowData;
    @FXML
    private Text txtTitle;
    @FXML private Button btnQuitter;
    @FXML private TableView<LieuDTO> tableListeLieux;
    @FXML private TableColumn<LieuDTO, String> collListeLieux;

    @FXML
    public void initialize() {
        rowData = controllerFichesClients.getRowData();
        txtTitle.setText("Liste des lieux proposés par "+ rowData.getNomEnt());


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