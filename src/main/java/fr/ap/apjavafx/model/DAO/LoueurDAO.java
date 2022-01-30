package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.model.DTO.LoueurDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoueurDAO {
    public static ArrayList<LoueurDTO> SelectLesLoueurs(){
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement(" SELECT * from loueur")) {

            LoueurDTO unLoeur;
            ArrayList<LoueurDTO> desLoeurs = new ArrayList <LoueurDTO>();

            try (ResultSet result = statement.executeQuery()) {


                while (result.next()) {

                    unLoeur = new LoueurDTO(result.getInt(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7),result.getString(8),result.getString(9),result.getString(10),result.getString(11));
                    desLoeurs.add(unLoeur);
                }
                return desLoeurs;



            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }
}
