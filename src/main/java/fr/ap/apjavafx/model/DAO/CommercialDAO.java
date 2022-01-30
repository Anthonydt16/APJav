package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.model.DTO.Commercial;

import fr.ap.apjavafx.model.DAO.DBConnex;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class CommercialDAO {
    /**
     * Métode permettant de récupérer les informations relatives à un commercial
     * @return ResultSet
     */
    public ArrayList<Commercial> lireCommerciaux() {
        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement(" Select * from commercial ")){

            Commercial unCommercial;
            ArrayList<Commercial> desCommerciaux = new ArrayList <Commercial>();

            try (ResultSet result = statement.executeQuery()) {


                while(result.next()) {
                    unCommercial = new Commercial(result.getString(1),result.getDouble(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7),result.getString(8));
                    desCommerciaux.add(unCommercial);

                }
                return desCommerciaux;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }









    /**
     * Ajouter un commercial
     * @param login
     * @param pourcentage
     * @param mdp
     * @param nom
     * @param prenom
     * @param adresse
     * @param telephone
     * @param mail
     * @return
     */
    public static void ajouterCommercial(String login,  double pourcentage,String mdp, String nom, String prenom, String adresse, String telephone, String mail ){
        try(PreparedStatement statement = DBConnex.getConnexion().prepareStatement("insert into `commercial` values(?,?,?,?,?,?,?,?) ")) {
            statement.setString(1, login);
            statement.setDouble(2, pourcentage);
            statement.setString(3, mdp);
            statement.setString(4, nom);
            statement.setString(5, prenom);
            statement.setString(6, adresse);
            statement.setString(7, telephone);
            statement.setString(8, mail);

            statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();

        }


    }
    //Supprimer le commerciau
    public static Commercial supprimerCommercial(String LOGIN){
        try(PreparedStatement statement = DBConnex.getConnexion().prepareStatement("DELETE FROM `commercial` WHERE `LOGIN` = ?")) {
            statement.setString(1, LOGIN);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }return null;


    }

    //Modifier les données du commerciau
    public static void modifierCommercial(String login, double pourcentage,String mdp, String nom, String prenom, String adresse, String telephone, String mail){
        try(PreparedStatement statement = DBConnex.getConnexion().prepareStatement("UPDATE `commercial` SET `POURCENTAGECOMMERCIAL`=?, `password`=?, `nom`=?, `prenom`=?, `adresse`=?, `telephone`=?, `mail`=? Where `login`=? ")) {
            statement.setDouble(1, pourcentage);
            statement.setString(2, mdp);
            statement.setString(3, nom);
            statement.setString(4, prenom);
            statement.setString(5, adresse);
            statement.setString(6, telephone);
            statement.setString(7, mail);
            statement.setString(8, login);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


    }

}
