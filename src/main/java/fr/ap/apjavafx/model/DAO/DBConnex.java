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
	public static Utilisateur authentification(String login, String mdp) {

		try(PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT * FROM `utilisateur` where LOGIN = ? and PASSWORD = ?")) {
			statement.setString(1, login);
			statement.setString(2, mdp);
			try(ResultSet result = statement.executeQuery()) {


				if (result.next()) {
					System.out.println("teste pt");
					Utilisateur unUser = new Utilisateur(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7));
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
	 * M�thode permettant l'envoi de requ�tes "select" � la base de donn�es
	 * @param requete
	 * @param unStatement
	 * @return ResultSet
	 */
	public static ResultSet  getRS(String requete ,Statement unStatement) {
		
		ResultSet rs = null ;
		 try {
				rs = unStatement.executeQuery(requete);		
				if (!rs.next()) {
					rs =null;
				}
								
		    } catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage()); 
				
			}
		 return rs;
	}
	
	/**
	  * M�thode permettant l'envoi de requ�tes "update, insert, delete" � la base de donn�es
	 * @param requete
	 * @param unStatement
	 * @return Integer
	 */
	public static Integer  noQuery(String requete ,Statement unStatement) {
		
		Integer reponse = -1 ;
		 try {
				reponse  = unStatement.executeUpdate(requete);		
				
								
		    } catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage()); 
				
			}
		 return reponse;
	}
	

}
