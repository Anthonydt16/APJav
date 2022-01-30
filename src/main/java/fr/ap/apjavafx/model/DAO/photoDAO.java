package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.model.DTO.LieuDTO;
import fr.ap.apjavafx.model.DTO.photoDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class photoDAO {
    public static ArrayList<photoDTO> SelectPhotoIdSalle(int idSalle) {
        photoDTO unePhoto;
        ArrayList<photoDTO> desPhoto = new ArrayList <photoDTO>();
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT p.* from photos as p, salle as s where s.IDSALLE = p.IDSALLE and p.IDSALLE = ?")) {
            statement.setInt(1, idSalle);

            try (ResultSet result = statement.executeQuery()) {

                while(result.next()) {
                    unePhoto = new photoDTO(result.getInt("idphotos"),result.getInt("idsalle"),result.getString("CHEMINPHOTOS"),result.getBoolean("PRINCIPALO_N") );
                    desPhoto.add(unePhoto);

                }
                return desPhoto;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return desPhoto;
    }
}
