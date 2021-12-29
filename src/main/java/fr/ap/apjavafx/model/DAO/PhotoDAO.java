package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.model.DTO.PhotoDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PhotoDAO {

    public static ArrayList<PhotoDTO> SelectPhotoIdSalle(int idSalle) {
        PhotoDTO unePhoto;
        ArrayList<PhotoDTO> desPhoto = new ArrayList<>();
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT p.* from photos as p, salle as s where s.IDSALLE = p.IDSALLE and p.IDSALLE = ?")) {
            statement.setInt(1, idSalle);

            try (ResultSet result = statement.executeQuery()) {

                while(result.next()) {
                    if(result.getInt("PRINCIPALO_N") == 1){

                        unePhoto = new PhotoDTO(result.getInt("idphotos"),result.getInt("idsalle"),result.getString("CHEMINPHOTOS"),true );

                    }
                    else{

                        unePhoto = new PhotoDTO(result.getInt("idphotos"),result.getInt("idsalle"),result.getString("CHEMINPHOTOS"),false );

                    }
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
    public static void modifPhoto(int idPhoto, String Cheminlien,Boolean principal ) {
        int principalP = 0;
            if(principal == true){
                principalP = 1;
            }else {
                principalP = 0;
            }
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("UPDATE `photos` SET `IDPHOTOS` = ?, cheminphotos = ?, principalO_N = ? WHERE `photos`.`IDPHOTOS` = ?;")) {
            statement.setInt(1, idPhoto);
            statement.setString(2, Cheminlien);
            statement.setInt(3, principalP);
            statement.setInt(4, idPhoto);

            statement.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
    public static void addPhoto(int idPhoto,int idSalle, String lien, Boolean principal ) {

        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("INSERT INTO `photos` (`IDPHOTOS`, `IDSALLE`, `CHEMINPHOTOS`, `PRINCIPALO_N`) VALUES (?, ?, ?, ?);")) {
            statement.setInt(1, idPhoto);
            statement.setInt(2, idSalle);
            statement.setString(3, lien);
            statement.setBoolean(4, principal);

            statement.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
    public static void deletePhoto(int idPhoto) {

        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("DELETE FROM `photos` WHERE `photos`.`IDPHOTOS` = ?")) {
            statement.setInt(1, idPhoto);

            statement.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }


    public static int SelectPhotoCountID() {
        int id = 0;
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT count(p.IDPHOTOS) from photos as p, salle as s where s.IDSALLE = p.IDSALLE")) {


            try (ResultSet result = statement.executeQuery()) {

                while(result.next()) {

                    id = result.getInt(1);
                }
                return id;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return id;
    }
}
