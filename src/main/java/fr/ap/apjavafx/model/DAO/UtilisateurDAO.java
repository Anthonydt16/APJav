package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.model.DTO.Adherent;
import fr.ap.apjavafx.model.DTO.Commercial;
import fr.ap.apjavafx.model.DTO.Utilisateur;
import fr.ap.apjavafx.model.DTO.administrateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class UtilisateurDAO {


	public static Utilisateur authentification(String login, String mdp) {

		try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT * FROM `utilisateur` where LOGIN = ? and PASSWORD = ?")) {
			statement.setString(1, login);
			statement.setString(2, mdp);
			Utilisateur unUser;

			try (ResultSet result = statement.executeQuery()) {


				if (result.next()) {
					if (Objects.equals(result.getString(1), login) && Objects.equals(result.getString(2), mdp)) {
						//connexion reussi
						unUser = new Utilisateur(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7));
					} else {
						//en cas d'echec de connexion
						unUser = null;
					}
					return unUser;
				}


			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
	}


	/**
	 * Métode permettant de récupérer le statut relatives à un utilisateur
	 *
	 * @param user (utilidsateur)
	 * @return
	 */
	public static Adherent statutAdherent(Utilisateur user) throws SQLException {
		Adherent unAdherent = null;
		try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("select I.* from utilisateur as U, inscrit as I where U.login = I.login and U.login = ?")) {
			statement.setString(1, user.getLOGIN());
			try (ResultSet result = statement.executeQuery()) {
				if (result.next()) {
					if (result != null) {
						// ajout dans l'objet adherent
						unAdherent = new Adherent(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7), result.getString(8));

					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());

		}
		if (unAdherent != null){
			return unAdherent;
		}
		else{
			return unAdherent;
		}
	}


	/**
	 * Métode permettant de récupérer les informations relatives à un utilisateur
	 *
	 * @param user (utilidsateur)
	 * @return
	 */
	public static Commercial statutCommercial(Utilisateur user) throws SQLException {
		Commercial unCommercial = null;
		try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("select C.* from utilisateur as U, Commercial as C where U.login = C.login and U.login = ?")) {
			statement.setString(1, user.getLOGIN());
			try (ResultSet result = statement.executeQuery()) {
				if (result.next()) {
					if (result != null) {
						// ajout dans l'objet adherent
						unCommercial = new Commercial(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7), result.getString(8));

					}
				}
			}


		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());

		}
		if (unCommercial != null){
			return unCommercial;
		}
		else{
			return unCommercial;
		}
	}
	public static administrateur statutAdmin(Utilisateur user){
		administrateur unAdministrateur = null;
		try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("select A.* from utilisateur as U, admin as A where U.login = A.login and U.login = ?")) {
			statement.setString(1, user.getLOGIN());
			try (ResultSet result = statement.executeQuery()) {
				if (result.next()) {
					if (result != null) {
						// ajout dans l'objet adherent
						unAdministrateur = new administrateur(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7), result.getString(8));

					}
				}
			}


		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());

		}
		if (unAdministrateur != null){
			return unAdministrateur;
		}
		else{
			return unAdministrateur;
		}
	}

	public static ArrayList<String> getAllCommercial(){
		ArrayList<String> nomCommerciaux = new ArrayList<>();
		try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT CONCAT(PRENOM, ' ', NOM) AS `PrenomNomContact` FROM commercial ORDER BY PrenomNomContact")){
			try(ResultSet result = statement.executeQuery()){
				while(result.next()){
					nomCommerciaux.add(result.getString("PrenomNomContact"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nomCommerciaux;
	}

}

