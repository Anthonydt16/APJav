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
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT IDLIEU,V.NOMVILLE,LIBELLELIEU,ADRESSELIEU,COORDX,COORDY,ANNULATIONGRATUITE,ADRESSELIEU,NBETOILES,DESCRIPTIF,Lo.login FROM `lieu` as l, ville as v,loueur as Lo where v.IDVILLE = l.IDVILLE AND Lo.IDENT = l.IDENT;")) {

            try (ResultSet result = statement.executeQuery()) {


                while(result.next()) {
                    unLieu = new LieuDTO(result.getInt("IDLIEU"),result.getString("V.NOMVILLE"),result.getString("LIBELLELIEU"),result.getString("ADRESSELIEU"),result.getInt("COORDX"),result.getInt("COORDY"),result.getString("ANNULATIONGRATUITE"),result.getInt("NBETOILES"),result.getString("DESCRIPTIF"),result.getString("Lo.login"));
                    desLieux.add(unLieu);
                    for(LieuDTO uneLieu : desLieux ){

                        System.out.println(uneLieu.getLibelleLieu());
                    }
                }
                return desLieux;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return desLieux;
    }
    public static ArrayList <LieuDTO>  SelectLieuxIdLoueur(int idLoueur) {
        LieuDTO unLieu;
        ArrayList<LieuDTO> desLieux = new ArrayList <LieuDTO>();
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT IDLIEU,V.NOMVILLE,LIBELLELIEU,ADRESSELIEU,COORDX,COORDY,ANNULATIONGRATUITE,ADRESSELIEU,NBETOILES,DESCRIPTIF,Lo.login FROM `lieu` as l, ville as v,loueur as Lo where v.IDVILLE = l.IDVILLE AND Lo.IDENT = l.IDENT AND l.IDENT = ?;")) {
            statement.setInt(1, idLoueur);

            try (ResultSet result = statement.executeQuery()) {

                while (result.next()) {

                    unLieu = new LieuDTO(result.getInt("IDLIEU"),result.getString("V.NOMVILLE"),result.getString("LIBELLELIEU"),result.getString("ADRESSELIEU"),result.getInt("COORDX"),result.getInt("COORDY"),result.getString("ANNULATIONGRATUITE"),result.getInt("NBETOILES"),result.getString("DESCRIPTIF"),result.getString("Lo.login"));
                    System.out.println(unLieu.getIdLieu());
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

    public static void modificationLieu(int idLieu, int ville, String libelleLieu, String adresseLieu, int coordX, int coordY, String annulationGratuite, int nbEtoile, String descriptif, int loueur) {

        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("UPDATE `lieu` SET `IDLIEU` = ?, `IDVILLE` = ?, `IDENT` = ?, `LIBELLELIEU` = ?, `ADRESSELIEU` = ?, `COORDX` = ?, `COORDY` = ?, `ANNULATIONGRATUITE` = ?, `NBETOILES` = ?, `DESCRIPTIF` = ? WHERE `lieu`.`IDLIEU` = ?;")) {

            statement.setInt(1, idLieu);
            statement.setInt(2, ville);
            statement.setInt(3, loueur);
            statement.setString(4, libelleLieu);
            statement.setString(5, adresseLieu);
            statement.setInt(6, coordX);
            statement.setInt(7, coordY);
            statement.setString(8, annulationGratuite);
            statement.setInt(9, nbEtoile);
            statement.setString(10, descriptif);
            statement.setInt(11, idLieu);
            System.out.println(statement.executeUpdate());

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    public static void ajoutLieu(int idLieu, int ville, String libelleLieu, String adresseLieu, int coordX, int coordY, String annulationGratuite, int nbEtoile, String descriptif, int loueur) {

        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("insert into lieu values (?,?,?,?,?,?,?,?,?,?);")) {
            // voir pk la requete s'excute pas
            statement.setInt(1, idLieu);
            statement.setInt(2, ville);
            statement.setInt(3, loueur);
            statement.setString(4, libelleLieu);
            statement.setString(5, adresseLieu);
            statement.setInt(6, coordX);
            statement.setInt(7, coordY);
            statement.setString(8, annulationGratuite);
            statement.setInt(9, nbEtoile);
            statement.setString(10, descriptif);
            System.out.println(statement.executeUpdate() );

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
    public static void suppLieu(int idLieu) {

        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("DELETE FROM `lieu` WHERE `idlieu` = ?")) {

            statement.setInt(1, idLieu);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
    public static ArrayList<ChiffreDAffaireMoisDTO> selectMoisCA(int idLieux, int annee){
        ArrayList<ChiffreDAffaireMoisDTO> desCA= new ArrayList<>();
        ChiffreDAffaireMoisDTO ca;
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("select sum(S.prixDemiJournee) as totalAnnee, MONTH(DATERESA) from Salle as S, Lieu as L, reservation as R where s.IDSALLE = R.IDSALLE and Year(DATERESA) = ? AND L.idLieu = S.idLieu AND L.idLieu = ?")) {
            statement.setInt(2,idLieux);
            statement.setInt(1,annee);

            try (ResultSet result = statement.executeQuery()) {



                while(result.next()) {
                    System.out.println(result.getInt(1));
                    ca  = new ChiffreDAffaireMoisDTO(result.getInt(1),result.getInt(2));
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


