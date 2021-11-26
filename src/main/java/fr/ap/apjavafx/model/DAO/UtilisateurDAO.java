package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.model.DTO.Adherent;
import fr.ap.apjavafx.model.DTO.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurDAO {

	/**
	 * Métode permettant de récupérer les informations relatives à un utilisateur
	 * @param user (utilidsateur)
     */
	public static void statut(Utilisateur user) throws SQLException {

		try(PreparedStatement statement = DBConnex.getConnexion().prepareStatement("select I.* from utilisateur as U, inscrit as I where U.login = I.login and U.login = ?")) {
			statement.setString(1, user.getLOGIN());
			try(ResultSet result = statement.executeQuery()) {
				if (result.next()) {
					if(result != null){
						// ajout dans l'objet adherent
					Adherent unAdherent = new Adherent(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7),result.getString(8));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}
	}
}
