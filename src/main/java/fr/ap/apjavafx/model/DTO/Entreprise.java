package fr.ap.apjavafx.model.DTO;

public class Entreprise {
    private int num;
    private String nom;
    private String adresse;
    private int tel;
    private String mail;

    public Entreprise(int num, String nom, String adresse, int tel, String mail) {
        this.num = num;
        this.nom = nom;
        this.adresse = adresse;
        this.tel = tel;
        this.mail = mail;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
