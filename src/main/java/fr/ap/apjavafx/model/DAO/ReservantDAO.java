package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.model.DTO.LieuDTO;
import fr.ap.apjavafx.model.DTO.ReservantDTO;
import fr.ap.apjavafx.model.DTO.ReservationDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservantDAO {
    public static ReservantDTO getReservantByReservation(ReservationDTO uneReservation) {
        ReservantDTO unReservant = null;
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT reservant.IDENT, NOMENT, ADRESSEENT,TELENT, EMAIL FROM reservant, reservation WHERE reservation.IDENT = reservant.IDENT AND reservation.IDENT = ?")){
            statement.setInt(1, uneReservation.getIdEnt());
            try(ResultSet result = statement.executeQuery()){
                if(result.next()){
                    unReservant = new ReservantDTO(result.getInt("IDENT") ,result.getString("NOMENT"), result.getString("ADRESSEENT"), result.getInt("TELENT"), result.getString("EMAIL"));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return unReservant;
    }
}
