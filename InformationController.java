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
import javafx.scene.text.Text;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import models.user;


public class InformationController implements Initializable {

      @FXML
    private Text txt_id;

    @FXML
    private JFXTextField txt_nom;

    @FXML
    private JFXTextField txt_prenom;

    @FXML
    private JFXTextField txt_login;

    @FXML
    private JFXTextField txt_email;

    @FXML
    private Button btt_mdp;

    @FXML
    private Button btt_valider;

    @FXML
    private Button btt_quiter;
    
    
    
    
    @FXML
        private void fenetre_mdp(ActionEvent event){
            
        Stage primaryStage = new Stage(); 
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/interfaces/Change_Password.fxml"));
        } catch (Exception e) {
                        e.printStackTrace();

        }

        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("changer mot de passe ");
        primaryStage.show();
            
        
    }
         @FXML
        private void valide_change(ActionEvent event ){
           Service.Service_user.user.setNom(txt_nom.getText());
           Service.Service_user.user.setPrenom(txt_prenom.getText());
           Service.Service_user.user.setLogin(txt_login.getText());
           Service.Service_user.user.setEmail(txt_email.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("les modifications ont été effectués avec succes");
            alert.showAndWait();
           Service.Service_user.Change_information(Service.Service_user.user);
            ((Node)event.getSource()).getScene().getWindow().hide();
           Stage primaryStage = new Stage(); 
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/interfaces/Admin.fxml"));
        } catch (Exception e) {
        }

        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ADMINISTRATEUR");
        primaryStage.show();
       
           
        }
            @FXML
    private void quitter(ActionEvent event ){
       ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage(); 
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/interfaces/Admin.fxml"));
        } catch (Exception e) {
                        e.printStackTrace();

        }

        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Administrateur ");
        primaryStage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        txt_id.setText("votre ID :"+Service.Service_user.user.getId()+"");
        txt_nom.setText(""+Service.Service_user.user.getNom()+"");
         txt_login.setText(""+Service.Service_user.user.getLogin()+"");
        txt_prenom.setText(""+Service.Service_user.user.getPrenom()+"");
        txt_email.setText(""+Service.Service_user.user.getEmail()+"");
        
        
        
    }    
    
}
