/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Service.Service_user;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.TAG;
import models.file;


/**
 * FXML Controller class
 *
 * @author linah
 */
public class Modifier_fichierController implements Initializable {
    private Parent fxml;
     @FXML
    private AnchorPane modifier_window;

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
    void quitter(MouseEvent event) {
         try {
            
             fxml= FXMLLoader.load(getClass().getResource("/interfaces/user_favoris.fxml"));
              modifier_window.getChildren().removeAll();
              modifier_window.getChildren().setAll(fxml);
          } catch (IOException ex) {
              ex.printStackTrace();
          }
    }

    @FXML
    void supprimerTag(MouseEvent event) {
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

    @FXML
    void valider(MouseEvent event) {
        String auteur = txt_Auteur.getText();
        String titre = txt_titre.getText();
        String commentaire = txt_commentaire.getText();
        String resume = txt_resume.getText();
        ArrayList tags = new ArrayList();
        for (int i=0; i<lidt_tags.getItems().size();i++){//remplir arraylist tags a partir listeview des tags
          tags.add(lidt_tags.getItems().get(i)) ;
            
        }
      
    
         if(titre.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("le titre est obligatoire");
            alert.setContentText("tu n'a pas saisie un titre a votre fichier");
            alert.showAndWait();
            
        }
      
        else if(tags.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("les tags sont obligatoire");
            alert.setContentText("tu n'a ajouter aucun tag  a votre fichier!!! Ajouter des tags");
            alert.showAndWait();
            
        }
        else {
            
        Service_user.selectedFile.setAuteur(auteur);
        Service_user.selectedFile.setCommentaire(commentaire);
        Service_user.selectedFile.setTitre(titre);
        Service_user.selectedFile.setResumer(resume);
        Service_user.selectedFile.setTags(tags);
        Service_user.selectedFile.setList_tags(tags.toString());
        //appeller la classe service pour modifier fichier selectionner dans la base
        Service.Service_user.modifierFile(Service_user.selectedFile);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("success");
            alert.setContentText("fichier modifier");
            alert.showAndWait();
            try {
            
             fxml= FXMLLoader.load(getClass().getResource("/interfaces/user_favoris.fxml"));
              modifier_window.getChildren().removeAll();
              modifier_window.getChildren().setAll(fxml);
          } catch (IOException ex) {
              ex.printStackTrace();
          }
        
        }
    

        

    }

 
  
   List<String> Listtag = null ;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String replace;
       
        txt_titre.setText(Service_user.selectedFile.getTitre());
        txt_resume.setText(Service_user.selectedFile.getResumer());
        txt_Auteur.setText(Service_user.selectedFile.getAuteur());
        txt_commentaire.setText(Service_user.selectedFile.getCommentaire());
       
        replace = Service_user.selectedFile.getList_tags().replace("[","");
        System.out.println(replace);
       String replace1 = replace.replace("]","");
       System.out.println(replace1);
       Listtag = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
        for (String  list1 : Listtag) {
            lidt_tags.getItems().add(list1);
            
        }
        
        
        
      
        
       
 
       
         
    
    
}
}

