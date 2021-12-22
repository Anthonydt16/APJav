package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.model.DTO.ContacterDTO;
import fr.ap.apjavafx.model.DTO.LoueurDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommercialDAO {
    public static String getLoginByNom(String nomPrenom) {
        String login = "";
        String nom[] = (nomPrenom.split("\\s+"));
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT LOGIN as log FROM commercial WHERE NOM = ? AND PRENOM = ?")) {
            statement.setString(1, nom[1]);
            statement.setString(2, nom[0]);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    login = result.getString("log");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return login;
    }

    public static String getNomByLogin(ContacterDTO unContact) {
        String prenomNom = null;
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT CONCAT(PRENOM, ' ', NOM) AS `PrenomNomContact` FROM commercial WHERE LOGIN = ?")) {
            statement.setString(1, unContact.getLOGIN());
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    prenomNom = result.getString("PrenomNomContact");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prenomNom;
    }
}