package fr.ap.apjavafx.model.DTO;

public class EntrepriseDTO {
    private int ident;
    private int idville;
    private String noment;
    private String adresseent;
    private String telent;
    private String email;

    public EntrepriseDTO(int unid, int unidville, String unnoment, String unadresseent, String untelent, String unemail){

    }

    public int getIdent() {
        return ident;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }

    public int getIdville() {
        return idville;
    }

    public void setIdville(int idville) {
        this.idville = idville;
    }

    public String getNoment() {
        return noment;
    }

    public void setNoment(String noment) {
        this.noment = noment;
    }

    public String getAdresseent() {
        return adresseent;
    }

    public void setAdresseent(String adresseent) {
        this.adresseent = adresseent;
    }

    public String getTelent() {
        return telent;
    }

    public void setTelent(String telent) {
        this.telent = telent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
