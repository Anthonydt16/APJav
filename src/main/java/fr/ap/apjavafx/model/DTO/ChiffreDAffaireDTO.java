package fr.ap.apjavafx.model.DTO;

public class ChiffreDAffaireDTO {
    private int ChiffreDaffaire;
    private int annee;

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        annee = annee;
    }

    public int getChiffreDaffaire() {
        return ChiffreDaffaire;
    }

    public void setChiffreDaffaire(int chiffreDaffaire) {
        ChiffreDaffaire = chiffreDaffaire;
    }

    public ChiffreDAffaireDTO(int chiffreDaffaire, int Annee) {
        ChiffreDaffaire = chiffreDaffaire;
        annee = Annee;
    }


}
