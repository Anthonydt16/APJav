package fr.ap.apjavafx.model.DTO;

import java.sql.Date;

public class ReservationDTO {
    private int numResa;
    private int idSalle;
    private int codeDuree;
    private int idEnt;
    private int nbPersonnes;
    private java.sql.Date dateResa;
    private java.sql.Date dateDebut;
    private int montantReservation;

    public ReservationDTO(int numResa, int idSalle, int codeDuree, int idEnt , int nbPersonnes, java.sql.Date dateResa, java.sql.Date dateDebut, int montantReservation) {
        this.numResa = numResa;
        this.idSalle = idSalle;
        this.codeDuree = codeDuree;
        this.idEnt = idEnt;
        this.nbPersonnes = nbPersonnes;
        this.dateResa = dateResa;
        this.dateDebut = dateDebut;
        this.montantReservation = montantReservation;
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

    public int getIdEnt() {
        return idEnt;
    }

    public void setIdEnt(int idEnt) {
        this.idEnt = idEnt;
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

    public int getMontantReservation() {
        return montantReservation;
    }

    public void setMontantReservation(int montantReservation) {
        this.montantReservation = montantReservation;
    }
}
