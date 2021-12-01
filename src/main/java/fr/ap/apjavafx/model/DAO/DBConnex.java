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
}
