package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.model.DTO.Entreprise;
import fr.ap.apjavafx.model.DTO.FicheClient;
import fr.ap.apjavafx.model.DTO.LoueurDTO;
import fr.ap.apjavafx.model.DTO.VilleDTO;

import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoueurDAO {
    public static void insertLoueur(LoueurDTO unLoueur){
        try(PreparedStatement statement = DBConnex.getConnexion().prepareStatement("INSERT INTO loueur VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")){
            statement.setInt(1, unLoueur.getIdEnt());
            statement.setString(2, unLoueur.getLogin());
            statement.setString(3, unLoueur.getNomContact());
            statement.setString(4, unLoueur.getPrenomContact());
            statement.setBoolean(5, unLoueur.getContacter());
            statement.setString(6, unLoueur.getTypeInscription());
            statement.setString(7, unLoueur.getMailContact());
            statement.setString(8, unLoueur.getTelContact());
            statement.setString(9, unLoueur.getNom());
            statement.setString(10, unLoueur.getAdresse());
            statement.setString(11, unLoueur.getTel());
            statement.setString(12, unLoueur.getMail());
            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteLoueur(int idLoueur) {
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement("DELETE FROM loueur WHERE IDENT = ?")) {
            statement.setInt(1, idLoueur);
            statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static LoueurDTO loueurByFicheClient(FicheClient uneFiche) throws SQLException {
        LoueurDTO leLoueur = null;
        try(PreparedStatement statement = DBConnex.getConnexion().prepareStatement("SELECT loueur.IDENT, loueur.LOGIN,NOM,PRENOM,CONTACTEO_N,TYPEINSCRIPTION,MAILCONTACT,TELCONTACT,loueur.NOMENT, loueur.ADRESSEENT, loueur.TELENT, loueur.EMAIL, NOMVILLE, NOMPAYS FROM loueur, ville, pays, entreprise WHERE loueur.LOGIN = ? AND loueur.IDENT = entreprise.IDENT AND entreprise.IDVILLE = ville.IDVILLE AND ville.IDPAYS = pays.IDPAYS")){
            statement.setString(1, uneFiche.getNomEnt());
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    leLoueur = new LoueurDTO(result.getInt("IDENT"), result.getString("LOGIN"), result.getString("NOMENT"), result.getString("ADRESSEENT"), result.getString("NOMVILLE"), result.getString("NOMPAYS"), result.getString("EMAIL"), result.getString("TELENT"), result.getBoolean("CONTACTEO_N"), result.getString("TYPEINSCRIPTION"), result.getString("PRENOM"), result.getString("NOM"), result.getString("MAILCONTACT"), result.getString("TELCONTACT"));
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return leLoueur;
    }

    public static void updateLoueur(LoueurDTO unLoueur) {
        try(PreparedStatement statement = DBConnex.getConnexion().prepareStatement("UPDATE loueur set NOM = ?,  PRENOM = ?, CONTACTEO_N = ?, TYPEINSCRIPTION = ?, MAILCONTACT = ?, TELCONTACT = ?, NOMENT = ?, ADRESSEENT = ?, TELENT = ?, EMAIL = ? WHERE IDENT = ?")){
            statement.setString(1, unLoueur.getNomContact());
            statement.setString(2, unLoueur.getPrenomContact());
            statement.setBoolean(3, unLoueur.getContacter());
            statement.setString(4, unLoueur.getTypeInscription());
            statement.setString(5, unLoueur.getMailContact());
            statement.setString(6, unLoueur.getTelContact());
            statement.setString(7, unLoueur.getNom());
            statement.setString(8, unLoueur.getAdresse());
            statement.setString(9, unLoueur.getTel());
            statement.setString(10, unLoueur.getMail());
            statement.setInt(11, unLoueur.getIdEnt());
            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


}