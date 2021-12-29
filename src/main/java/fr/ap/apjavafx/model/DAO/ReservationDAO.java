package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.model.DTO.LieuDTO;
import fr.ap.apjavafx.model.DTO.LoueurDTO;
import fr.ap.apjavafx.model.DTO.ReservationDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationDAO {
    public static ArrayList<ReservationDTO> getReservationByLieu(LieuDTO unLieu){
        ArrayList<ReservationDTO> Reservations = new ArrayList<>();
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT NUMRESA, salle.IDSALLE as IDSALLE, CODEDUREE, IDENT, NBPERSONNES, DATERESA, DATEDEBUT, IDLIEU, MONTANTRESERVATION as mtr FROM reservation, salle WHERE reservation.IDSALLE = salle.IDSALLE AND IDLIEU = ?")){
            statement.setInt(1, unLieu.getIdLieu());
            try(ResultSet result = statement.executeQuery()){
                while(result.next()){
                    ReservationDTO uneReservation = new ReservationDTO(result.getInt("NUMRESA"), result.getInt("IDSALLE"), result.getInt("CODEDUREE"), result.getInt("IDENT"), result.getInt("NBPERSONNES"), result.getDate("DATERESA"), result.getDate("DATEDEBUT"),result.getInt("mtr"));
                    Reservations.add(uneReservation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Reservations;
    }

    //Pour mettre le montant des reservations directement dans la talbe reservation en faisant un calcul selon le codeDuree et le prixDemiJournee
    public static void calculMontant(){
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT NUMRESA,CODEDUREE, prixDemiJournee as pdj FROM reservation, salle WHERE reservation.IDSALLE = salle.IDSALLE")){
            try(ResultSet result = statement.executeQuery()){
                while(result.next()){
                    if(result.getInt("CODEDUREE") == 2) {
                        try (PreparedStatement statement1 = DBConnex.getConnexion().prepareStatement("UPDATE reservation SET MONTANTRESERVATION = ? WHERE NUMRESA = ? AND CODEDUREE = ?")) {
                            statement1.setInt(1, result.getInt("pdj")*2);
                            statement1.setInt(2, result.getInt("NUMRESA"));
                            statement1.setInt(3, result.getInt("CODEDUREE"));
                            statement1.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        try (PreparedStatement statement2 = DBConnex.getConnexion().prepareStatement("UPDATE reservation SET MONTANTRESERVATION = ? WHERE NUMRESA = ? AND CODEDUREE = ?")) {
                            statement2.setInt(1, result.getInt("pdj"));
                            statement2.setInt(2, result.getInt("NUMRESA"));
                            statement2.setInt(3, result.getInt("CODEDUREE"));
                            statement2.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getMontantGlobalReservation(LoueurDTO unLoueur){
        int montant = 0;
        try(PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT SUM(MONTANTRESERVATION) as montant FROM reservation, salle, lieu WHERE reservation.IDSALLE = salle.IDSALLE and salle.IDLIEU = lieu.IDLIEU and lieu.IDENT = ?")){
            statement.setInt(1, unLoueur.getIdEnt());
            try(ResultSet result = statement.executeQuery()){
                if(result.next()){
                   montant = result.getInt("montant");
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return montant;
    }

}
