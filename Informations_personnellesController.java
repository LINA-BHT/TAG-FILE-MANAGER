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
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Informations_personnellesController implements Initializable {
    private Parent fxml;
        @FXML
    private AnchorPane information_window;
  @FXML
    private Button btt_valider;

    @FXML
    private Button btt_mdp;

    @FXML
    private JFXTextField txt_prenom;
        @FXML
    private JFXTextField txt_nom;

    @FXML
    private JFXTextField txt_login;

    @FXML
    private Label lbl_id;

    @FXML
    private JFXTextField txt_email;

    @FXML
    void fenetre_mdp(MouseEvent event) {
        try {
            
             fxml= FXMLLoader.load(getClass().getResource("/interfaces/Change_mdp.fxml"));
              information_window.getChildren().removeAll();
              information_window.getChildren().setAll(fxml);
          } catch (IOException ex) {
              ex.printStackTrace();
          }
        

    }

    @FXML
    void valide_change(MouseEvent event) {
         Service.Service_user.user.setNom(txt_nom.getText());
           Service.Service_user.user.setPrenom(txt_prenom.getText());
           Service.Service_user.user.setLogin(txt_login.getText());
           Service.Service_user.user.setEmail(txt_email.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            //changer les informations dans la base
            Service.Service_user.Change_information(Service.Service_user.user); 
            
            alert.setHeaderText(null);
            alert.setContentText("les modifications ont été effectués avec succes");
            alert.showAndWait();
           

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         lbl_id.setText("votre ID :"+Service.Service_user.user.getId()+"");
        txt_nom.setText(""+Service.Service_user.user.getNom()+"");
         txt_login.setText(""+Service.Service_user.user.getLogin()+"");
        txt_prenom.setText(""+Service.Service_user.user.getPrenom()+"");
        txt_email.setText(""+Service.Service_user.user.getEmail()+"");
    }    
    
}
