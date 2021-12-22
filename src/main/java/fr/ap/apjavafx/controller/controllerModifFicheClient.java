package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.Main;
import fr.ap.apjavafx.lib.AutoCompleteBox;
import fr.ap.apjavafx.model.DAO.*;
import fr.ap.apjavafx.model.DTO.ContacterDTO;
import fr.ap.apjavafx.model.DTO.Entreprise;
import fr.ap.apjavafx.model.DTO.FicheClient;
import fr.ap.apjavafx.model.DTO.LoueurDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.Date;

import static fr.ap.apjavafx.model.DAO.LoueurDAO.loueurByFicheClient;

public class controllerModifFicheClient {

    @FXML
    private Button btnEnregistrerClient;
    @FXML
    private Button btnQuitter;
    @FXML
    private TextField inputNom;
    @FXML
    private TextField inputAdresse;
    @FXML
    private ComboBox cbxVille;
    @FXML
    private Button btnVilleDisplay;
    @FXML
    private Button btnAjoutUneVille;
    @FXML
    private ComboBox cbxPays;
    @FXML
    private Button btnRemovePays;
    @FXML
    private TextField inputTel;
    @FXML
    private TextField inputEmailEnt;
    @FXML
    private TextField inputNomContact;
    @FXML
    private TextField inputPrenomContact;
    @FXML
    private TextField inputTelContact;
    @FXML
    private TextField inputEmailContact;
    @FXML
    private CheckBox ChxContacter;
    @FXML
    private ChoiceBox DplContacter;
    @FXML
    private ChoiceBox DplTypeInscription;
    @FXML
    private DatePicker dtpDateContacter;
    @FXML
    private Text txtErreur;
    public FicheClient uneNouvelleFC;
    LoueurDTO unLoueur;
    ContacterDTO contacter;
    Entreprise uneEntreprise;
    ObservableList<String> listChoix = FXCollections.observableArrayList();
    ArrayList<String> LesPays = PaysDAO.getAllPaysName();
    ArrayList<String> LesCommerciaux = UtilisateurDAO.getAllCommercial();

    //Je dois instancier la variable "LesVilles" je met France par defaut
    ArrayList<String> LesVilles = VilleDAO.getAllVilleNameByPays("France");

    @FXML
    public void initialize() throws SQLException {
        //On met affiche pas les villes tant que l'utilisateur n'a pas choisi le pays
        btnRemovePays.setVisible(false);
        cbxVille.setVisible(false);
        btnAjoutUneVille.setVisible(false);

        txtErreur.setVisible(false);

        //On créer une comboBox custom pour faire des recherches parmis tout les pays
        new AutoCompleteBox(cbxPays);

        //On rempli la combobox avec le noms de tout les pays
        ObservableList<String> listPays = FXCollections.observableArrayList();
        for (String unPays : LesPays) {
            listPays.add(unPays);
        }
        cbxPays.setItems(listPays);

        listChoix.add("Site");
        DplTypeInscription.setItems(listChoix);

        ObservableList<String> listCommerciaux = FXCollections.observableArrayList();
        for (String unCommerciaux : LesCommerciaux) {
            listCommerciaux.add(unCommerciaux);
        }
        DplContacter.setItems(listCommerciaux);

        DplContacter.setDisable(true);
        dtpDateContacter.setDisable(true);

        remplirForm();
    }

    private void setListVille() {
        ArrayList<String> LesVilles = VilleDAO.getAllVilleNameByPays((String) cbxPays.getValue());
        ObservableList<String> listVille = FXCollections.observableArrayList();
        for (String uneVille : LesVilles) {
            listVille.add(uneVille);
        }
        cbxVille.setItems(listVille);
    }

    @FXML
    protected void OnVilleDisplay(ActionEvent e) {
        if ((String) cbxPays.getValue() != null) {
            cbxVille.setVisible(true);
            cbxPays.setDisable(true);
            btnRemovePays.setVisible(true);
            btnVilleDisplay.setVisible(false);
            btnAjoutUneVille.setVisible(true);
            new AutoCompleteBox(cbxVille);
            setListVille();
        } else {
            txtErreur.setVisible(true);
            txtErreur.setText("Erreur: vous n'avez pas sélectionner un\n pays valide");
        }
    }

    @FXML
    private void OnBlankPays(ActionEvent e) {
        setListVille();
        cbxVille.setVisible(false);
        btnAjoutUneVille.setVisible(false);
        cbxPays.setDisable(false);
        btnRemovePays.setVisible(false);
        btnVilleDisplay.setVisible(true);
    }

    @FXML
    private void OnAjoutUneVille(ActionEvent e) throws IOException {
        btnRemovePays.setVisible(true);
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(Main.class.getResource("/fxml/view-ajout-ville.fxml"));
        Pane AjoutVilleLayout = (Pane) loader1.load();
        Stage AjoutVilleStage = new Stage();
        AjoutVilleStage.getIcons().add(new Image("/image/MB.png"));
        Scene ConnectScene = new Scene(AjoutVilleLayout);
        AjoutVilleStage.setScene(ConnectScene);

        Stage stage = (Stage) btnQuitter.getScene().getWindow();
        stage.close();

        AjoutVilleStage.setTitle("Commerciaux - Ajout ville");
        AjoutVilleStage.initModality(Modality.APPLICATION_MODAL);
        AjoutVilleStage.show();
    }

    @FXML
    private void OnContacter(ActionEvent e) {
        if (ChxContacter.isSelected()) {
            DplContacter.setDisable(false);
            dtpDateContacter.setDisable(false);
            contacter = ContacterDAO.getContacterByIdEnt(unLoueur);
            if(contacter != null){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate localDate = LocalDate.parse(contacter.getDateContact(), formatter);
                dtpDateContacter.setValue(localDate);
                String prenomNomComm = CommercialDAO.getNomByLogin(contacter);
                DplContacter.setValue(prenomNomComm);
            }
            listChoix.add("Commercial");
            DplTypeInscription.setItems(listChoix);
            DplTypeInscription.setValue("Commercial");
        } else {
            DplContacter.setDisable(true);
            dtpDateContacter.setDisable(true);
            DplContacter.setValue(null);
            dtpDateContacter.setValue(null);
            DplTypeInscription.setValue("Site");
            listChoix.remove("Commercial");
        }
    }

    public void remplirForm() throws SQLException {
        //On récupère la fiche fiche client séléctionnez
        uneNouvelleFC = controllerFichesClients.getUnFicheClient();
        //Grâce a la fiche client on récupère toute les infos du loueur
        unLoueur = loueurByFicheClient(uneNouvelleFC);

        inputNom.setText(unLoueur.getNom());
        inputAdresse.setText(unLoueur.getAdresse());
        cbxPays.setValue(unLoueur.getPays());
        cbxVille.setValue(unLoueur.getVille());
        inputTel.setText(unLoueur.getTel());
        inputEmailEnt.setText(unLoueur.getMail());
        inputNomContact.setText(unLoueur.getNomContact());
        inputPrenomContact.setText(unLoueur.getPrenomContact());
        inputTelContact.setText(unLoueur.getTelContact());
        inputEmailContact.setText(unLoueur.getMailContact());
        ChxContacter.setSelected(unLoueur.getContacter());
        if (ChxContacter.isSelected()) {
            DplContacter.setDisable(false);
            dtpDateContacter.setDisable(false);
            contacter = ContacterDAO.getContacterByIdEnt(unLoueur);
            if(contacter != null){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate localDate = LocalDate.parse(contacter.getDateContact(), formatter);
                dtpDateContacter.setValue(localDate);
                String prenomNomComm = CommercialDAO.getNomByLogin(contacter);
                DplContacter.setValue(prenomNomComm);
            }
            DplTypeInscription.setValue("Commercial");
        } else {
            DplContacter.setDisable(true);
            dtpDateContacter.setDisable(true);
            DplContacter.setValue(null);
            dtpDateContacter.setValue(null);
            DplTypeInscription.setValue("Site");
        }
        DplTypeInscription.setValue(unLoueur.getTypeInscription());
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

    @FXML
    protected void onEnregistrerClient(ActionEvent e) throws IOException {
        //Je fais les même vérfication que l'ajout
        //Contrôle de saisie : NOT NULL
        if(inputNom.getText() != "" || inputAdresse.getText() != "" || cbxVille.getValue() != "" || inputTel.getText() != "" || inputEmailEnt.getText() != "" || inputNomContact.getText() != ""
                || inputPrenomContact.getText() != "" || inputTelContact.getText() != "" || inputEmailContact.getText() != ""){
            if(LesPays.contains((String) cbxPays.getValue())) {
                if (VilleDAO.getVilleExist((String) cbxVille.getValue())) {
                    if(cbxVille.isVisible()){
                        if (inputTel.getText().matches("^[0-9]*$") || inputTelContact.getText().matches("^[0-9]*$")) {
                            if (inputEmailEnt.getText().matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?") || inputEmailContact.getText().matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")) {
                                //Je récupère toute les données nécessaire pour la créations de mes objets
                                if(!ChxContacter.isSelected() && ContacterDAO.getExistContacter(unLoueur)){
                                    unLoueur.setContacter(false);
                                    ContacterDAO.removeContacter(unLoueur.getIdEnt());
                                }

                                //Objet entreprise
                                int idVille = VilleDAO.getIdVilleByName((String) cbxVille.getValue());
                                uneEntreprise = new Entreprise(unLoueur.getIdEnt(), idVille, inputNom.getText(), inputAdresse.getText(), inputTel.getText(), inputEmailEnt.getText());

                                //Objet loueur
                                unLoueur = new LoueurDTO(uneEntreprise.getNum(), uneEntreprise.getNom(), uneEntreprise.getNom(), uneEntreprise.getAdresse(),
                                        (String) cbxVille.getValue(), (String) cbxPays.getValue(), uneEntreprise.getMail(), uneEntreprise.getTel(),
                                        ChxContacter.isSelected(), (String) DplTypeInscription.getValue(),
                                        inputPrenomContact.getText(), inputNomContact.getText(), inputEmailContact.getText(), inputTelContact.getText());

                                if(ChxContacter.isSelected()){
                                    if(DplContacter.getValue() != null && dtpDateContacter.getValue() != null) {
                                        Date dateNow = new Date();
                                        Date dateSaisie = controllerAjoutFichesClients.convertToDateViaSqlDate(dtpDateContacter.getValue());
                                        if (dateNow.after(dateSaisie)) {
                                            //Objet contacter
                                            String nom = CommercialDAO.getLoginByNom(DplContacter.getValue().toString());
                                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                            String dateSaisieString = formatter.format(dateSaisie);
                                            contacter = new ContacterDTO(nom, dateSaisieString, unLoueur.getIdEnt());
                                            if (ChxContacter.isSelected() && !ContacterDAO.getExistContacter(unLoueur)) {
                                                ContacterDAO.insertContacter(contacter);
                                            } else {
                                                ContacterDAO.UpdateContacter(contacter);
                                            }
                                        } else {
                                            txtErreur.setText("Erreur : la date saisie est pas valide");
                                            txtErreur.setVisible(true);
                                        }
                                    }
                                    else{
                                        txtErreur.setText("Erreur : veuillez saisir une date de contact \n et/ou un commercial");
                                        txtErreur.setVisible(true);
                                    }
                                }
                                //On fait les updates
                                EntrepriseDAO.UpdateEntreprise(uneEntreprise);
                                LoueurDAO.updateLoueur(unLoueur);

                                //On retour sur la liste des loueurs
                                FXMLLoader loader1 = new FXMLLoader();
                                loader1.setLocation(Main.class.getResource("/fxml/view-commerciaux-fiches-clients.fxml"));
                                Pane FicheClientLayout = (Pane) loader1.load();
                                Stage FicheClientStage = new Stage();
                                FicheClientStage.getIcons().add(new Image("/image/MB.png"));
                                Scene ConnectScene = new Scene(FicheClientLayout);
                                FicheClientStage.setScene(ConnectScene);

                                Stage stage = (Stage) btnEnregistrerClient.getScene().getWindow();
                                stage.close();

                                FicheClientStage.setTitle("Commerciaux - Liste des fiches clients");
                                FicheClientStage.initModality(Modality.APPLICATION_MODAL);
                                FicheClientStage.show();

                            }
                            else {
                                txtErreur.setText("Erreur : veuillez saisir une \n adresse mail valide");
                                txtErreur.setVisible(true);
                            }
                        }
                        else {
                            txtErreur.setText("Erreur : veuillez saisir un numéro \n de téléphone valide");
                            txtErreur.setVisible(true);
                        }
                    }
                    else{
                        txtErreur.setText("Erreur : veuillez selectionnez une ville");
                        txtErreur.setVisible(true);
                    }
                }
                else {
                    txtErreur.setText("Erreur : la ville sélectionner n'existe pas");
                    txtErreur.setVisible(true);
                }
            }
            else{
                txtErreur.setText("Erreur : le pays sélectionner n'existe pas");
                txtErreur.setVisible(true);
            }
        }
        else {
            txtErreur.setVisible(true);
        }

    }
}