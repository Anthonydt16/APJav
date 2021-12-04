package fr.ap.apjavafx.model.DTO;

public class SalleDTO {
    private int idSalle;
    private String nomLieu;
    private String nomSalle;
    private float largeur;
    private float longueur;
    private float surface;
    private float hauteur;
    private int capacite;

    public SalleDTO(int idSalle, String nomLieu, String nomSalle, float largeur, float longueur, float surface, float hauteur, int capacite) {
        this.idSalle = idSalle;
        this.nomLieu = nomLieu;
        this.nomSalle = nomSalle;
        this.largeur = largeur;
        this.longueur = longueur;
        this.surface = surface;
        this.hauteur = hauteur;
        this.capacite = capacite;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public String getNomLieu() {
        return nomLieu;
    }

    public void setNomLieu(String nomLieu) {
        this.nomLieu = nomLieu;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

    public float getLargeur() {
        return largeur;
    }

    public void setLargeur(float largeur) {
        this.largeur = largeur;
    }

    public float getLongueur() {
        return longueur;
    }

    public void setLongueur(float longueur) {
        this.longueur = longueur;
    }

    public float getSurface() {
        return surface;
    }

    public void setSurface(float surface) {
        this.surface = surface;
    }

    public float getHauteur() {
        return hauteur;
    }

    public void setHauteur(float hauteur) {
        this.hauteur = hauteur;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
}
