package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.model.DTO.FicheClient;
import fr.ap.apjavafx.model.DTO.LoueurDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LieuDAO {
    public static ArrayList<String> getLieuxByLoueur(LoueurDTO unLoueur){
        ArrayList<String> Lieux = new ArrayList<>();
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT LIBELLELIEU as lbl FROM lieu WHERE IDENT= ?")){
            statement.setInt(1, unLoueur.getIdEnt());
            try(ResultSet result = statement.executeQuery()){
                while(result.next()){
                    Lieux.add(result.getString("log"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Lieux;
    }

}
