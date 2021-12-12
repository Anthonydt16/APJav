package fr.ap.apjavafx.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaysDAO {
    public static int getLastIdPays() {
        int lastIdPays = 0;
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT MAX(IDPAYS) as MidPays FROM pays")){
            try(ResultSet result = statement.executeQuery()){
                if(result.next()){
                    lastIdPays = result.getInt("MidPays");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastIdPays;
    }

    public static ArrayList<String> getAllPaysName(){
        ArrayList<String> Pays = new ArrayList<>();
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT NOMPAYS as NomPays FROM pays ORDER BY NOMPAYS")){
            try(ResultSet result = statement.executeQuery()){
                while(result.next()){
                    Pays.add(result.getString("NomPays"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Pays;
    }

    public static int getIdByName(String nom){
        int idPays = 0;
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT IDPAYS as idPays FROM pays WHERE NOMPAYS = ?")){
            try(ResultSet result = statement.executeQuery()){
                while(result.next()){
                    idPays = result.getInt("idPays");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idPays;
    }
}
