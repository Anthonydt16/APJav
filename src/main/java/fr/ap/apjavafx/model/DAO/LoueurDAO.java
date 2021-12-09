package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.model.DTO.Utilisateur;
import fr.ap.apjavafx.model.DTO.loueur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class LoueurDAO {
    public static ArrayList<String> getAllLoueur() {
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT e.NOMENT as `nomEntreprise`, e.ADRESSEENT as `adresseEntreprise`, CONCAT(V.NOMVILLE,'/', P.NOMPAYS) as `vilePays`, e.TELENT as `telEntreprise`, e.EMAIL as `emailEntreprise`, CONCAT(l.NOM, ' ', l.PRENOM) AS `nomPrenomContact`, l.MAILCONTACT as `mailContact`, l.TELCONTACT as `telContact`" +
                "FROM loueur as l JOIN entreprise as e ON l.IDENT = e.IDENT JOIN `ville` AS V ON e.IDVILLE = V.IDVILLE JOIN `pays` AS P ON V.IDPAYS = P.IDPAYS")) {
            ArrayList<String> lesLoueurs = null;
            try (ResultSet result = statement.executeQuery()) {


                if (result.next()) {
                    for(int i = 0; i < 8; i++){
                        lesLoueurs.add(result.getString(i));
                    }
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