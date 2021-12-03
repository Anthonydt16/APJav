package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.model.DTO.Utilisateur;
import fr.ap.apjavafx.model.DTO.loueur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class LoueurDAO {
    public static ArrayList<loueur> getAllLoueur() {
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT * FROM `loueur`")) {
            ArrayList<loueur> lesLoueurs;
            try (ResultSet result = statement.executeQuery()) {


                if (result.next()) {
                    if (//test) {
                        //connexion reussi
                       lesLoueurs = new ArrayList<loueur>(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7));
                    } else {
                        //en cas d'echec de connexion
                        lesLoueurs = null;
                    }
                    return lesLoueurs;
                }


            }
        catch(SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }
}