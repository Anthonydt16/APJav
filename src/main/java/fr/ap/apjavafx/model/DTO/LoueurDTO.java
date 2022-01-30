package fr.ap.apjavafx.model.DTO;

public class LoueurDTO {
    private int idLoueur;
    private String Nom;
    private String Prenom;
    private String ContactO_N;
    private String typeInscription;
    //boolean les 2 attribut qui suis mais dans la bdd elle pu ça race c'est du string
    private String mailContact;
    private String telContact;

    private String nomLoeur;
    private String adresseLoeur;
    private String TelLoeur;
    private String mailLoeur;

    public LoueurDTO(int idLoeur, String nom, String prenom, String contactO_N, String typeInscription, String mailContact, String telContact, String nomLoeur, String adresseLoeur, String telLoeur, String mailLoeur) {
        this.idLoueur = idLoeur;
        Nom = nom;
        Prenom = prenom;
        ContactO_N = contactO_N;
        this.typeInscription = typeInscription;
        this.mailContact = mailContact;
        this.telContact = telContact;
        this.nomLoeur = nomLoeur;
        this.adresseLoeur = adresseLoeur;
        TelLoeur = telLoeur;
        this.mailLoeur = mailLoeur;
    }

    public int getIdLoueur() {
        return idLoueur;
    }

    public void setIdLoueur(int idLoueur) {
        this.idLoueur = idLoueur;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getContactO_N() {
        return ContactO_N;
    }

    public void setContactO_N(String contactO_N) {
        ContactO_N = contactO_N;
    }

    public String getTypeInscription() {
        return typeInscription;
    }

    public void setTypeInscription(String typeInscription) {
        this.typeInscription = typeInscription;
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

    public String getNomLoeur() {
        return nomLoeur;
    }

    public void setNomLoeur(String nomLoeur) {
        this.nomLoeur = nomLoeur;
    }

    public String getAdresseLoeur() {
        return adresseLoeur;
    }

    public void setAdresseLoeur(String adresseLoeur) {
        this.adresseLoeur = adresseLoeur;
    }

    public String getTelLoeur() {
        return TelLoeur;
    }

    public void setTelLoeur(String telLoeur) {
        TelLoeur = telLoeur;
    }

    public String getMailLoeur() {
        return mailLoeur;
    }

    public void setMailLoeur(String mailLoeur) {
        this.mailLoeur = mailLoeur;
    }
}
