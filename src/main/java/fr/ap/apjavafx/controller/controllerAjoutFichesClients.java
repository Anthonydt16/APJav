package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.Main;
import fr.ap.apjavafx.lib.AutoCompleteBox;
import fr.ap.apjavafx.model.DAO.*;
import fr.ap.apjavafx.model.DTO.ContacterDTO;
import fr.ap.apjavafx.model.DTO.Entreprise;
import fr.ap.apjavafx.model.DTO.LoueurDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Date;

public class controllerAjoutFichesClients {

    @FXML
    private Button btnEnregistrerClient;
    @FXML private Button btnQuitter;
    @FXML private TextField inputNom;
    @FXML private TextField inputAdresse;
    @FXML private ComboBox cbxVille;
    @FXML private Button btnVilleDisplay;
    @FXML private Button btnAjoutUneVille;
    @FXML private ComboBox cbxPays;
    @FXML private Button btnRemovePays;
    @FXML private TextField inputTel;
    @FXML private TextField inputEmailEnt;
    @FXML private TextField inputNomContact;
    @FXML private TextField inputPrenomContact;
    @FXML private TextField inputTelContact;
    @FXML private TextField inputEmailContact;
    @FXML private CheckBox ChxContacter;
    @FXML private ChoiceBox DplContacter;
    @FXML private DatePicker dtpDateContacter;
    @FXML private ChoiceBox DplTypeInscription;
    @FXML private Text TxtErreur;
    ArrayList<String> LesPays = PaysDAO.getAllPaysName();
    ArrayList<String> LesCommerciaux = UtilisateurDAO.getAllCommercial();

    //Je dois instancier la variable "LesVilles" je met France par defaut
    ArrayList<String> LesVilles = VilleDAO.getAllVilleNameByPays("France");
    ObservableList<String> listChoix = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws IOException {
        //On met affiche pas les villes tant que l'utilisateur n'a pas choisi le pays
        btnRemovePays.setVisible(false);
        cbxVille.setVisible(false);
        btnAjoutUneVille.setVisible(false);
        //On créer une comboBox custom pour faire des recherches parmis tout les pays
        new AutoCompleteBox(cbxPays);

        //On rempli la combobox avec le noms de tout les pays
        ObservableList<String> listPays = FXCollections.observableArrayList();
        for(String unPays: LesPays){
            listPays.add(unPays);
        }
        cbxPays.setItems(listPays);


        listChoix.add("Site");
        DplTypeInscription.setItems(listChoix);

        ObservableList<String> listCommerciaux = FXCollections.observableArrayList();
        for(String unCommerciaux: LesCommerciaux){
            listCommerciaux.add(unCommerciaux);
        }
        DplContacter.setItems(listCommerciaux);

        DplContacter.setDisable(true);
        dtpDateContacter.setDisable(true);

        TxtErreur.setVisible(false);

        btnVilleDisplay.setOnAction(this::OnVilleDisplay);
        btnRemovePays.setOnAction(this::OnBlankPays);
    }

    private void setListVille(){
        ArrayList<String> LesVilles = VilleDAO.getAllVilleNameByPays((String) cbxPays.getValue());
        ObservableList<String> listVille = FXCollections.observableArrayList();
        for(String uneVille: LesVilles){
            listVille.add(uneVille);
        }
        cbxVille.setItems(listVille);
    }

    @FXML
    private void OnContacter(ActionEvent e){
        if(ChxContacter.isSelected()){
            DplContacter.setDisable(false);
            dtpDateContacter.setDisable(false);
            listChoix.add("Commercial");
            DplTypeInscription.setItems(listChoix);
        }
        else{
            DplContacter.setDisable(true);
            dtpDateContacter.setDisable(true);
            DplContacter.setValue(null);
            dtpDateContacter.setValue(null);
            listChoix.remove("Commercial");
            DplTypeInscription.setItems(listChoix);
            DplTypeInscription.setValue("Site");
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
    protected void OnVilleDisplay(ActionEvent e){
        if((String)cbxPays.getValue() != null){
            cbxVille.setVisible(true);
            cbxPays.setDisable(true);
            btnVilleDisplay.setVisible(false);
            btnAjoutUneVille.setVisible(true);
            new AutoCompleteBox(cbxVille);
            setListVille();
        }
        else{
            TxtErreur.setVisible(true);
            TxtErreur.setText("Erreur: vous n'avez pas sélectionner un\n pays valide");
        }
    }

    //Permet de convertir un LocalDate en Date via les date sql
    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    @FXML
    protected void onEnregistrerClient(ActionEvent e) throws IOException {
        //Contrôle de saisie : NOT NULL
        if(inputNom.getText() != "" || inputAdresse.getText() != "" || cbxVille.getValue() != "" || inputTel.getText() != "" || inputEmailEnt.getText() != "" || inputNomContact.getText() != ""
                || inputPrenomContact.getText() != "" || inputTelContact.getText() != "" || inputEmailContact.getText() != ""){
            //Contrôle de saisie : EXIST
            if(LesPays.contains((String) cbxPays.getValue())){
                if(VilleDAO.getVilleExist((String) cbxVille.getValue())){
                    if(EntrepriseDAO.getEntExist(inputNom.getText())){
                        //On vérifie si c'est bien un numéro de téléphone
                        if(inputTel.getText().matches("^[0-9]*$") || inputTelContact.getText().matches("^[0-9]*$")){
                            //On vérifie si c'est bien un mail
                            if (inputEmailEnt.getText().matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?") || inputEmailContact.getText().matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")) {
                                int lastIdEnt = EntrepriseDAO.getLastIdEnt() + 1;
                                int idVille = VilleDAO.getIdVilleByName((String) cbxVille.getValue());
                                Entreprise uneEntreprise = new Entreprise(lastIdEnt, idVille, (String) inputNom.getText(), (String) inputAdresse.getText(), (String) inputTel.getText(), (String) inputEmailEnt.getText());
                                EntrepriseDAO.insertEnt(uneEntreprise);
                                //J'ajoute un loueur
                                LoueurDTO unLoueur = new LoueurDTO(uneEntreprise.getNum(), uneEntreprise.getNom(), uneEntreprise.getNom(), uneEntreprise.getAdresse(),
                                        (String) cbxVille.getValue(), (String) cbxPays.getValue(), uneEntreprise.getMail(), uneEntreprise.getTel(),
                                        ChxContacter.isSelected(), (String) DplTypeInscription.getValue(),
                                        inputPrenomContact.getText(), inputNomContact.getText(), inputEmailContact.getText(), inputTelContact.getText());
                                LoueurDAO.insertLoueur(unLoueur);

                                if(ChxContacter.isSelected()){
                                    Date dateNow = new Date();
                                    Date dateSaisie = convertToDateViaSqlDate(dtpDateContacter.getValue());
                                    //On vérifie si la date saisie est pas après la date d'aujourd'hui
                                    if(dateNow.after(dateSaisie)){
                                        //Je gère la gestion des commercial chargé de suivre un loueur
                                        SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy");
                                        String dateSaisieString  = formatter.format(dateSaisie);
                                        String nom = CommercialDAO.getLoginByNom(DplContacter.getValue().toString());
                                        ContacterDTO contacter = new ContacterDTO(nom , dateSaisieString , lastIdEnt);
                                        ContacterDAO.insertContacter(contacter);
                                    }
                                    else{
                                        TxtErreur.setText("Erreur : la date saisie est pas valide");
                                        TxtErreur.setVisible(true);
                                    }
                                }

                                //Je change de scene après l'ajout d'un loueur
                                FXMLLoader loader1 = new FXMLLoader();
                                loader1.setLocation(Main.class.getResource("/fxml/view-commerciaux-fiches-clients.fxml"));
                                Pane ConnexionLayout = (Pane) loader1.load();
                                Stage ConnexionStage = new Stage();
                                ConnexionStage.getIcons().add(new Image("/image/MB.png"));
                                Scene ConnectScene = new Scene(ConnexionLayout);
                                ConnexionStage.setScene(ConnectScene);

                                Stage stage = (Stage) btnEnregistrerClient.getScene().getWindow();
                                stage.close();

                                ConnexionStage.setTitle("Commerciaux - modifiez fiches clients");
                                ConnexionStage.initModality(Modality.APPLICATION_MODAL);
                                ConnexionStage.show();
                            } else {
                                TxtErreur.setText("Erreur : veuillez entrer une adresse \n email valide");
                                TxtErreur.setVisible(true);
                            }
                        }
                        else{
                            TxtErreur.setText("Erreur : veuillez entrer un numéro \n de téléphone valide");
                            TxtErreur.setVisible(true);
                        }
                    }
                    else{
                        TxtErreur.setText("Erreur : l'entreprise existe déjà \n veuillez modifier la fiche");
                        TxtErreur.setVisible(true);
                    }
                }
                else{
                    TxtErreur.setText("Erreur : la ville sélectionner n'existe pas");
                    TxtErreur.setVisible(true);
                }
            }
            else{
                TxtErreur.setText("Erreur : le pays sélectionner n'existe pas");
                TxtErreur.setVisible(true);
            }
        }
        else{
            TxtErreur.setVisible(true);
        }
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
    private void OnQuitter() throws IOException{
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