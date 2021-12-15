package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.model.DTO.ChiffreDAffaireDTO;
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
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement(" SELECT S.`IDSALLE`,L.LIBELLELIEU, S.`NOMSALLE`,S.`LARGEUR`,S.`LONGUEUR`,S.`SURFACE`,S.`HAUTEUR`,S.`CAPACITE`,S.prixDemiJournee FROM `salle` as S, Lieu as L where S.idLieu = L.IDLIEU AND L.idLieu = ?")) {
            statement.setString(1, idLieu);

            SalleDTO unSalle;
            ArrayList<SalleDTO> DesSalles = new ArrayList <SalleDTO>();

            try (ResultSet result = statement.executeQuery()) {


                while(result.next()) {

                    unSalle = new SalleDTO(result.getInt(1),result.getString(2),result.getString(3),result.getFloat(4),result.getFloat(5),result.getFloat(6),result.getFloat(7),result.getInt(8),result.getFloat(9));
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
    public static void modificationSalle(int idSalle, String NomSalle, double largueur, double longeur, double surface, double hauteur, double capacite,double prixDJ ) {

        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("UPDATE `salle` SET `NOMSALLE` = ?, `LARGEUR` = ?, `LONGUEUR` = ?, `SURFACE` = ?, `HAUTEUR` = ?, `CAPACITE` = ?, prixDemiJournee = ? WHERE `salle`.`IDSALLE` = ?;")) {
            statement.setString(1, NomSalle);
            statement.setDouble(2, largueur);
            statement.setDouble(3,longeur );
            statement.setDouble(4,surface );
            statement.setDouble(5,hauteur );
            statement.setDouble(6, capacite);
            statement.setDouble(7, prixDJ);
            statement.setInt(8,idSalle );
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    public static void AjoutSalle(int idSalle,int idlieu, String NomSalle, double largueur, double longeur, double surface, double hauteur, double capacite,double prixDJ ) {

        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("insert into `salle` values ( ?, ?, ?, ?, ?, ?, ?, ?,?)")) {
            statement.setInt(1,idSalle );
            statement.setInt(2,idlieu);
            statement.setString(3, NomSalle);
            statement.setDouble(4, largueur);
            statement.setDouble(5,longeur );
            statement.setDouble(6,surface );
            statement.setDouble(7,hauteur );
            statement.setDouble(8, capacite);
            statement.setDouble(9, prixDJ);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
    public static ArrayList<SalleDTO> SelectSalleCount(){
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement(" SELECT S.`IDSALLE`,L.LIBELLELIEU, S.`NOMSALLE`,S.`LARGEUR`,S.`LONGUEUR`,S.`SURFACE`,S.`HAUTEUR`,S.`CAPACITE`,S.prixDemiJournee FROM `salle` as S, Lieu as L where S.idLieu = L.IDLIEU ")) {


            SalleDTO unSalle;
            ArrayList<SalleDTO> DesSalles = new ArrayList <SalleDTO>();

            try (ResultSet result = statement.executeQuery()) {


                while(result.next()) {

                    unSalle = new SalleDTO(result.getInt(1),result.getString(2),result.getString(3),result.getFloat(4),result.getFloat(5),result.getFloat(6),result.getFloat(7),result.getInt(8),result.getFloat(9));
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
    public static void suppSalle(int idSalle) {

        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("DELETE FROM `salle` WHERE `IDSALLE` = ?")) {

            statement.setInt(1, idSalle);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }


    public static void suppSalleIdLieu(int idLieu) {

        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement(" DELETE FROM `salle` WHERE `salle`.`idlieu` = ?")) {

            statement.setInt(1, idLieu);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    public static ArrayList<SalleDTO> SelectSalle(){
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement(" SELECT S.`IDSALLE`,L.LIBELLELIEU, S.`NOMSALLE`,S.`LARGEUR`,S.`LONGUEUR`,S.`SURFACE`,S.`HAUTEUR`,S.`CAPACITE`,S.prixDemiJournee FROM `salle` as S, Lieu as L where S.idLieu = L.IDLIEU ")) {


            SalleDTO unSalle;
            ArrayList<SalleDTO> DesSalles = new ArrayList <SalleDTO>();

            try (ResultSet result = statement.executeQuery()) {


                while(result.next()) {

                    unSalle = new SalleDTO(result.getInt(1),result.getString(2),result.getString(3),result.getFloat(4),result.getFloat(5),result.getFloat(6),result.getFloat(7),result.getInt(8),result.getFloat(9));
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

    public static ArrayList<ChiffreDAffaireDTO> selectAnnuelCA(int idSalle,int annee){
        ArrayList<ChiffreDAffaireDTO> desCA= new ArrayList <ChiffreDAffaireDTO>();
        ChiffreDAffaireDTO ca =null;
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("select sum(S.prixDemiJournee) as totalAnnee from Salle as S, reservation as R where s.IDSALLE = R.IDSALLE and Year(DATERESA) = ? AND S.IDSALLE = ?")) {
            statement.setInt(2,idSalle);
            statement.setInt(1,annee);

            try (ResultSet result = statement.executeQuery()) {



                while(result.next()) {
                    System.out.println(result.getInt(1));
                    ca  = new ChiffreDAffaireDTO(result.getInt(1),annee);
                    desCA.add(ca);

                }
                return desCA;



            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return desCA;
    }





}
