package fr.ap.apjavafx.model.DTO;

public class LieuDTO {
    private int idLieu;
    private int idVille;
    private int idEnt;
    private String libLieu;
    private String adresseLieux;
    private Double coordX;
    private Double coordY;
    private String AnnulationGratuite;
    private int nbEtoiles;
    private String Desc;

    public LieuDTO(String libLieu) {
        this.libLieu = libLieu;
    }

    public LieuDTO(int idLieu, int idVille, int idEnt, String libLieu, String adresseLieux, Double coordX, Double coordY, String annulationGratuite, int nbEtoiles, String desc) {
        this.idLieu = idLieu;
        this.idVille = idVille;
        this.idEnt = idEnt;
        this.libLieu = libLieu;
        this.adresseLieux = adresseLieux;
        this.coordX = coordX;
        this.coordY = coordY;
        AnnulationGratuite = annulationGratuite;
        this.nbEtoiles = nbEtoiles;
        Desc = desc;
    }

    public int getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(int idLieu) {
        this.idLieu = idLieu;
    }

    public int getIdVille() {
        return idVille;
    }

    public void setIdVille(int idVille) {
        this.idVille = idVille;
    }

    public int getIdEnt() {
        return idEnt;
    }

    public void setIdEnt(int idEnt) {
        this.idEnt = idEnt;
    }

    public String getLibLieu() {
        return libLieu;
    }

    public void setLibLieu(String libLieu) {
        this.libLieu = libLieu;
    }

    public String getAdresseLieux() {
        return adresseLieux;
    }

    public void setAdresseLieux(String adresseLieux) {
        this.adresseLieux = adresseLieux;
    }

    public Double getCoordX() {
        return coordX;
    }

    public void setCoordX(Double coordX) {
        this.coordX = coordX;
    }

    public Double getCoordY() {
        return coordY;
    }

    public void setCoordY(Double coordY) {
        this.coordY = coordY;
    }

    public String getAnnulationGratuite() {
        return AnnulationGratuite;
    }

    public void setAnnulationGratuite(String annulationGratuite) {
        AnnulationGratuite = annulationGratuite;
    }

    public int getNbEtoiles() {
        return nbEtoiles;
    }

    public void setNbEtoiles(int nbEtoiles) {
        this.nbEtoiles = nbEtoiles;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }
}
