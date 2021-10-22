package fr.ap.apjavafx.controller;


import fr.ap.apjavafx.model.DAO.FicheFraisDAO;
import fr.ap.apjavafx.model.DTO.FicheFrais;
import fr.ap.apjavafx.model.DTO.LigneFrais;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class ControllerComptableFiche implements Initializable  {


	/**
	 * Les variables du fichier FXML associ�
	 */
	@FXML	private Label nomPrenomLabel;
	@FXML	private Label adresseVisiteurLabel;
	@FXML	private Label cpVilleVisiteurLabel;
	@FXML	private Label dateEmbaucheLabel;
	@FXML	private Label dateClotureLabel;
	@FXML	private Label etatLabel;
	@FXML	private Label montantDeclareLabel;
	@FXML	private Label numFicheLabel;
	@FXML	private Button buttonCloseFicheComptable;

	@FXML	private Button buttonValiderFicheComptable;




	// construction de la TableView 
	@FXML 	private TableView<LigneFrais> tableDetailFicheComptable;
	// les colonnes de la tableView
	@FXML 	private TableColumn<LigneFrais , String > colLibelleTypeFrais;
	@FXML 	private TableColumn<LigneFrais , Float > colTarifTypeFrais;
	@FXML 	private TableColumn<LigneFrais , Integer > colQuantiteDeclaree;
	@FXML  private TableColumn<LigneFrais , Float >  colTotalDeclaree;



	private FicheFrais ficheActive ;

	//D�claration de l'ObservableList n�cessaire au remplissage de la tableView
	private ObservableList<LigneFrais> dataLignes  = FXCollections.observableArrayList();;





	/**
	 * Fonction de transfert permettant d'afficher dans la vue "viewComptableFiche" 
	 * les informations relatives à la fiche sélectionnée dans la vue "viewComptableListeFiches"
	 * @param uneFiche
	 */
	public void transfertFunction(FicheFrais uneFiche) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		numFicheLabel.setText("Fiche de frais n° " +uneFiche.getIdFiche() + " - " + uneFiche.getMoisLettre() +  " " + uneFiche.getAnnee());
		nomPrenomLabel.setText(uneFiche.getNomPrenomUtilisateur());
		adresseVisiteurLabel.setText(uneFiche.getUtilisateur().getAdresse());
		cpVilleVisiteurLabel.setText(uneFiche.getUtilisateur().getCPVille());
		dateEmbaucheLabel.setText("Date d'embauche : "+ dateFormat.format(uneFiche.getUtilisateur().getDateEmbauche()));
		if (uneFiche.getDateCloture()!=null) {
			dateClotureLabel.setText("Date clôture : "+ dateFormat.format(uneFiche.getDateCloture()));
		}
		else {
			dateClotureLabel.setText("Date clôture : ");
		}
		etatLabel.setText("Etat : "+ uneFiche.getEtatLong());
		montantDeclareLabel.setText("Montant déclaré : "+ String.format("%.2f", uneFiche.getMontantDeclare())+ " �");

		if (uneFiche.nbLignes() > 0) {

			dataLignes.addAll(uneFiche.getLesLignes());

			colLibelleTypeFrais.setCellValueFactory(new PropertyValueFactory<>("libelle"));
			colTarifTypeFrais.setCellValueFactory(new PropertyValueFactory<>("tarif"));
			colQuantiteDeclaree.setCellValueFactory(new PropertyValueFactory<>("quantiteDeclaree"));
			colTotalDeclaree.setCellValueFactory(new PropertyValueFactory<>("montant"));

			tableDetailFicheComptable.setItems(dataLignes);


		}

		ficheActive = uneFiche;

	}

	/**
	 * Fermeture de la vue 
	 * Click sur le bouton "Quitter"
	 * @param e
	 */
	public void buttonCloseFicheComptableClick(ActionEvent e) {
		Stage stage = (Stage) buttonCloseFicheComptable.getScene().getWindow();
		stage.close();
	}

	/**
	 * Validation de la fiche
	 * Click sur le bouton "Valider"
	 * @param e
	 */
	public void buttonValiderFicheComptableClick(ActionEvent e) {
		if( ficheActive.getEtat().compareTo("EC")==0) {

			Integer reponse = FicheFraisDAO.changerEtat(ficheActive.getIdFiche() , "VA");
			if(reponse == 1) {
				ficheActive.changerEtatFiche("VA");
				etatLabel.setText("Etat : "+ ficheActive.getEtatLong());
			}
			else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText("Modification impossible");
				alert.getDialogPane().setContentText("Validation de la fiche non effectu�e.");
				alert.showAndWait();
			}
		}
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
}
	