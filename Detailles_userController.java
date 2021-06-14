/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXListView;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.file;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Detailles_userController implements Initializable {
     private Parent fxml;
     @FXML
    private AnchorPane detail_window;

      @FXML
    private Button btt_quitter;

    @FXML
    private Label lbl_id;

    @FXML
    private Label lbl_nbfichier;

    @FXML
    private Label lbl_nomprenom;

    @FXML
    private Label lbl_mail;

    @FXML
    private JFXListView<String> liste_tags;
    
    @FXML
    void quitter(MouseEvent event) {

         try {
 
            
             fxml= FXMLLoader.load(getClass().getResource("/interfaces/Gestion_user.fxml"));
              detail_window.getChildren().removeAll();
              detail_window.getChildren().setAll(fxml);
          } catch (IOException ex) {
              ex.printStackTrace();
        

    }
    }
        @FXML
    void listage_tags(ActionEvent event) {
        try {
            File f = new File("liste tags.txt");
            BufferedReader br = null;
            FileReader fr = null;
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String sCurrentLine ;
            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
            }
            
       
     
       
        
                   
                    Desktop desktop= Desktop.getDesktop();
           
                   desktop.open(f);
                                     
           
         } catch (Exception e) {
            e.printStackTrace();
        }
    

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        int id= Service.Service_user.selecteduser.getId();
        String nom = Service.Service_user.selecteduser.getNom();
        String prenom = Service.Service_user.selecteduser.getPrenom();
        String email= Service.Service_user.selecteduser.getEmail();
        lbl_nbfichier.setText(""+Service.Service_user.count_fichiers_user(id)+"");
        
        lbl_id.setText("id user : "+id +"");
        lbl_nomprenom.setText(""+nom+" "+" "+" "+prenom+"");
        lbl_mail.setText(email);
        List<String> tags = Service.Service_user.gettags_data(id);
         for(int i=0;i<tags.size();i++){
        for(int j=i+1;j<tags.size();j++){
            if(tags.get(i).equals(tags.get(j))){
                tags.remove(j);
                j--;
            }
        }
    }
        for (String tag : tags) {
            liste_tags.getItems().add(tag);
            
        }
        
        
        
        //
          PrintWriter writer;
    try {
        File f=new File("liste tags.txt");
 writer = new PrintWriter(f, "UTF-8");
       for (String tag : tags) {
           writer.println(""+tag+"\n");
           
            
              
          }
       writer.close();
    
     } catch (Exception e) {
       e.printStackTrace();
     }
        
        
        
      
    }    
    
}
