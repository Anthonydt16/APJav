package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.model.DTO.FicheClient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VilleDAO {
    public static int getLastIdVille() {
        int lastIdVille = 0;
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT MAX(IDVILLE) as MIdVille FROM ville")){
            try(ResultSet result = statement.executeQuery()){
                if(result.next()){
                    lastIdVille = result.getInt("MIdVille");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastIdVille;
    }

    public static ArrayList<String> getAllVilleNameByPays(String nomPays){
        ArrayList<String> Ville = new ArrayList<>();
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT NOMVILLE as NomVille FROM ville WHERE IDPAYS IN (SELECT IDPAYS FROM pays WHERE NOMPAYS = ?) ORDER BY NOMVILLE")){
            statement.setString(1, nomPays);
            try(ResultSet result = statement.executeQuery()){
                while(result.next()){
                    Ville.add(result.getString("NomVille"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Ville;

    }

    public static boolean getVilleExist(String nom){
        boolean villeExist = false;
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT IDVILLE FROM ville WHERE NOMVILLE = ?")){
            statement.setString(1, nom);
            try(ResultSet result = statement.executeQuery()){
                if(result.next()){
                    villeExist = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return villeExist;
    }


}