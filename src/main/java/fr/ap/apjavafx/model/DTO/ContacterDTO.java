package fr.ap.apjavafx.model.DTO;

import java.util.Date;

public class ContacterDTO {
    private String login;
    private Date date;
    private int ident ;

    public ContacterDTO (String unlogin, Date unedate, int unident){
        this.login=unlogin;
        this.date=unedate;
        this.ident=unident;
    }

    public String getLogin() {
        return login;
    }

    public Date getDate() {
        return date;
    }

    public int getIdent() {
        return ident;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }









}