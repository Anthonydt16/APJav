package fr.ap.apjavafx.model.DTO;

import java.util.Date;

public class ReservationDTO {
    private int numResa;
    private int idSalle;
    private int codeDuree;
    private int nbPersonnes;
    private Date dateResa;
    private Date dateDebut;

    public ReservationDTO(int numResa, int idSalle, int codeDuree, int nbPersonnes, Date dateResa, Date dateDebut) {
        this.numResa = numResa;
        this.idSalle = idSalle;
        this.codeDuree = codeDuree;
        this.nbPersonnes = nbPersonnes;
        this.dateResa = dateResa;
        this.dateDebut = dateDebut;
    }

    public int getNumResa() {
        return numResa;
    }

    public void setNumResa(int numResa) {
        this.numResa = numResa;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public int getCodeDuree() {
        return codeDuree;
    }

    public void setCodeDuree(int codeDuree) {
        this.codeDuree = codeDuree;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }

    public Date getDateResa() {
        return dateResa;
    }

    public void setDateResa(Date dateResa) {
        this.dateResa = dateResa;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
}
