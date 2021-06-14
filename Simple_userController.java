/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Service.Service_user;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author linah
 */
public class Simple_userController implements Initializable {

   private Parent fxml;
    
    @FXML
    private AnchorPane root;
    
    @FXML
    private Text txt_welcome;

  
        @FXML
    void accueil(MouseEvent event) {
        
          try {
             fxml= FXMLLoader.load(getClass().getResource("/interfaces/accueil.fxml"));
              root.getChildren().removeAll();
              root.getChildren().setAll(fxml);
          } catch (IOException ex) {
              ex.printStackTrace();
          }
        

    }

      @FXML
    void afficher_favoris(MouseEvent event) {

          try {
             fxml= FXMLLoader.load(getClass().getResource("/interfaces/user_favoris.fxml"));
              root.getChildren().removeAll();
              root.getChildren().setAll(fxml);
          } catch (IOException ex) {
              ex.printStackTrace();
          }
    }
    
    @FXML
    void Deconnection(MouseEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Delete file");
            alert.setHeaderText(null);
            alert.setContentText("vous etes sur que tu vas deconnecter de votre compte ???");
           Optional<ButtonType> option = alert.showAndWait();
           if(option.get()==ButtonType.OK){
               ((Node)event.getSource()).getScene().getWindow().hide();
           Stage primaryStage = new Stage(); 
        Parent roott = null;
        try {
            roott = FXMLLoader.load(getClass().getResource("/interfaces/Login.fxml"));
        } catch (Exception e) {
                        e.printStackTrace();

        }

        Scene scene = new Scene(roott);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Administrateur ");
        primaryStage.show();
           }
     
    }

   

    @FXML
    void get_information(MouseEvent event) {
          try {
             fxml= FXMLLoader.load(getClass().getResource("/interfaces/Informations_personnelles.fxml"));
              root.getChildren().removeAll();
              root.getChildren().setAll(fxml);
          } catch (IOException ex) {
              ex.printStackTrace();
          } 

    }
 

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_welcome.setText("Bienvenue "+Service_user.user.getNom()+" to TAGFILE");
    }    
    
}
