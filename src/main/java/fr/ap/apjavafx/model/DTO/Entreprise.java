package fr.ap.apjavafx.model.DTO;

public class Entreprise {
    private int num;
    private int idVille;
    private String nom;
    private String adresse;
    private String tel;
    private String mail;



    public Entreprise(int num,  int idVille, String nom,String adresse, String tel, String mail) {
        this.num = num;
        this.idVille = idVille;
        this.nom = nom;
        this.adresse = adresse;
        this.tel = tel;
        this.mail = mail;
    }
    public int getIdVille() {
        return idVille;
    }

    public void setIdVille(int idVille) {
        this.idVille = idVille;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
