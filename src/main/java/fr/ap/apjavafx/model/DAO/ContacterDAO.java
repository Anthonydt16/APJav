package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.model.DTO.ContacterDTO;
import fr.ap.apjavafx.model.DTO.Entreprise;
import fr.ap.apjavafx.model.DTO.LoueurDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public static ContacterDTO getContacterByIdEnt(LoueurDTO unLoueur){
        ContacterDTO contacter = null;
        try(PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT LOGIN, DATECONTACT, IDENT FROM contacter WHERE IDENT = ?")){
            statement.setInt(1, unLoueur.getIdEnt());
            try(ResultSet result = statement.executeQuery()){
                if(result.next()){
                    contacter = new ContacterDTO(result.getString("LOGIN"), result.getString("DATECONTACT"), result.getInt("IDENT"));
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return contacter;
    }

    public static Boolean getExistContacter(LoueurDTO unLoueur){
        boolean rep = false;
        try(PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT * FROM contacter WHERE IDENT = ?")){
            statement.setInt(1, unLoueur.getIdEnt());
            try(ResultSet result = statement.executeQuery()){
                if(result.next()){
                    rep = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rep;
    }

    public static void UpdateContacter(ContacterDTO contacter){
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("UPDATE contacter set LOGIN = ?,  DATECONTACT = ?, IDENT = ?")) {
            statement.setString(1, contacter.getLOGIN());
            statement.setString(2, contacter.getDateContact());
            statement.setInt(3, contacter.getIdEnt());
            statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
