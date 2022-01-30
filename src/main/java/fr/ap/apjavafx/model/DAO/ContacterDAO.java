package fr.ap.apjavafx.model.DAO;

import fr.ap.apjavafx.model.DTO.Commercial;

import fr.ap.apjavafx.model.DAO.DBConnex;
import fr.ap.apjavafx.model.DTO.ContacterDTO;
import fr.ap.apjavafx.model.DTO.EntrepriseDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class ContacterDAO {


    /**
     * Métode permettant de récupérer les informations relatives aux contacts des commerciaux
     *
     */
    public ArrayList<ContacterDTO> lireContact() {

        ContacterDTO unContactCom;
        ArrayList<ContacterDTO> desContactCom = new ArrayList <ContacterDTO>();

        try (PreparedStatement statement = DBConnex.getConnexion().prepareStatement(" Select noment, date  from contacter as c, entreprise as e where c.ident=e.ident order by noment ")){

            try (ResultSet result = statement.executeQuery()) {

                while(result.next()) {
                    unContactCom = new ContacterDTO(result.getString(1),result.getDate(2),result.getInt(3));
                    desContactCom.add(unContactCom);

                }
                return desContactCom;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}