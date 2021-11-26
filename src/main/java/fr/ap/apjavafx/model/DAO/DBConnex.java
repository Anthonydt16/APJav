package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.lib.settings;
import fr.ap.apjavafx.model.DTO.Utilisateur;
import jdk.jshell.execution.Util;

import javax.xml.transform.Result;
import java.sql.*;

public class DBConnex {
	/**
	 * M�thode de connexion � la base de donn�es
	 *
	 * @return connexion
	 */
	public static Connection getConnexion() throws SQLException {


		Connection connexion = DriverManager.getConnection("jdbc:mysql://" + settings.db_host + "/" + settings.db_url + "?user=" + settings.db_user + "&password=" + settings.db_password + "");

		return connexion;


	}

	/**
	 * M�thode d'authentification des l'utilisteur
	 *
	 * @param login
	 * @param mdp
	 */
	public static Utilisateur authentification(String login, String mdp, String inputLogin, String inputPassword) {

		try(PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT * FROM `utilisateur` where LOGIN = ? and PASSWORD = ?")) {
			statement.setString(1, login);
			statement.setString(2, mdp);
			Utilisateur unUser;
			try(ResultSet result = statement.executeQuery()) {


				if (result.next()) {
					System.out.println("teste pt");

					if (result.getString(1) == inputLogin && result.getString(2) == inputPassword){
						//connexion reussi
						 unUser = new Utilisateur(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7));
					}else{
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
}
