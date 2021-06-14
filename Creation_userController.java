/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Service.EmailValidator;
import Service.Service_user;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import models.user;

/**
 * FXML Controller class
 *
 * @author linah
 */
public class Creation_userController implements Initializable {
    @FXML
    private JFXTextField txt_nom;

    @FXML
    private JFXTextField txt_prenom;

    @FXML
    private JFXTextField txt_email;

    @FXML
    private JFXTextField txt_login;

    @FXML
    private JFXPasswordField txt_mdp;

    @FXML
    private JFXPasswordField txt_mdp1;

    @FXML
    private JFXButton btt_valider;
    
    @FXML
    private void addUser(ActionEvent event ){
       
        String nom= txt_nom.getText();
        String prenom= txt_prenom.getText();
        String login=txt_login.getText();
        String mdp=txt_mdp.getText();
        String email=txt_email.getText();
        String confirmMdp= txt_mdp1.getText();
        EmailValidator emailValidator = new EmailValidator();
    
       
  
        if (nom.isEmpty()|| prenom.isEmpty()|| login.isEmpty() || login.isEmpty() || mdp.isEmpty() || email.isEmpty() )
        {
              System.out.println("empty");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("tu n'a pas saisie tout les chapms");
            alert.showAndWait();
            
            
        }
        else if (emailValidator.validate(email)==false)
        {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("adresse mail non valide !!!!!!!");
            alert.showAndWait();
            
            
        }
        else if (confirmMdp.isEmpty())
        {
             
            System.out.println("confirm empty");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("tu n'a pas confirmer le mot de passe");
            alert.showAndWait();
        }
        else if (!confirmMdp.equals(mdp))
        {
            System.out.println("mdp dif");
            System.out.println(confirmMdp+"xx"+mdp);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("confirmation du mot de passe incorrect");
            alert.showAndWait();
            
        }
        else
        {
            System.out.println("create user");         
            System.out.println(nom+prenom+login+mdp+email);
            user new_user = new user( nom, prenom, login, mdp, email);
            Service_user.ajout(new_user);
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Welcom"+nom+": vous pouvez maintenant connecter a votre compte ");
            alert.showAndWait();
            
            ((Node)event.getSource()).getScene().getWindow().hide();
             Stage primaryStage = new Stage(); 
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/interfaces/Login.fxml"));
        } catch (Exception e) {
                        e.printStackTrace();

        }

        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("se connecter");
        primaryStage.show();
            
            
        }
    }


   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
