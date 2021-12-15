package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.model.DTO.LoueurDTO;
import fr.ap.apjavafx.model.DTO.VilleDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoueurDAO {
    public static void insertLoueur(LoueurDTO unLoueur){
        try(PreparedStatement statement = DBConnex.getConnexion().prepareStatement("INSERT INTO loueur VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")){
            statement.setInt(1, unLoueur.getIdEnt());
            statement.setString(2, unLoueur.getLogin());
            statement.setString(3, unLoueur.getNomContact());
            statement.setString(4, unLoueur.getPrenomContact());
            statement.setBoolean(5, unLoueur.getContacter());
            statement.setString(6, unLoueur.getTypeInscription());
            statement.setString(7, unLoueur.getMailContact());
            statement.setString(8, unLoueur.getTelContact());
            statement.setString(9, unLoueur.getNom());
            statement.setString(10, unLoueur.getAdresse());
            statement.setString(11, unLoueur.getTel());
            statement.setString(12, unLoueur.getMail());
            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteLoueur(int idLoueur) {
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("DELETE FROM loueur WHERE IDENT = ?")) {
            statement.setInt(1, idLoueur);
            statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}