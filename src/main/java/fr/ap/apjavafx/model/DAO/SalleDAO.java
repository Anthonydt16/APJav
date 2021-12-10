package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.model.DTO.LieuDTO;
import fr.ap.apjavafx.model.DTO.SalleDTO;
import fr.ap.apjavafx.model.DTO.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class SalleDAO {
    public static ArrayList<SalleDTO> SelectSalle(String idLieu){
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement(" SELECT S.`IDSALLE`,L.LIBELLELIEU, S.`NOMSALLE`,S.`LARGEUR`,S.`LONGUEUR`,S.`SURFACE`,S.`HAUTEUR`,S.`CAPACITE` FROM `salle` as S, Lieu as L where S.idLieu = L.IDLIEU AND L.idLieu = ?")) {
            statement.setString(1, idLieu);

            SalleDTO unSalle;
            ArrayList<SalleDTO> DesSalles = new ArrayList <SalleDTO>();

            try (ResultSet result = statement.executeQuery()) {


                if (result.next()) {

                    unSalle = new SalleDTO(result.getInt(1),result.getString(2),result.getString(3),result.getFloat(4),result.getFloat(5),result.getFloat(6),result.getFloat(7),result.getInt(8));
                    DesSalles.add(unSalle);
                }
                return DesSalles;



            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static void modificationSalle(int idSalle, String NomSalle, double largueur, double longeur, double surface, double hauteur, double capacite) {

        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("UPDATE `salle` SET `NOMSALLE` = ?, `LARGEUR` = ?, `LONGUEUR` = ?, `SURFACE` = ?, `HAUTEUR` = ?, `CAPACITE` = ? WHERE `salle`.`IDSALLE` = ?;")) {
            statement.setString(1, NomSalle);
            statement.setDouble(2, largueur);
            statement.setDouble(3,longeur );
            statement.setDouble(4,surface );
            statement.setDouble(5,hauteur );
            statement.setDouble(6, capacite);
            statement.setInt(7,idSalle );
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

}
