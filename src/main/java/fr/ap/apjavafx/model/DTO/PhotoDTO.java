package fr.ap.apjavafx.model.DTO;

public class PhotoDTO {
    private int idPhoto;
    private int idSalle;
    private String lienphoto;
    private Boolean principal;

    public int getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(int idPhoto) {
        this.idPhoto = idPhoto;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public String getLienphoto() {
        return lienphoto;
    }

    public void setLienphoto(String lienphoto) {
        this.lienphoto = lienphoto;
    }

    public boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public PhotoDTO(int idPhoto, int idSalle, String lienphoto, boolean principal) {
        this.idPhoto = idPhoto;
        this.idSalle = idSalle;
        this.lienphoto = lienphoto;
        this.principal = principal;
    }

}
