package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.model.DTO.ContacterDTO;
import fr.ap.apjavafx.model.DTO.Entreprise;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContacterDAO {
    public static void insertContacter(ContacterDTO unContact){
        try(PreparedStatement statement = DBConnex.getConnexion().prepareStatement("INSERT INTO contacter VALUES(?, ?, ?)")){
            statement.setString(1, unContact.getLOGIN());
            statement.setString(2, unContact.getDateContact());
            statement.setInt(3, unContact.getIdEnt());
            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeContacter(int numEnt){
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("DELETE FROM contacter WHERE IDENT = ?")) {
            statement.setInt(1, numEnt);
            statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}
