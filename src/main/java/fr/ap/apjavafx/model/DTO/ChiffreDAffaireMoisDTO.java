package fr.ap.apjavafx.model.DTO;

public class ChiffreDAffaireMoisDTO {
    private int CA;
    private int mois;

    public ChiffreDAffaireMoisDTO(int CA, int mois) {
        this.CA = CA;
        this.mois = mois;
    }

    public int getCA() {
        return CA;
    }

    public void setCA(int CA) {
        this.CA = CA;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }
}
