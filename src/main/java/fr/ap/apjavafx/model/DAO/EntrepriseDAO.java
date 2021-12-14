package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.model.DTO.Entreprise;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntrepriseDAO {
    public static int getLastIdEnt() {
        int lastIdEnt = 0;
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT MAX(IDENT) as MidEnt FROM Entreprise")){
            try(ResultSet result = statement.executeQuery()){
                if(result.next()){
                    lastIdEnt = result.getInt("MidEnt");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastIdEnt;
    }

    public static boolean getEntExist(String nom){
        boolean entExist = false;
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT IDENT FROM entreprise WHERE NOMENT = ?")){
            statement.setString(1, nom);
            try(ResultSet result = statement.executeQuery()){
                if(!result.isBeforeFirst()){
                    entExist = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entExist;
    }

    public static void insertEnt(Entreprise unEntreprise){
        try(PreparedStatement statement = DBConnex.getConnexion().prepareStatement("INSERT INTO entreprise VALUES(?, ?, ?, ?, ?, ?)")){
            statement.setInt(1, unEntreprise.getNum());
            statement.setInt(2, unEntreprise.getIdVille());
            statement.setString(3, unEntreprise.getNom());
            statement.setString(4, unEntreprise.getAdresse());
            statement.setString(5, unEntreprise.getTel());
            statement.setString(6, unEntreprise.getMail());
            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

