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
}