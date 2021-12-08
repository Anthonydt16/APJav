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
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT LOGIN,NOM,CONTACTEO_N,TYPEINSCRIPTION,MAILCONTACT,TELCONTACT FROM `loueur`")) {
            ArrayList<String> lesLoueurs = null;
            try (ResultSet result = statement.executeQuery()) {


                if (result.next()) {
                    lesLoueurs.add(result.getString(1));
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

//    SELECT e.NOMENT, e.ADRESSEENT, v.NOMVILLE, p.NOMPAYS, e.TELENT
//        FROM loueur as l, entreprise as e, ville as v, pays as p
//        WHERE l.IDENT = e.IDENT
//        AND e.IDVILLE = v.IDVILLE
//        AND v.IDPAYS = p.IDPAYS
//        AND v.NOMVILLE IN (SELECT NOMVILLE
//        FROM ville
//       WHERE e.IDVILLE = v.IDVILLE)
//      AND p.NOMPAYS IN (SELECT NOMPAYS
//      FROM pays
//     WHERE v.IDPAYS = p.IDPAYS);