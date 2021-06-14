/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Service.Service_user;
import Service.sendMail;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Alert;
import Service.EmailValidator;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author linah
 */
public class RecuperPasswordController implements Initializable {
    @FXML
    private Button btt_envoyer;
       @FXML
    private JFXTextField txt_email;

   

    @FXML
    void btt_envoyer(ActionEvent event) {
        
          
         sendMail javaEmail = new sendMail();
         String password;
        String email= txt_email.getText();
        EmailValidator emailValidator = new EmailValidator();
        
        if (emailValidator.validate(email)==false)
        {
            ((Node)event.getSource()).getScene().getWindow().hide();
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("adresse mail non valide !!!!!!!");
            alert.showAndWait();
            
            
        }
        else 
        if(Service_user.mail_exist(email)){
            
            
           password= Service_user.get_password(email);
            
        
        try {
         
          
            javaEmail.sendMail(email,password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
         ((Node)event.getSource()).getScene().getWindow().hide();
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("success,mail envoyer");
            alert.setContentText("Consultez votre boite ");
            alert.showAndWait();
        
        }
        else {
            ((Node)event.getSource()).getScene().getWindow().hide();
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("utilisateur introuvable");
            alert.setContentText("il n ya pas un utilisateur avec cette adresse ");
            alert.showAndWait();
            
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
