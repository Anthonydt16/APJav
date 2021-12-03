package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.model.DTO.LieuDTO;
import fr.ap.apjavafx.model.DTO.Utilisateur;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LieuDAO {


    public static ArrayList <LieuDTO>  SelectLieux() {
        LieuDTO unLieu;
        ArrayList<LieuDTO> desLieux = new ArrayList <LieuDTO>();
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT IDLIEU,V.NOMVILLE,LIBELLELIEU,ADRESSELIEU,COORDX,COORDY,ADRESSELIEU,NBETOILES,DESCRIPTIF FROM `lieu` as l, ville as v where v.IDVILLE = l.IDVILLE;")) {


            try (ResultSet result = statement.executeQuery()) {


                if (result.next()) {
                    unLieu = new LieuDTO(result.getInt(1),result.getString(2),result.getString(3),result.getString(4),result.getInt(5),result.getInt(6),result.getString(7),result.getInt(8),result.getString(9));
                    desLieux.add(unLieu);
                }
                return desLieux;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return desLieux;
    }
}
