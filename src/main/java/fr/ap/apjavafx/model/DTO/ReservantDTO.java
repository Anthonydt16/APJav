package fr.ap.apjavafx.model.DTO;

public class ReservantDTO {
    private int idEnt;
    private String nomEnt;
    private String adresseEnt;
    private int telEnt;
    private String email;

    public ReservantDTO(int idEnt, String nomEnt, String adresseEnt, int telEnt, String email) {
        this.idEnt = idEnt;
        this.nomEnt = nomEnt;
        this.adresseEnt = adresseEnt;
        this.telEnt = telEnt;
        this.email = email;
    }

    public int getIdEnt() {
        return idEnt;
    }

    public void setIdEnt(int idEnt) {
        this.idEnt = idEnt;
    }

    public String getNomEnt() {
        return nomEnt;
    }

    public void setNomEnt(String nomEnt) {
        this.nomEnt = nomEnt;
    }

    public String getAdresseEnt() {
        return adresseEnt;
    }

    public void setAdresseEnt(String adresseEnt) {
        this.adresseEnt = adresseEnt;
    }

    public int getTelEnt() {
        return telEnt;
    }

    public void setTelEnt(int telEnt) {
        this.telEnt = telEnt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
