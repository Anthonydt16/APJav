package fr.ap.apjavafx.model.DTO;
//pense a ajouter l'attribut loueur et a l'ajouter dans le DAOloueur de la requete+ a l'afficher dans la comboBox
public class LieuDTO {
    private int idLieu;
    private String ville;
    private String libelleLieu;
    private String adresseLieu;
    private int coordX;
    private int coordY;
    private String annulationGratuite;
    private int nbEtoile;
    private String descriptif;
    private String Loueur;

    public LieuDTO(int idLieu, String ville, String libelleLieu, String adresseLieu, int coordX, int coordY, String annulationGratuite, int nbEtoile, String descriptif) {
        this.idLieu = idLieu;
        this.ville = ville;
        this.libelleLieu = libelleLieu;
        this.adresseLieu = adresseLieu;
        this.coordX = coordX;
        this.coordY = coordY;
        this.annulationGratuite = annulationGratuite;
        this.nbEtoile = nbEtoile;
        this.descriptif = descriptif;
    }

    public int getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(int idLieu) {
        this.idLieu = idLieu;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }


    public String getLibelleLieu() {
        return libelleLieu;
    }

    public void setLibelleLieu(String libelleLieu) {
        this.libelleLieu = libelleLieu;
    }

    public String getAdresseLieu() {
        return adresseLieu;
    }

    public void setAdresseLieu(String adresseLieu) {
        this.adresseLieu = adresseLieu;
    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public String getAnnulationGratuite() {
        return annulationGratuite;
    }

    public void setAnnulationGratuite(String annulationGratuite) {
        this.annulationGratuite = annulationGratuite;
    }

    public int getNbEtoile() {
        return nbEtoile;
    }

    public void setNbEtoile(int nbEtoile) {
        this.nbEtoile = nbEtoile;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }
}
