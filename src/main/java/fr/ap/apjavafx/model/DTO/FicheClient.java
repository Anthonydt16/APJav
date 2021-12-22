package fr.ap.apjavafx.model.DTO;

public class FicheClient {
    private String nomEnt;
    private String adresseEnt;
    private String VillePays;
    private String telEnt;
    private String emailEnt;
    private String contacter;
    private String inscription;
    private String nomPrenomContact;
    private String mailContact;
    private String telContact;

    public FicheClient(String nomEnt, String adresseEnt, String villePays, String telEnt, String emailEnt, Boolean contacter, String inscription,String nomPrenomContact, String mailContact, String telContact) {
        this.nomEnt = nomEnt;
        this.adresseEnt = adresseEnt;
        VillePays = villePays;
        this.telEnt = telEnt;
        this.emailEnt = emailEnt;
        if(contacter == true){
            this.contacter = "oui";
        }
        else{
            this.contacter = "non";
        }
        this.inscription = inscription;
        this.nomPrenomContact = nomPrenomContact;
        this.mailContact = mailContact;
        this.telContact = telContact;
    }

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
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

    public String getVillePays() {
        return VillePays;
    }

    public void setVillePays(String villePays) {
        VillePays = villePays;
    }

    public String getTelEnt() {
        return telEnt;
    }

    public void setTelEnt(String telEnt) {
        this.telEnt = telEnt;
    }

    public String getEmailEnt() {
        return emailEnt;
    }

    public void setEmailEnt(String emailEnt) {
        this.emailEnt = emailEnt;
    }

    public String getNomPrenomContact() {
        return nomPrenomContact;
    }

    public void setNomPrenomContact(String nomPrenomContact) {
        this.nomPrenomContact = nomPrenomContact;
    }

    public String getMailContact() {
        return mailContact;
    }

    public void setMailContact(String mailContact) {
        this.mailContact = mailContact;
    }

    public String getTelContact() {
        return telContact;
    }

    public void setTelContact(String telContact) {
        this.telContact = telContact;
    }
}
