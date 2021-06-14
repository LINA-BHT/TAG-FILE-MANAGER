/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.io.File;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import models.file;
import java.awt.Desktop;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.RadioButton;
import java.net.MalformedURLException;



public class Ajouter_fichierController implements Initializable {

    private Parent fxml;
    @FXML
    private AnchorPane root;
       @FXML
    private JFXTextField txt_titre;

    @FXML
    private JFXTextField txt_Auteur;

    @FXML
    private JFXTextField txt_tag;

    @FXML
    private JFXTextArea txt_commentaire;

    @FXML
    private JFXTextArea txt_resume;

    @FXML
    private JFXListView<String> lidt_tags;
     @FXML
    private  Text  lbl_nomfichier;
         @FXML
    private Label lbl_fichier;

    @FXML
    private  Text  lbl_emplacementfichier;

    @FXML
    private ImageView img_dowload;

    @FXML
    private JFXButton btt_valider;

    @FXML
    private ImageView img_annuler;
        @FXML
    private JFXTextField txt_lienweb;

    @FXML
    private Label lbl_enterlien;
      @FXML
    private RadioButton rdd_fichier;

    @FXML
    private RadioButton rdd_web;

    
    public static file nouv_file = new file();
    
    
       @FXML
    void rddfichier_selected(ActionEvent event) {
        rdd_web.setSelected(false);
        lbl_enterlien.setVisible(false);
        txt_lienweb.setVisible(false);
        img_dowload.setVisible(true);
        lbl_fichier.setVisible(true);
        lbl_emplacementfichier.setVisible(true);
        lbl_nomfichier.setVisible(true);
       

    }

   @FXML
    void rdd_webselected(ActionEvent event) {
        rdd_fichier.setSelected(false);
        lbl_fichier.setVisible(false);
        lbl_emplacementfichier.setVisible(false);
        img_dowload.setVisible(false);
        lbl_enterlien.setVisible(true);
        txt_lienweb.setVisible(true);
        lbl_nomfichier.setVisible(false);

    }

    

    @FXML
           
    void Ajouter_fichiers(ActionEvent event) {

        String auteur = txt_Auteur.getText();
        String titre = txt_titre.getText();
        String commentaire = txt_commentaire.getText();
        String resume = txt_resume.getText();
        ArrayList tags = new ArrayList();
        for (int i=0; i<lidt_tags.getItems().size();i++){//remplir arraylist tags a partir listeview des tags
          tags.add(lidt_tags.getItems().get(i)) ;
            
        }
        //cas fichier non selectonner
         if((nouv_file.getNom_fichier()==null|| nouv_file.getEmplacement()==null) && rdd_fichier.isSelected()==true){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("invalid file");
            alert.setContentText("tu n'a pas selectonner un fichier!! cliquer sour l'image dowload ");
            alert.showAndWait();
            
        }
    
         //cas champ titre nll
         else if(titre.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("le titre est obligatoire");
            alert.setContentText("tu n'a pas saisie un titre a votre fichier");
            alert.showAndWait();
            
        }
      //cas liste tags vide
        else if(tags.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("les tags sont obligatoire");
            alert.setContentText("tu n'a ajouter aucun tag  a votre fichier!!! Ajouter des tags");
            alert.showAndWait();
            
        }
        //ajout de fichier avec succes a la base
        else if (rdd_fichier.isSelected()==true) {
            
        nouv_file.setAuteur(auteur);
        nouv_file.setCommentaire(commentaire);
        nouv_file.setTitre(titre);
        nouv_file.setResumer(resume);
        nouv_file.setTags(tags);
        nouv_file.setType("fichier");
        //appeller la classe service pour ajouter le nouveaux fichier aux base de donnes
        Service.Service_user.ajout_fichier(nouv_file, Service.Service_user.user.getId());
        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("success");
            alert.setContentText("fichier ajouter a votre liste favorie");
            alert.showAndWait();
        try {
            
             fxml= FXMLLoader.load(getClass().getResource("/interfaces/user_favoris.fxml"));
              root.getChildren().removeAll();
              root.getChildren().setAll(fxml);
          } catch (IOException ex) {
              ex.printStackTrace();

          }
        
        
        
        }
        else if(rdd_web.isSelected()==true){
         String lienweb=   txt_lienweb.getText();
          try {
              
      URL url = new URL(lienweb);
      //si Url est valide 
       nouv_file.setAuteur(auteur);
        nouv_file.setCommentaire(commentaire);
        nouv_file.setTitre(titre);
        nouv_file.setResumer(resume);
        nouv_file.setTags(tags);
        nouv_file.setEmplacement(lienweb);
        nouv_file.setNom_fichier(url.getFile());
        nouv_file.setType("page web");
        
         //appeller la classe service pour ajouter la nouveaux page web  aux base de donnes
        Service.Service_user.ajout_fichier(nouv_file, Service.Service_user.user.getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("success");
            alert.setContentText("fichier ajouter a votre liste favorie");
            alert.showAndWait();
        try {
            
             fxml= FXMLLoader.load(getClass().getResource("/interfaces/user_favoris.fxml"));
              root.getChildren().removeAll();
              root.getChildren().setAll(fxml);
          } catch (IOException ex) {
              ex.printStackTrace();

          }
    }
    catch (MalformedURLException e) {
        //invalide url 
         Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("invalid URL");
            alert.setContentText("CE n'est pas une format d'un lien web , entrer un valide url ");
            alert.showAndWait();
    }
            
            
        }
        else {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("no file to stck ");
            alert.setContentText("selectionner un fichier ou entrer un lien web");
            alert.showAndWait();
        }
    }

    @FXML
    void Annuler_ajout(MouseEvent event) {
        try {
            
             fxml= FXMLLoader.load(getClass().getResource("/interfaces/user_favoris.fxml"));
              root.getChildren().removeAll();
              root.getChildren().setAll(fxml);
          } catch (IOException ex) {
              ex.printStackTrace();

          }
    }

    @FXML
    void dowload_fichiers(MouseEvent event) {
        String nom_fichier ;
        String empl_fichier;

        FileChooser files = new FileChooser();
       File selectedfile= files.showOpenDialog(null);
       
       if (selectedfile != null) {
          
           
           
          nom_fichier= selectedfile.getName();
          empl_fichier =selectedfile.getAbsolutePath();
          nouv_file.setNom_fichier(nom_fichier);
          nouv_file.setEmplacement(empl_fichier);
          lbl_fichier.setText("fichier selectionner : ");
          lbl_emplacementfichier.setText(empl_fichier);
          lbl_nomfichier.setText(nom_fichier);
           
       }
       else 
       {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("fichier non falide !!! , selectionner un fichier");
            alert.showAndWait();
       }
     }
     @FXML
    void Ajout_tag(MouseEvent event) {
        String tag = txt_tag.getText();
         if(tag.isEmpty()){
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("CHAMPS VIDE");
            alert.setContentText("ecrire un tag pour l'ajouter a la list");
            alert.showAndWait();
           
       }
       else{
       
       lidt_tags.getItems().add(tag);
       txt_tag.setText("");

    }
    }
    @FXML
    void supprimerTag(ActionEvent event) {
        
         if(lidt_tags.getSelectionModel().getSelectedItem() != null)
        {
            
         int index=  lidt_tags.getSelectionModel().getSelectedIndex();
         lidt_tags.getItems().remove(index);
         
        
            
           
        }
     else{
           Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("TAG NOT SELECTED");
                alert.setHeaderText(null);
            alert.setContentText("sellectionner un tag de votre liste tags pour le supprimer");
           alert.showAndWait();
         }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
