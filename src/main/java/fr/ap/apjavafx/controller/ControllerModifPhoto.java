package fr.ap.apjavafx.controller;

import fr.ap.apjavafx.model.DAO.PhotoDAO;
import fr.ap.apjavafx.model.DAO.SalleDAO;
import fr.ap.apjavafx.model.DTO.PhotoDTO;
import fr.ap.apjavafx.model.DTO.SalleDTO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.List;

public class ControllerModifPhoto {

    @FXML
    private ComboBox<SalleDTO> cnbxSalle;
    @FXML
    private CheckBox ckbOuiAdd;

    @FXML
    private TextField txAlterLien;

    @FXML
    private TextField txAddLien;

    @FXML
    private CheckBox ckbNonAdd;

    @FXML
    private CheckBox ckbOuiModif;

    @FXML
    private CheckBox ckbNonModif;

    @FXML
    private Button btnSuppPhoto;

    @FXML
    private Button btnModifPhoto;

    @FXML
    private Button btnAddPhoto;

    private PhotoDTO Photo;
    //permet de savoir de quelle bouton le click provient
    private String indicebtn;




    public String getIndicebtn() {
        return indicebtn;
    }

    public void setIndicebtn(String indicebtn) {
        this.indicebtn = indicebtn;
    }

    public PhotoDTO getIdPhoto() {

        return Photo;
    }

    public void setPhoto(PhotoDTO idPhoto)
    {
        this.Photo = idPhoto;
    }



    //setup de l page
    public  void setup() {

        if(this.indicebtn == "add"){
            List<SalleDTO> lesSalle = SalleDAO.SelectSalle();
            //mettre un autre combo box cacher pour l'afficher
            cnbxSalle.setItems(FXCollections.observableArrayList(lesSalle));
            cnbxSalle.setValue(lesSalle.stream().findFirst().get());
            //on cache la parti modification
            txAlterLien.setVisible(false);
            ckbNonModif.setVisible(false);
            ckbOuiModif.setVisible(false);

            btnModifPhoto.setVisible(false);
            btnSuppPhoto.setVisible(false);

        }
        else if(this.indicebtn == "modification"){
            txAddLien.setVisible(false);
            ckbNonAdd.setVisible(false);
            ckbOuiAdd.setVisible(false);
            cnbxSalle.setVisible(false);

            btnAddPhoto.setVisible(false);
            if(this.Photo != null){
                txAlterLien.setText(this.Photo.getLienphoto());
                if (Photo.getPrincipal() == true){
                    ckbOuiModif.setSelected(true);
                    ckbNonModif.setSelected(false);
                }else if(Photo.getPrincipal() == false){
                    ckbNonModif.setSelected(true);
                    ckbOuiModif.setSelected(false);
                }
            }
        }


    }

    //suppression d'image
    public void clickPhoto(ActionEvent actionEvent) {
        PhotoDAO.deletePhoto(Photo.getIdPhoto());
    }
    //modification photo
    public void clickModif(ActionEvent actionEvent) {

        if (ckbOuiModif.isSelected() == true){
            PhotoDAO.modifPhoto(Photo.getIdPhoto(),txAlterLien.getText(),true);
        }
        else if(ckbNonModif.isSelected() == true){
            PhotoDAO.modifPhoto(Photo.getIdPhoto(),txAlterLien.getText(),false);
        }

    }
    //ajout d'une photo
    public void clickAdd(ActionEvent actionEvent) {
        int id = 0;
        id = PhotoDAO.SelectPhotoCountID();
        if (ckbOuiAdd.isSelected() == true){
            PhotoDAO.addPhoto(id+1, cnbxSalle.getValue().getIdSalle(),txAddLien.getText(), true );
        }
        else if(ckbNonAdd.isSelected() == true){
            PhotoDAO.addPhoto(id+1,cnbxSalle.getValue().getIdSalle(),txAddLien.getText(), false );
        }

    }
}
