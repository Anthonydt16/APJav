package fr.ap.apjavafx.model.DTO;


import java.util.Date;
import java.util.ArrayList;



public class Utilisateur {

    private String login;
    private String nom ;
    private String prenom;
    private String adresse;
    private int telephone;
    

    public Utilisateur(String unLogin , String unNom , String unPrenom  , String uneAdresse , int telephone)
    {
        nom = unNom;
        prenom = unPrenom;
        login = unLogin;
        adresse = uneAdresse;
        telephone = telephone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }
}
	

