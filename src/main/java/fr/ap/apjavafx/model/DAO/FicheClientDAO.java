package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.model.DTO.FicheClient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FicheClientDAO {
    public static ArrayList <FicheClient> getAllLoueur() {
        FicheClient uneFicheClient;
        ArrayList<FicheClient> lesFichesClient = new ArrayList<FicheClient>();
        int i = 0;
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT e.NOMENT as `nomEntreprise`, e.ADRESSEENT as `adresseEntreprise`, CONCAT(V.NOMVILLE,'/', P.NOMPAYS) as `villePays`, e.TELENT as `telEntreprise`, e.EMAIL as `emailEntreprise`, CONCAT(l.NOM, ' ', l.PRENOM) AS `nomPrenomContact`, l.MAILCONTACT as `mailContact`, l.TELCONTACT as `telContact`" +
                "FROM loueur as l JOIN entreprise as e ON l.IDENT = e.IDENT JOIN `ville` AS V ON e.IDVILLE = V.IDVILLE JOIN `pays` AS P ON V.IDPAYS = P.IDPAYS")) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    uneFicheClient = new FicheClient(result.getString("nomEntreprise"),result.getString("adresseEntreprise"),result.getString("villePays"),result.getString("telEntreprise"),result.getString("emailEntreprise"),result.getString("nomPrenomContact"),result.getString("mailContact"),result.getString("telContact"));
                    lesFichesClient.add(uneFicheClient);
                }
                return lesFichesClient;
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }
}