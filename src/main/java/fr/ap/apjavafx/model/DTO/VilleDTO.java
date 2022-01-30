package fr.ap.apjavafx.model.DTO;

public class VilleDTO
{
   private int idVille;
   private int idPays;
   private String nomVille;
   private int CodePostal;

    public VilleDTO(int idVille, int idPays, String nomVille, int codePostal) {
        this.idVille = idVille;
        this.idPays = idPays;
        this.nomVille = nomVille;
        CodePostal = codePostal;
    }

    public int getIdVille() {
        return idVille;
    }

    public void setIdVille(int idVille) {
        this.idVille = idVille;
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

    public int getCodePostal() {
        return CodePostal;
    }

    public void setCodePostal(int codePostal) {
        CodePostal = codePostal;
    }
}
