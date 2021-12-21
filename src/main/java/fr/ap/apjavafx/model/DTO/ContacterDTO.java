package fr.ap.apjavafx.model.DTO;

import java.util.Date;

public class ContacterDTO {
    private String LOGIN;
    private String DateContact;
    private int idEnt;

    public ContacterDTO(String LOGIN, String dateContact, int idEnt) {
        this.LOGIN = LOGIN;
        DateContact = dateContact;
        this.idEnt = idEnt;
    }

    public String getLOGIN() {
        return LOGIN;
    }

    public void setLOGIN(String LOGIN) {
        this.LOGIN = LOGIN;
    }

    public String getDateContact() {
        return DateContact;
    }

    public void setDateContact(String dateContact) {
        DateContact = dateContact;
    }

    public int getIdEnt() {
        return idEnt;
    }

    public void setIdEnt(int idEnt) {
        this.idEnt = idEnt;
    }
}
