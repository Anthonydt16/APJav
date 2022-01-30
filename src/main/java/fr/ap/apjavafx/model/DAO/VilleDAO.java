package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.model.DTO.SalleDTO;
import fr.ap.apjavafx.model.DTO.VilleDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VilleDAO {
    public static ArrayList<VilleDTO> SelectLesVilles(){
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement(" SELECT * from ville")) {

            VilleDTO uneVille;
            ArrayList<VilleDTO> desVilles = new ArrayList <VilleDTO>();

            try (ResultSet result = statement.executeQuery()) {


                while (result.next()) {

                    uneVille = new VilleDTO(result.getInt(1),result.getInt(2),result.getString(3),result.getInt(4));
                    desVilles.add(uneVille);
                }
                return desVilles;



            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }
}
