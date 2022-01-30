package fr.ap.apjavafx.model.DTO;

public class Commercial {
    private String login;
    private double pourcentageCommercial;
    private String password ;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String mail;


    public Commercial(String login, double pourcentageCommercial, String password, String nom, String prenom, String adresse, String telephone, String mail) {
        this.login = login;
        this.pourcentageCommercial = pourcentageCommercial;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.mail = mail;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public double getPourcentageCommercial() {
        return pourcentageCommercial;
    }

    public void setPourcentageCommercial(double pourcentageCommercial) {
        this.pourcentageCommercial = pourcentageCommercial;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
