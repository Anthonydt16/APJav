package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.model.DTO.loueur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EntrepriseDAO {
    public static int getLastIdEnt() {
        int lastIdEnt = 0;
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT MAX(IDENT) as MidEnt FROM Entreprise")){
            try(ResultSet result = statement.executeQuery()){
                if(result.next()){
                    lastIdEnt = result.getInt("MidEnt");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastIdEnt;
    }

    public static boolean getEntExist(String nom){
        boolean entExist = false;
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT IDENT FROM entreprise WHERE NOMENT = ?")){
            statement.setString(1, nom);
            try(ResultSet result = statement.executeQuery()){
                if(result.next()){
                    entExist = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entExist;
    }
}

