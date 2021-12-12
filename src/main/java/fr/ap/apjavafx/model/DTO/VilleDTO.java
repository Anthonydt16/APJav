package fr.ap.apjavafx.model.DTO;

public class VilleDTO {
    private int id;
    private int idPays;
    private String nomVille;
    private String codePostal;

    public VilleDTO(int id, int idPays, String nomVille, String codePostal) {
        this.id = id;
        this.idPays = idPays;
        this.nomVille = nomVille;
        this.codePostal = codePostal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPays() {
        return idPays;
    }

    public void setIdPays(int idPays) {
        this.idPays = idPays;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
}
